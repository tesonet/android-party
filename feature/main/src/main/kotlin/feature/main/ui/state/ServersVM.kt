package feature.main.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.ui.state.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import feature.main.data.model.Server
import feature.main.domain.GetServersUseCase
import feature.main.domain.IsSignedInUseCase
import feature.main.domain.SignOutUseCase
import feature.main.domain.SyncServersUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import logcat.LogPriority
import logcat.asLog
import logcat.logcat
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random

fun sampleServers() = Locale.getISOCountries()
    .map { Locale("", it).displayCountry }
    .shuffled()
    .take(30)
    .map {
        val name = "$it #${Random.nextInt(100)}"
        val distance = Random.nextInt(2000)
        Server(name, distance)
    }
    .toImmutableList()

@HiltViewModel
class ServersVm @Inject constructor(
    getServersUseCase: GetServersUseCase,
    private val syncServersUseCase: SyncServersUseCase,
    private val signOutUseCase: SignOutUseCase,
    isSignedInUseCase: IsSignedInUseCase
) : ViewModel() {
    private val sampleServers = sampleServers()
    private val _loading = MutableStateFlow(false)
    private val servers = getServersUseCase().map { it.ifEmpty { sampleServers } }
    private val loading =
        combine(getServersUseCase(), _loading) { servers, loading ->
            val empty = servers.isEmpty()
            when {
                empty && !loading -> Loading.PlaceholderBasic
                empty -> Loading.PlaceholderHighlight
                loading -> Loading.Refreshing
                else -> null
            }
        }
    private val error = MutableStateFlow<Error?>(null)
    private val signedIn = isSignedInUseCase()
    val uiState = combine(servers, loading, error, signedIn)
    { servers, loading, error, signedIn -> UiState(servers, loading, error, signedIn) }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            UiState(sampleServers, Loading.PlaceholderBasic, null, false)
        )

    init {
        viewModelScope.launch {
            if (getServersUseCase().first().isEmpty()) load()
            isSignedInUseCase().drop(1).collect { load() }
        }
    }

    fun load() {
        viewModelScope.launch {
            _loading.value = true
            error.value = try {
                syncServersUseCase()
                null
            } catch (e: SyncServersUseCase.NoAccessToken) {
                Error.NoAccessToken
            } catch (e: Exception) {
                logcat(LogPriority.WARN) { e.asLog() }
                Error.Exception(e.localizedMessage ?: e.javaClass.simpleName)
            }
            _loading.value = false
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
        }
    }

    fun onErrorDismissed() {
        error.value = null
    }

    sealed interface Error {
        @Suppress("ConvertObjectToDataObject")
        object NoAccessToken : Error
        class Exception(val message: String) : Error
    }

    data class UiState(
        val servers: ImmutableList<Server>,
        val loading: Loading?,
        val error: Error?,
        val signedIn: Boolean
    )
}
package lt.havefun.tesonetfunparty.servers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import lt.havefun.tesonetfunparty.MockedApi
import lt.havefun.tesonetfunparty.RxSchedulerRule
import lt.havefun.tesonetfunparty.data.MainRepository
import lt.havefun.tesonetfunparty.data.Server
import lt.havefun.tesonetfunparty.data.db.CachedServersListRepository
import lt.havefun.tesonetfunparty.ui.main.ServersViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ServersViewModelUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var errorObserver: Observer<String>

    @Mock
    lateinit var dataObserver: Observer<List<Server>>

    private lateinit var serversViewModel: ServersViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        val fakeDao = TestServerDao()
        val cachedServersRepo = CachedServersListRepository(fakeDao)
        val mockedApi = MockedApi()
        serversViewModel = ServersViewModel(MainRepository(mockedApi), cachedServersRepo)
    }

    @Test
    fun dataReceived() {
        serversViewModel.data.observeForever(dataObserver)
        serversViewModel.getServersList()

        verify(dataObserver).onChanged(listOf(Server("test",123)))
    }

    @Test
    fun listNotEmpty() {
        serversViewModel.data.observeForever(dataObserver)
        serversViewModel.getServersList()

        verify(dataObserver).onChanged(argThat {
            it.isNotEmpty()
        })
    }

    @Test
    fun dataNotNull() {
        serversViewModel.data.observeForever(dataObserver)
        serversViewModel.getServersList()

        verify(dataObserver, never()).onChanged(null)
    }

    @Test
    fun errorNotReceived() {
        serversViewModel.error.observeForever(errorObserver)
        serversViewModel.getServersList()

        verify(errorObserver, never()).onChanged("")
    }
}
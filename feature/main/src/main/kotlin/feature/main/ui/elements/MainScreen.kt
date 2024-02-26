package feature.main.ui.elements

import androidx.activity.compose.ReportDrawnWhen
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Login
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
//noinspection MissingResourceImportAlias
import com.github.k4dima.testio.feature.main.R
import core.ui.elements.Logo
import core.ui.elements.paddingNoBottom
import core.ui.elements.placeholder
import core.ui.state.Loading
import core.ui.state.placeholder
import core.ui.state.refreshing
import core.ui.theme.CompLocalTheme
import core.ui.theme.TestioTheme
import core.ui.theme.logoPrimary
import feature.main.data.model.Server
import feature.main.ui.state.ServersVm
import feature.main.ui.state.sampleServers
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.annotations.TestOnly
import com.github.k4dima.testio.lib.signin.R as SigninR

@TestOnly
const val TAG_SERVERS = "servers:servers"

@TestOnly
const val TAG_SIGN_IN_OUT = "servers:signInOut"

@Composable
private fun MainSample() =
    MainScreen({}, ServersVm.UiState(sampleServers(), null, null, true), {}, {})

@PreviewLightDark
@Composable
private fun MainPreview() = CompLocalTheme { MainSample() }

@Composable
fun MainScreenWithPreview(onSignIn: () -> Unit) {
    if (LocalInspectionMode.current) MainSample() else MainScreen(onSignIn)
}

@Composable
fun MainScreen(onSignIn: () -> Unit, viewModel: ServersVm = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    MainScreen(
        { viewModel.signOut(); onSignIn() }, uiState, viewModel::load, viewModel::onErrorDismissed
    )
}

@Composable
fun MainScreen(
    onSignIn: () -> Unit,
    uiState: ServersVm.UiState,
    load: () -> Unit,
    onErrorDismissed: () -> Unit
) {
    val (servers, loading, error, signedIn) = uiState
    MainScreen(onSignIn, load, servers, loading, error, onErrorDismissed, signedIn)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun MainScreen(
    onSignIn: () -> Unit,
    load: () -> Unit,
    servers: ImmutableList<Server>,
    loading: Loading?,
    error: ServersVm.Error?,
    onErrorDismissed: () -> Unit,
    signedIn: Boolean
) {
    val scrollBehavior = pinnedScrollBehavior(rememberTopAppBarState())
    val hostState = remember { SnackbarHostState() }
    SnackbarError(error, onSignIn, onErrorDismissed, hostState)
    Scaffold(
        Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .semantics { testTagsAsResourceId = true },
        { MainTopAppBar(onSignIn, scrollBehavior, signedIn) },
        snackbarHost = { SnackbarHost(hostState) },
        content = { MainContent(it, load, servers, loading) }
    )
    ReportDrawnWhen { loading == null }
}

@Composable
private fun SnackbarError(
    error: ServersVm.Error?,
    onSignIn: () -> Unit,
    onErrorDismissed: () -> Unit,
    hostState: SnackbarHostState
) {
    if (error == null) return
    val noAccount = stringResource(SigninR.string.no_account)
    val signIn = stringResource(SigninR.string.sign_in)
    LaunchedEffect(error) {
        var message: String? = null
        var actionLabel: String? = null
        var withDismissAction: Boolean? = null
        var duration: SnackbarDuration? = null
        var onAction: (() -> Unit)? = null
        var onDismiss: (() -> Unit)? = null
        when (error) {
            is ServersVm.Error.NoAccessToken -> {
                message = noAccount
                actionLabel = signIn
                withDismissAction = false
                duration = SnackbarDuration.Indefinite
                onAction = onSignIn
            }

            is ServersVm.Error.Exception -> {
                message = error.message
                withDismissAction = true
                duration = SnackbarDuration.Indefinite
                onDismiss = onErrorDismissed
            }
        }
        when (hostState.showSnackbar(message, actionLabel, withDismissAction, duration)) {
            SnackbarResult.Dismissed -> onDismiss?.invoke()
            SnackbarResult.ActionPerformed -> onAction?.invoke()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainTopAppBar(
    onSignIn: () -> Unit, scrollBehavior: TopAppBarScrollBehavior, signedIn: Boolean
) = Column {
    TopAppBar(
        { TestioTheme(true) { Logo(typography.headlineLarge) } },
        actions = {
            TestioTheme(true) {
                IconButton(onSignIn, Modifier.testTag(TAG_SIGN_IN_OUT)) {
                    val description =
                        if (signedIn) stringResource(R.string.logout) else stringResource(SigninR.string.sign_in)
                    val vector =
                        if (signedIn) Icons.AutoMirrored.Rounded.Logout
                        else Icons.AutoMirrored.Rounded.Login
                    Icon(vector, description, Modifier.size(32.dp), logoPrimary())
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
    Header(scrollBehavior)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun appBarContainerColor(scrollBehavior: TopAppBarScrollBehavior): Color {
    val containerColor = MaterialTheme.colorScheme.surface
    val scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
    val colorTransitionFraction = scrollBehavior.state.overlappedFraction
    val fraction = if (colorTransitionFraction > 0.01f) 1f else 0f
    val appBarContainerColor by animateColorAsState(
        lerp(containerColor, scrolledContainerColor, FastOutLinearInEasing.transform(fraction)),
        spring(stiffness = Spring.StiffnessMediumLow),
        "AppBarContainerColor"
    )
    return appBarContainerColor
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header(scrollBehavior: TopAppBarScrollBehavior) {
    val insets = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal)
    Row(
        Modifier
            .composed {
                val color = appBarContainerColor(scrollBehavior)
                drawBehind { drawRect(color) }
            }
            .padding(horizontal = 16.dp)
            .height(36.dp)
            .fillMaxWidth()
            .windowInsetsPadding(insets),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        HeaderText(R.string.server)
        HeaderText(R.string.distance)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainContent(
    paddingValues: PaddingValues,
    load: () -> Unit,
    servers: ImmutableList<Server>,
    loading: Loading?
) {
    val refreshing = loading.refreshing
    val state = rememberPullRefreshState(refreshing, load)
    Box(
        Modifier
            .pullRefresh(state)
            .paddingNoBottom(paddingValues)
    ) {
        Servers(servers, loading)
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
private fun Servers(servers: ImmutableList<Server>, loading: Loading?) {
    val userScrollEnabled = !loading.placeholder
    val modifierColumn = Modifier
        .fillMaxSize()
        .let { if (userScrollEnabled) it.testTag(TAG_SERVERS) else it }
    LazyColumn(
        modifierColumn, contentPadding = PaddingValues(horizontal = 16.dp),
        userScrollEnabled = userScrollEnabled
    ) {
        item(contentType = "divider") { HorizontalDivider() }
        items(servers) {
            Row(Modifier.height(48.dp), verticalAlignment = Alignment.CenterVertically) {
                val modifier = Modifier.placeholder(loading)
                val style = typography.titleMedium
                Box(Modifier.weight(1f)) {
                    Text(
                        it.name, modifier, overflow = TextOverflow.Ellipsis, maxLines = 1,
                        style = style
                    )
                }
                Spacer(Modifier.weight(0.1f))
                Text(stringResource(R.string.km, it.distance), modifier, style = style)
            }
        }
    }
}

@Composable
private fun HeaderText(@StringRes id: Int) =
    Text(
        stringResource(id).uppercase(), fontWeight = FontWeight.Bold, style = typography.titleMedium
    )
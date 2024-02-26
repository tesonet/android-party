package signin.ui.elements

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DonutLarge
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
//noinspection MissingResourceImportAlias
import com.github.k4dima.testio.lib.signin.R
import core.ui.elements.navigationIcon
import core.ui.theme.ModernTheme
import kotlinx.collections.immutable.ImmutableList
import signin.ui.state.LoginViewModel

const val TAG_USERNAME = "signin:username"
const val TAG_PASSWORD = "signin:password"

@OptIn(ExperimentalComposeUiApi::class)
@PreviewPixel5Landscape
@Composable
private fun SignInScreenPreview() =
    ModernTheme {
        val uiState = LoginViewModel.UiState("k4dima", "password", true, null)
        SignInScreen({}, { SignInPreviewTitle() }, uiState, {}, {}, {}, pixel5Size)
    }

@OptIn(ExperimentalComposeUiApi::class)
@PreviewLightDark
@Composable
private fun LoginScreenPreview() =
    ModernTheme {
        val uiState = LoginViewModel.UiState("", "", false, null)
        SignInScreen({}, { LoginPreviewTitle() }, uiState, {}, {}, {}, pixel5Size)
    }

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
annotation class PreviewPixel5Landscape

val pixel5Size: WindowSizeClass
    @Composable
    get() {
        val dpSize = when (LocalConfiguration.current.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> DpSize(1200.dp, 400.dp)
            else -> DpSize(400.dp, 1200.dp)
        }
        @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
        return WindowSizeClass.calculateFromSize(dpSize)
    }

@Composable
private fun SignInPreviewTitle() =
    Column {
        Icon(Icons.Rounded.DonutLarge, "logo", Modifier.size(64.dp))
        Text("sign in", style = typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("with your cloud account")
        Spacer(Modifier.height(24.dp))
    }

@Composable
private fun LoginPreviewTitle() =
    Text(
        "testio.",
        color = colorScheme.primary,
        fontWeight = FontWeight.Bold,
        style = typography.displayLarge
    )

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SignInScreen(
    onBack: () -> Unit,
    title: @Composable ColumnScope.() -> Unit,
    footer: @Composable ((Modifier) -> Unit)? = null,
    background: @Composable (() -> Unit)? = null,
    autofill: ImmutableList<AutofillType>? = null,
    credential: Boolean = false,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val windowSizeClass = calculateWindowSizeClass(activity)
    val onBackCurrent by rememberUpdatedState(onBack)
    val onSignInCurrent by rememberUpdatedState(viewModel::signIn)
    LaunchedEffect(Unit) {
        if (credential) viewModel.getCredential(activity)
        viewModel.serversScreen.collect {
            if (credential) viewModel.createCredential(activity)
            onBackCurrent()
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    SignInScreen(
        onBackCurrent, title, uiState, viewModel::onUsernameChange, viewModel::onPasswordChange,
        onSignInCurrent, windowSizeClass, footer, background, autofill
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    onBack: () -> Unit,
    title: @Composable ColumnScope.() -> Unit,
    uiState: LoginViewModel.UiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    signIn: () -> Unit,
    windowSizeClass: WindowSizeClass,
    footer: @Composable ((Modifier) -> Unit)? = null,
    background: @Composable (() -> Unit)? = null,
    autofill: ImmutableList<AutofillType>? = null,
) {
    val (username, password, loading, error) = uiState
    SignInScreen(
        onBack, title, footer, background, autofill, username, password, onUsernameChange,
        onPasswordChange, signIn, loading, error, windowSizeClass
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun SignInScreen(
    onBack: (() -> Unit),
    title: @Composable ColumnScope.() -> Unit,
    footer: @Composable ((Modifier) -> Unit)?,
    background: @Composable (() -> Unit)?,
    autofill: ImmutableList<AutofillType>?,
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    signIn: () -> Unit,
    loading: Boolean,
    error: String?,
    windowSizeClass: WindowSizeClass
) = Surface {
    background?.invoke()
    val containerColor = Color.Transparent
    Scaffold(
        topBar = {
            TopAppBar(
                {},
                navigationIcon = navigationIcon(onBack),
                colors = topAppBarColors(containerColor)
            )
        },
        containerColor = containerColor
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val horizontalSpace = 32.dp
            val maxWidth = 340.dp
            when (windowSizeClass.heightSizeClass) {
                WindowHeightSizeClass.Compact -> {
                    Landscape(
                        horizontalSpace, maxWidth, loading, title, autofill, onUsernameChange,
                        onPasswordChange, username, error, password, signIn, footer
                    )
                }

                else -> {
                    Portrait(
                        horizontalSpace, maxWidth, loading, title, autofill, onUsernameChange,
                        onPasswordChange, username, error, password, signIn,
                        Modifier.align(Alignment.Center), footer
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Portrait(
    horizontalSpace: Dp,
    maxWidth: Dp,
    loading: Boolean,
    title: @Composable (ColumnScope.() -> Unit),
    autofill: ImmutableList<AutofillType>?,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    username: String,
    error: String?,
    password: String,
    signIn: () -> Unit,
    modifier: Modifier,
    footer: @Composable ((Modifier) -> Unit)?
) = Column(
    modifier
        .padding(horizontal = horizontalSpace)
        .widthIn(max = maxWidth)
) {
    Loader(loading)
    val spacerModifier = Modifier.weight(1f)
    Spacer(spacerModifier)
    title()
    Form(autofill, onUsernameChange, onPasswordChange, username, error, password, signIn, loading)
    Spacer(spacerModifier)
    footer?.invoke(Modifier.align(Alignment.End))
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Landscape(
    horizontalSpace: Dp,
    maxWidth: Dp,
    loading: Boolean,
    title: @Composable (ColumnScope.() -> Unit),
    autofill: ImmutableList<AutofillType>?,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    username: String,
    error: String?,
    password: String,
    signIn: () -> Unit,
    footer: @Composable ((Modifier) -> Unit)?
) = Column(Modifier.padding(horizontal = horizontalSpace)) {
    Loader(loading)
    Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpace)) {
        val modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
        Column(modifier, Arrangement.Top, content = title)
        Box(modifier, Alignment.Center) {
            Column(
                Modifier
                    .widthIn(max = maxWidth)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                Arrangement.SpaceBetween
            ) {
                Form(
                    autofill, onUsernameChange, onPasswordChange, username, error, password, signIn,
                    loading
                )
                footer?.invoke(Modifier.align(Alignment.End))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun Form(
    autofill: ImmutableList<AutofillType>?,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    username: String,
    error: String?,
    password: String,
    signIn: () -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier
) = Column(modifier.semantics { testTagsAsResourceId = true }) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val textFieldModifier = Modifier.fillMaxWidth()
    val usernameModifier = textFieldModifier
        .testTag(TAG_USERNAME)
        .let { if (autofill != null) it.autofill(autofill, onUsernameChange) else it }
    val passwordModifier = textFieldModifier
        .testTag(TAG_PASSWORD)
        .let {
            if (autofill != null) it.autofill(listOf(AutofillType.Password), onPasswordChange)
            else it
        }
    OutlinedTextField(
        username,
        onUsernameChange,
        usernameModifier,
        label = { Text(stringResource(R.string.username)) },
        isError = error != null,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true
    )
    OutlinedTextField(
        password,
        onPasswordChange,
        passwordModifier,
        label = { Text(stringResource(R.string.password)) },
        trailingIcon = {
            val image =
                if (passwordVisible) Icons.Rounded.Visibility
                else Icons.Rounded.VisibilityOff
            val description =
                stringResource(if (passwordVisible) R.string.hide_password else R.string.show_password)
            IconButton({ passwordVisible = !passwordVisible }) { Icon(image, description) }
        },
        supportingText = { Text(error ?: "") },
        isError = error != null,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                defaultKeyboardAction(ImeAction.Done)
                signIn()
            }
        ),
        singleLine = true
    )
    Spacer(Modifier.height(4.dp))
    val keyboardController = LocalSoftwareKeyboardController.current
    val enabled = !loading && username.isNotBlank() && password.isNotBlank()
    Button(
        { keyboardController?.hide(); signIn() },
        Modifier
            .fillMaxWidth()
            .height(60.dp),
        enabled
    ) { Text(stringResource(R.string.sign_in)) }
}

@Composable
private fun Loader(loading: Boolean) {
    val alpha by animateFloatAsState(if (loading) 1f else 0f, tween(), label = "progress")
    val modifier = Modifier
        .alpha(alpha)
        .fillMaxWidth()
    LinearProgressIndicator(modifier)
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autofill(autofillTypes: List<AutofillType>, onFill: ((String) -> Unit)) = composed {
    val autofill = LocalAutofill.current
    val autofillNode = AutofillNode(autofillTypes, onFill = onFill)
    LocalAutofillTree.current += autofillNode
    onGloballyPositioned { autofillNode.boundingBox = it.boundsInWindow() }
        .onFocusChanged {
            autofill?.run {
                if (it.isFocused) requestAutofillForNode(autofillNode)
                else cancelAutofillForNode(autofillNode)
            }
        }
}
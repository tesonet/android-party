package core.ui.state

@Suppress("ConvertObjectToDataObject")
sealed interface Loading {
    object PlaceholderBasic : Loading
    object PlaceholderHighlight : Loading
    object Refreshing : Loading
}

val Loading?.placeholderBasic get() = this == Loading.PlaceholderBasic
val Loading?.placeholderHighlight get() = this == Loading.PlaceholderHighlight
val Loading?.refreshing get() = this == Loading.Refreshing
val Loading?.placeholder get() = placeholderBasic || placeholderHighlight
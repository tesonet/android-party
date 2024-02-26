object Config {
    const val COMPILE_SDK = 34
    const val MIN_SDK = 21
    const val TARGET_SDK = COMPILE_SDK
    const val JAVA = 17
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.10"
    val lintEnable = listOf(
        "AppCompatMethod", "AppLinksAutoVerify", "BackButton", "ComposableLambdaParameterNaming",
        "ComposableLambdaParameterPosition", "ConvertToWebp", "DalvikOverride", "DefaultEncoding",
        "DuplicateStrings", "EasterEgg", "ExpensiveAssertion", "IconExpectedSize",
        "ImplicitSamInstance", "InvalidPackage", "KotlinPropertyAccess", "KotlincFE10",
        "LambdaLast", "LintDocExample", "LintImplPsiEquals", "LintImplUnexpectedDomain",
        "LogConditional", "MangledCRLF", "MinSdkTooLow", "NegativeMargin", "NewerVersionAvailable",
        "NoHardKeywords", "NoOp", "PermissionNamingConvention", "Registered", "RequiredSize",
        "SelectableText", "StopShip", "StringFormatTrivial", "SyntheticAccessor",
        "TypographyQuotes", "UnknownNullness", "UnsupportedChromeOsHardware", "UnusedIds",
        "ValidActionsXml", "VulnerableCordovaVersion", "WrongThreadInterprocedural",
        "UnsafeImplicitIntentLaunch"
    )
    val lintDisable = listOf(
        "InvalidSingleLineComment", "MissingXmlHeader", "RawColor", "WrongDrawableName",
        "WrongGlobalIconColor", "XmlSpacing", "StringNotCapitalized"
    )
}
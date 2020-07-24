package gj.tesonet.data.model

data class Token(val token: String) {

    val bearer: String
        get() = "Bearer $token"

}

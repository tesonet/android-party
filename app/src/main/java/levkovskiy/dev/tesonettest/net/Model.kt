package levkovskiy.dev.tesonettest.net

object Model {
  data class Login(
    val token: String = ""
  )

  data class Server(
    val name: String = "",
    val distance: Int = 0
  )
}

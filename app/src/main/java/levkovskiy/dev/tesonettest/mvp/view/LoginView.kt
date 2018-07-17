package levkovskiy.dev.tesonettest.mvp.view


interface LoginView:BaseView{

  fun success(token: String)
  fun error()
}
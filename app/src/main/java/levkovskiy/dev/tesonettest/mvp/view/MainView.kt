package levkovskiy.dev.tesonettest.mvp.view

import levkovskiy.dev.tesonettest.net.Model

interface MainView : BaseView {

  fun errorLoad()
  fun successLoad(list: List<Model.Server>)
  fun logout()
}
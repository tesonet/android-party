package com.giedrius.androidparty.ui

import com.giedrius.androidparty.ui.MainActivityContract.*

class MainActivityPresenter(view: View) : Presenter {

  private var view: View = view

  init {
    view.initView()
  }


  companion object {

  }
}
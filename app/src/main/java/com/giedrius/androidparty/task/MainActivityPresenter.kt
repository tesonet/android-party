package com.giedrius.androidparty.task

import com.giedrius.androidparty.task.MainActivityContract.*

class MainActivityPresenter(view: View) : Presenter {

  private var view: View = view

  init {
    view.initView()
  }


  companion object {

  }
}
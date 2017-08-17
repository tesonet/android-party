package com.example.lukas.tesonettest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lukas.tesonettest.activity.MainActivity
import io.reactivex.disposables.CompositeDisposable
import lt.topocentras.android.Prefs

/**
 * Created by lukas on 17.8.17.
 */
abstract class BaseFragment : Fragment() {
	abstract val layoutId: Int
	override fun onCreateView(inflater: LayoutInflater,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {

		return inflater.inflate(layoutId, container, false)
	}

	protected fun changeFragment(fragment: BaseFragment, addToBackStack: Boolean = true) {
		if (activity is MainActivity) {
			(activity as MainActivity).changeFragment(fragment, addToBackStack)
		}
	}
	protected fun logout() {
		Prefs.authorization = null
		changeFragment(LoginFragment.getInstance(),false)
	}

	val disposable by lazy {
		CompositeDisposable()
	}

	override fun onStop() {
		super.onStop()
		disposable.clear()
	}
}
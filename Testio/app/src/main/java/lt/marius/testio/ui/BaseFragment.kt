package lt.marius.testio.ui

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by marius on 17.8.21.
 */
abstract class BaseFragment : LifecycleFragment() {
	abstract val layoutId: Int

	override fun onCreateView(inflater: LayoutInflater,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View = inflater.inflate(layoutId,
	                                                                                container,
	                                                                                false)

}
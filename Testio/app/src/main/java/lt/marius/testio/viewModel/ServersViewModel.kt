package lt.marius.testio.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import lt.marius.testio.model.Server

/**
 * Created by marius on 17.8.21.
 */
class ServersViewModel : ViewModel() {
	val servers = MutableLiveData<List<Server>>()
}
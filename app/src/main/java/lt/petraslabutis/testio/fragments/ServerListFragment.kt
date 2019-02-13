package lt.petraslabutis.testio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lt.petraslabutis.testio.R

class ServerListFragment : BaseFragment() {

    override fun inject() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_server_list, container, false)
    }

}
package lt.havefun.tesonetfunparty.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import lt.havefun.tesonetfunparty.MainActivity
import lt.havefun.tesonetfunparty.R
import lt.havefun.tesonetfunparty.events.ServersLoadedEvent
import lt.havefun.tesonetfunparty.ui.CustomFragment
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class ServersFragment: CustomFragment() {

    @Inject
    lateinit var eventBus: EventBus

    @Inject
    lateinit var viewModelFactory: ServersViewModel.Factory

    companion object {
        fun newInstance() = ServersFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingViewsLayout: ConstraintLayout
    private lateinit var viewModel: ServersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.servers_fragment, container, false)
        recyclerView = view.findViewById(R.id.servers_rv)
        loadingViewsLayout = view.findViewById(R.id.loading_cl)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ServersViewModel::class.java)

        viewModel.getServersList()

        viewModel.data.observe(viewLifecycleOwner, Observer { servers ->
            loadingViewsLayout.visibility = View.GONE
            eventBus.post(ServersLoadedEvent(true))
            with(recyclerView) {
                adapter = ServerRecyclerViewAdapter(servers)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            eventBus.post(ServersLoadedEvent(false))
            Toast.makeText(
                context,
                getString(R.string.error),
                Toast.LENGTH_LONG)
                .show()
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).activityComponent.inject(this)
    }
}
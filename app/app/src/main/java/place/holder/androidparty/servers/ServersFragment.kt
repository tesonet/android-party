package place.holder.androidparty.servers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_servers.view.*
import place.holder.androidparty.AppController
import place.holder.androidparty.R

class ServersFragment : Fragment() {

    private val serverListAdapter = ServerListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_servers, container, false).also { view ->
            view.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.servers_bg, view.context.theme))
            view.serversRecyclerView.also {
                it.layoutManager = LinearLayoutManager(it.context)
                it.adapter = serverListAdapter
            }
        }
    }

    override fun onStart() {
        super.onStart()
        view?.apply {
            logoutImageView.setOnClickListener {
                AppController.instance.token = null
                val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.serversFragment, true)
                        .setExitAnim(R.anim.slide_out_right)
                        .setPopEnterAnim(R.anim.idle)
                        .build()
                findNavController(this).navigate(ServersFragmentDirections.actionLogout(), navOptions)
            }
        }
    }

    override fun onStop() {
        view?.apply {
            logoutImageView.setOnClickListener(null)
        }
        super.onStop()
    }

    override fun onDestroyView() {
        serverListAdapter.servers = mutableListOf()
        super.onDestroyView()
    }
}
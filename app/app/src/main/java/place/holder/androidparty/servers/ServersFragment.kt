package place.holder.androidparty.servers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.fragment_servers.view.*
import place.holder.androidparty.AppController
import place.holder.androidparty.R

class ServersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_servers, container, false).also {
            it.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.servers_bg, it.context.theme))
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
}
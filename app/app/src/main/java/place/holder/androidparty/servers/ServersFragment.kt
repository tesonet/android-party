package place.holder.androidparty.servers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import place.holder.androidparty.R

class ServersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_servers, container, false).also {
            it.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.servers_bg, it.context.theme))
        }
    }
}
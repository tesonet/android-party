package net.justinas.tesonetapp.withlib.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list.*
import net.justinas.minilist.domain.item.IdEntity
import net.justinas.tesonetapp.withlib.databinding.FragmentListBinding
import net.justinas.tesonetapp.withlib.view.adapter.IdLinearEntityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListViewFragment : Fragment(), IdLinearEntityAdapter.Callbacks {

    val viewModel: ListViewModel by viewModel()

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.callback = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exit.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun onItemClick(view: View, item: IdEntity) {
        val bundle = Bundle()
        bundle.putSerializable("ID", item)
        Snackbar.make(view, item.name, Snackbar.LENGTH_SHORT).show()
    }
}
package net.justinas.tesonetapp.withlib.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import net.justinas.minilist.databinding.FragmentListBinding
import net.justinas.minilist.domain.IdEntity
import net.justinas.minilist.view.list.IdEntityAdapter
import net.justinas.minilist.view.list.ListViewModel
import net.justinas.tesonetapp.withlib.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListViewFragment : Fragment(), IdEntityAdapter.Callbacks {

    val viewModel: ListViewModel by viewModel()

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.callback = this
        binding.header.item = IdEntity(0, "SERVER", "DISTANCE")
        binding.toolbar.setLogo(R.drawable.ic_tesonet_logo)
        return binding.root
    }

    override fun onItemClick(view: View, item: IdEntity) {
        val bundle = Bundle()
        bundle.putSerializable("ID", item)
        Snackbar.make(view, item.name, Snackbar.LENGTH_SHORT).show()
    }
}
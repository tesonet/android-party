package net.justinas.tesonetapp.withlib.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import net.justinas.minilist.databinding.LoginBinding
import net.justinas.minilist.view.login.LoginViewModel
import net.justinas.tesonetapp.withlib.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = LoginBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setCustomDesign(binding)
        return binding.root
    }

    private fun setCustomDesign(binding: LoginBinding) {
        binding.loginBg.setImageResource(R.drawable.bg)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnLoginGoNext()
    }

    private fun setOnLoginGoNext() {
        viewModel.successLogin.observe(this, Observer {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.container, ListViewFragment(), "h")
                .addToBackStack("h")
                .commit()
        })
    }
}

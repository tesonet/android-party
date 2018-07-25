package com.playground.ugnius.homework.views.fragments

import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.Shader.TileMode.CLAMP
import android.support.v7.widget.RecyclerView.VERTICAL
import android.os.Bundle
import android.graphics.Color.WHITE
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.playground.ugnius.homework.R
import com.playground.ugnius.homework.global.App
import com.playground.ugnius.homework.model.ServersRepository
import com.playground.ugnius.homework.views.activites.MainActivity
import com.playground.ugnius.homework.views.adapters.ServersAdapter
import kotlinx.android.synthetic.main.fragment_servers.*
import javax.inject.Inject

class ServersFragment : Fragment() {

    @Inject lateinit var serversRepository: ServersRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity?.applicationContext as? App)?.mainComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_servers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView.shade(ContextCompat.getColor(context!!, R.color.gray), 0.32F)
        initializeRecyclerView()
        logout.setOnClickListener {
            serversRepository.clear()
            (activity as MainActivity).navigate(LoginFragment())
        }
    }

    private fun initializeRecyclerView() {
        with(serversRecycler) {
            adapter = ServersAdapter(context = context!!, entries = serversRepository.getServers())
            layoutManager = LinearLayoutManager(context)
            val divider = ContextCompat.getDrawable(context!!, R.drawable.divider)!!
            val decoration = DividerItemDecoration(context!!, VERTICAL).apply { setDrawable(divider) }
            addItemDecoration(decoration)
        }
    }

    private fun View.shade(color: Int, ratio: Float) {
        if (ratio < 0 && ratio > 1F) {
            throw IllegalArgumentException("Ratio must be a float value between 0 and 1")
        }
        val shader = object : ShapeDrawable.ShaderFactory() {
            override fun resize(width: Int, height: Int): Shader {
                return LinearGradient(
                    (width / 2).toFloat(),
                    0F,
                    (width / 2).toFloat(),
                    (height).toFloat(),
                    intArrayOf(color, WHITE, WHITE),
                    floatArrayOf(0f, ratio, 1f),
                    CLAMP
                )
            }
        }
        with(PaintDrawable()) {
            shape = RectShape()
            shaderFactory = shader
            background = this
        }
    }

}

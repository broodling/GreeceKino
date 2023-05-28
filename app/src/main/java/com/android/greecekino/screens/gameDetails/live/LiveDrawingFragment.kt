package com.android.greecekino.screens.gameDetails.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings.PluginState
import androidx.fragment.app.Fragment
import com.android.greecekino.LIVE_DRAFT_URL
import com.android.greecekino.databinding.FragmentLiveDrawingBinding


class LiveDrawingFragment : Fragment() {

    private var binding: FragmentLiveDrawingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLiveDrawingBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.webView?.apply {
            settings.javaScriptEnabled = true
            settings.setSupportMultipleWindows(false)
            settings.setSupportZoom(true)
            settings.loadWithOverviewMode = true
            settings.builtInZoomControls = true
            isVerticalScrollBarEnabled = true
            isHorizontalScrollBarEnabled = true
            binding?.webView?.loadUrl(LIVE_DRAFT_URL)

        }
    }
}
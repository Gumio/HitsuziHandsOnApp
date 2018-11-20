package com.gumioji.hitsuziqiitaviewerapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.gumioji.hitsuziqiitaviewerapp.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    companion object {
        private const val ARGS_NAME = "web_url"

        @JvmStatic
        fun newInstance(url: String): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGS_NAME, url)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupView() {
        val url = arguments?.getString(ARGS_NAME)

        parentFragment?.view?.apply {
            setOnKeyListener(View.OnKeyListener { _, _, event ->
                if (event.keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                    fragmentManager!!.popBackStack()
                    return@OnKeyListener true
                }
                false
            })

            setOnClickListener(View.OnClickListener { return@OnClickListener })
        }

        web_view.apply {
            webViewClient = WebViewClient()
            loadUrl(url)
            settings.javaScriptEnabled = true

            setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
                if (event?.action == KeyEvent.ACTION_DOWN) {
                    val browseWeb = view as WebView

                    when (keyCode) {
                        KeyEvent.KEYCODE_BACK -> if (browseWeb.canGoBack()) {
                            browseWeb.goBack()
                            return@OnKeyListener true
                        }
                    }
                }
                false
            })
        }
    }
}

package com.gumioji.hitsuziqiitaviewerapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.keyCode == KeyEvent.KEYCODE_BACK) {
                if (event.action == KeyEvent.ACTION_UP) {
                    fragmentManager!!.popBackStack()
                    return@OnKeyListener true
                } else {
                    return@OnKeyListener true
                }
            }
            false
        })

        rootView.setOnClickListener(View.OnClickListener { return@OnClickListener })

        val args = arguments
        webView = web_view
        val url = args!!.getString(ARGS_NAME)

        // リンクをタップしたときに標準ブラウザを起動させない
        webView .webViewClient = WebViewClient()
        // 最初に投稿を表示
        webView .loadUrl(url)
        // jacascriptを許可する
        webView.settings.javaScriptEnabled = true

        webView.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
            if (event?.action == KeyEvent.ACTION_DOWN) {
                val hp_web = view as WebView

                when (keyCode) {
                    KeyEvent.KEYCODE_BACK -> if (hp_web.canGoBack()) {
                        hp_web.goBack()
                        return@OnKeyListener true
                    }
                }
            }
            false
        })
    }

    companion object {
        private val ARGS_NAME = "web_url"

        @JvmStatic
        fun newInstance(url: String): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGS_NAME, url)
                }
            }
        }
    }
}

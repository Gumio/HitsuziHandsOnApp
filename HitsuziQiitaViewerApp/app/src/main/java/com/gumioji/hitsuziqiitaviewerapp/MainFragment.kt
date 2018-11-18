package com.gumioji.hitsuziqiitaviewerapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class MainFragment : Fragment() {

    private lateinit var mAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = ListAdapter(activity?.applicationContext!!, R.layout.list_item)
        val listView = list_view
        listView.adapter = mAdapter

        val editText = edit_text
        editText.setOnKeyListener(View.OnKeyListener { view, keyCode, keyEvent ->
            if (keyEvent.action != KeyEvent.ACTION_UP || keyCode != KeyEvent.KEYCODE_ENTER) {
                return@OnKeyListener false
            }

            val editText = view as EditText
            // キーボードを閉じる
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)

            var text = editText.text.toString()
            try {
                // url encode　例. スピッツ > %83X%83s%83b%83c
                text = URLEncoder.encode(text, "UTF-8")
            } catch (e: UnsupportedEncodingException) {
                Log.e("", e.toString(), e)
                return@OnKeyListener true
            }

            if (!TextUtils.isEmpty(text)) {
                val request = QiitaClient.create().items(text)
                Log.d("", request.request().url().toString())
                val item = object : Callback<List<Item>> {
                    override fun onResponse(call: Call<List<Item>>?, response: Response<List<Item>>?) {
                        mAdapter.clear()
                        response?.body()?.forEach { mAdapter.add(it) }
                    }

                    override fun onFailure(call: Call<List<Item>>?, t: Throwable?) {
                    }
                }
                request.enqueue(item)
            }
            true
        })

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val result = mAdapter.getItem(position)
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.main_container, DetailFragment.newInstance(result.url.toString()))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}

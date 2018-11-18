package com.gumioji.hitsuziqiitaviewerapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val result = mAdapter.getItem(position)
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.main_container, DetailFragment.newInstance(result.url.toString()))
            transaction.addToBackStack(null)
            transaction.commit()
        }

        val request = QiitaClient.create().items()
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

    fun searchRequest(searchText: String) {
        var text = searchText
        try {
            // url encode　例. スピッツ > %83X%83s%83b%83c
            text = URLEncoder.encode(text, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            Log.e("", e.toString(), e)
        }

        if (text.isNotEmpty()) {
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
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}

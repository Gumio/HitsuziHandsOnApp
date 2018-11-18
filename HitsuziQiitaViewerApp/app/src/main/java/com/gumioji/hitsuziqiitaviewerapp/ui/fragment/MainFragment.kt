package com.gumioji.hitsuziqiitaviewerapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.gumioji.hitsuziqiitaviewerapp.R
import com.gumioji.hitsuziqiitaviewerapp.adapter.ListAdapter
import com.gumioji.hitsuziqiitaviewerapp.ext.replaceFragment
import com.gumioji.hitsuziqiitaviewerapp.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import java.net.URLEncoder


class MainFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private lateinit var mAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = ListAdapter(
            context!!,
            R.layout.list_item
        )

        list_view.apply {
            adapter = mAdapter
            onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val result = mAdapter.getItem(position)
                replaceFragment(DetailFragment.newInstance(result?.url.toString()), R.id.main_container)
            }
        }
        searchRequest()
    }

    fun searchRequest(searchText: String? = null) {
        val text: String? = searchText?.let {
            URLEncoder.encode(it, "UTF-8")
        }

        val subscribe = SearchRepository.searchItem(text)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                mAdapter.clear()
                result?.forEach { mAdapter.add(it) }
            }, { error ->
                error.printStackTrace()
            })
    }
}

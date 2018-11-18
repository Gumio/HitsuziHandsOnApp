package com.gumioji.hitsuziqiitaviewerapp.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.gumioji.hitsuziqiitaviewerapp.R
import com.gumioji.hitsuziqiitaviewerapp.adapter.ListAdapter
import com.gumioji.hitsuziqiitaviewerapp.data.Item
import kotlinx.android.synthetic.main.fragment_main.*


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
                Snackbar.make(this, "${position}番目のイベントが取れたぜ", Snackbar.LENGTH_SHORT).show()
            }
        }
        dummy()
    }

    fun dummy() {
        mutableListOf<Item>().apply {
            repeat(10) {
                add(Item(title = "hogehoge",
                        user = Item.User(
                            id = "hogeo",
                            profile_image_url = "https://secure.gravatar.com/avatar/039bd34ca27b927998bd38483d505074"
                        )
                ))
            }
        }.toList().forEach { mAdapter.add(it) }
    }
}

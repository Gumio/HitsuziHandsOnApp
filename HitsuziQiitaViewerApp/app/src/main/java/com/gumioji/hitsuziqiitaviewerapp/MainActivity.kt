package com.gumioji.hitsuziqiitaviewerapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.gumioji.hitsuziqiitaviewerapp.databinding.ActivityMainBinding
import com.gumioji.hitsuziqiitaviewerapp.ui.fragment.MainFragment


class MainActivity : AppCompatActivity() {
    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
    }

    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // replaceFragment(MainFragment.newInstance(), mBinding.mainContainer.id)

        // 下を拡張したのが上
        val manager = supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        mSearchView = (menu?.findItem(R.id.toolbar_menu_search)?.actionView as SearchView).apply {
            queryHint = "キーワードから探す"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String?): Boolean {
                    val mainFragment = supportFragmentManager.findFragmentById(R.id.main_container)
                    if ((mainFragment is MainFragment) && text != null) {
                        mainFragment.searchRequest(text)
                    }
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    return false
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }
}
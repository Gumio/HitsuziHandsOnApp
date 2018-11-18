package com.gumioji.hitsuziqiitaviewerapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.gumioji.hitsuziqiitaviewerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = this::class.java.simpleName
    }

    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MainFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        mSearchView = (menu?.findItem(R.id.toolbar_menu_search)?.actionView as SearchView).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String?): Boolean {
                    // 検索キーが押下された
                    Log.d(TAG, "submit text: $text")
                    val mainFragment = supportFragmentManager.findFragmentById(R.id.main_container)
                    if (mainFragment is MainFragment && text != null) {
                        mainFragment.searchRequest(text)
                    }
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    // テキストが変更された
                    Log.d(TAG, "change text: $text")
                    return false
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }
}



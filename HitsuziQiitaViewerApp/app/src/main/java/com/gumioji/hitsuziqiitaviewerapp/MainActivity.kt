package com.gumioji.hitsuziqiitaviewerapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
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

        supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment.newInstance()).commit()
    }
}
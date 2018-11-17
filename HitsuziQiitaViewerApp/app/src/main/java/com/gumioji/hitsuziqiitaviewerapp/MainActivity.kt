package com.gumioji.hitsuziqiitaviewerapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gumioji.hitsuziqiitaviewerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MainFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container,fragment )
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

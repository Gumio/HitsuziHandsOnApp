package com.gumioji.hitsuziqiitaviewerapp.ext

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().addToBackStack(null).commit()
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    fragmentManager?.inTransaction{
        replace(frameId, fragment)
    }
}
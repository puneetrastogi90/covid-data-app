package com.covid.covidapp.utils

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.covid.covidapp.R

@Throws(IllegalStateException::class)
fun AppCompatActivity.addFragment(
    fragmentContainerResourceId: Int,
    currentFragment: Fragment?,
    nextFragment: Fragment?,
    commitAllowingStateLoss: Boolean
): Boolean {
    if (currentFragment == null || nextFragment == null) {
        return false
    }
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.setCustomAnimations(
        R.anim.slide_right_in, R.anim.slide_left_out,
        R.anim.slide_left_in, R.anim.slide_right_out
    )
    fragmentTransaction.add(
        fragmentContainerResourceId,
        nextFragment,
        nextFragment.javaClass.simpleName
    )
    fragmentTransaction.addToBackStack(nextFragment.javaClass.simpleName)

    val parentFragment = currentFragment.parentFragment
    fragmentTransaction.hide(parentFragment ?: currentFragment)

    if (!commitAllowingStateLoss) {
        fragmentTransaction.commit()
    } else {
        fragmentTransaction.commitAllowingStateLoss()
    }

    return true
}

@Throws(IllegalStateException::class)
fun AppCompatActivity.replaceFragment(
    fragmentContainerResourceId: Int,
    supportFragmentManager: FragmentManager?,
    nextFragment: Fragment?,
    commitAllowingStateLoss: Boolean
): Boolean {
    if (nextFragment == null || supportFragmentManager == null) {
        return false
    }
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.setCustomAnimations(
        R.anim.slide_right_in, R.anim.slide_left_out,
        R.anim.slide_left_in, R.anim.slide_right_out
    )
    fragmentTransaction.replace(
        fragmentContainerResourceId,
        nextFragment,
        nextFragment.javaClass.simpleName
    )

    if (!commitAllowingStateLoss) {
        fragmentTransaction.commit()
    } else {
        fragmentTransaction.commitAllowingStateLoss()
    }

    return true
}

fun AppCompatActivity.showToast(str: String) = Toast.makeText(this, str, Toast.LENGTH_LONG).show()


package com.covid.covidapp.utils


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.covid.covidapp.base.BaseActivity

/**
 * Adds the Fragment into layout container.
 *
 * @param container               Resource _id of the layout in which Fragment will be added
 * @param currentFragment         Current loaded Fragment to be hide
 * @param nextFragment            New Fragment to be loaded into container
 * @param requiredAnimation       true if screen transition animation is required
 * @param commitAllowingStateLoss true if commitAllowingStateLoss is needed
 * @return true if new Fragment added successfully into container, false otherwise
 * @throws ClassCastException    Throws exception if getActivity() is not an instance of BaseActivity
 * @throws IllegalStateException Exception if Fragment transaction is invalid
 */
@Throws(ClassCastException::class, IllegalStateException::class)
fun Fragment.addFragment(
    container: Int,
    currentFragment: Fragment,
    nextFragment: Fragment,
    commitAllowingStateLoss: Boolean
): Boolean {

    return if (activity != null) {
        (activity as AppCompatActivity).addFragment(
            container,
            currentFragment,
            nextFragment,
            commitAllowingStateLoss
        )

    } else false
}

@Throws(IllegalStateException::class)
fun Fragment.replaceFragment(
    container: Int,
    fragmentManager: FragmentManager,
    nextFragment: Fragment,
    commitAllowingStateLoss: Boolean
): Boolean {
    return if (activity != null) {
        (activity as BaseActivity).replaceFragment(
            container,
            fragmentManager,
            nextFragment,
            commitAllowingStateLoss
        )

    } else false
}
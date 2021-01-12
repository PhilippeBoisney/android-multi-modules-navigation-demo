package com.backmarket.pocnavigation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.backmarket.pocnavigation.home.model.MainViewModel
import com.backmarket.pocnavigation.navigation.Navigable
import com.backmarket.pocnavigation.navigation.direction.NavDirection
import com.backmarket.pocnavigation.navigation.dsl.doWhen
import com.backmarket.pocnavigation.navigation.ktx.requireFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), Navigable {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViewModel()
        if (savedInstanceState == null) showFragment(MainFragment.newInstance())
    }

    override fun navigateTo(direction: NavDirection) {
        direction.doWhen {
            isFragment {
                showFragment(requireFragment(), true)
            }
            otherDirection { super.navigateTo(direction) }
        }
    }

    private fun bindViewModel() = viewModel.run {
        observeNavigation()
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.commit {
            val tag = fragment.tag
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            if (addToBackStack) addToBackStack(tag)
            replace(R.id.content, fragment, tag)
        }
    }
}

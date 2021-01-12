package com.backmarket.pocnavigation.fragment_feature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.backmarket.pocnavigation.fragment_feature.model.FeatureFragmentViewModel
import com.backmarket.pocnavigation.navigation.Navigable
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureFragmentDirection
import com.backmarket.pocnavigation.navigation.ktx.requireNavParam
import kotlinx.android.synthetic.main.fragment_feature.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FeatureFragment : Fragment(R.layout.fragment_feature), Navigable {

    private val viewModel by viewModel<FeatureFragmentViewModel> {
        parametersOf(requireNavParam<FeatureFragmentDirection.Params>())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        bindViews()
    }

    private fun bindViewModel() = viewModel.run {
        observeNavigation()
    }

    private fun bindViews() {
        btnBack.setOnClickListener {
            viewModel.onUserClicksOnBack()
        }
    }

    companion object {
        fun newInstance() = FeatureFragment()
    }
}

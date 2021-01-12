package com.backmarket.pocnavigation.activity_feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.backmarket.pocnavigation.activity_feature.model.FeatureViewModel
import com.backmarket.pocnavigation.core.lifecycleOwner
import com.backmarket.pocnavigation.core.observeNonNull
import com.backmarket.pocnavigation.navigation.Navigable
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureActivityDirection
import com.backmarket.pocnavigation.navigation.ktx.requireNavParam
import kotlinx.android.synthetic.main.activity_feature.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FeatureActivity : AppCompatActivity(), Navigable {

    private val viewModel by viewModel<FeatureViewModel> {
        parametersOf(requireNavParam<FeatureActivityDirection.Params>())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)
        configureToolbar()
        bindViewModel()
        bindViews()
    }

    private fun bindViewModel() = viewModel.run {
        observeNavigation()
        isOpenForResult.observeNonNull(lifecycleOwner) {
            btnFinishWithResult.isVisible = it
            btnFinish.isVisible = !it
        }
    }

    private fun bindViews() {
        btnFinish.setOnClickListener {
            viewModel.onUserClicksOnFinish()
        }
        btnFinishWithResult.setOnClickListener {
            viewModel.onUserClicksOnFinishWithResult()
        }
        btnBack.setOnClickListener {
            viewModel.onUserClicksOnBack()
        }
    }

    private fun configureToolbar() = toolbar.run {
        setSupportActionBar(this)
        setNavigationOnClickListener { viewModel.onUserClicksOnBack() }
        supportActionBar?.run {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
    }
}

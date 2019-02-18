package com.borderdev.trocaodisco.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.borderdev.presentation.common.BasePresenter
import com.borderdev.presentation.splash.SplashPresenter
import com.borderdev.presentation.splash.SplashView
import com.borderdev.trocaodisco.R
import com.borderdev.trocaodisco.ui.common.BaseActivity
import org.kodein.di.Kodein
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SplashActivity : BaseActivity<SplashView>(), SplashView {

    override val kodein: Kodein by closestKodein()

    override val layoutId: Int = R.layout.activity_splash
    override val presenter: SplashPresenter by instance()

    override fun setPresenter() {
        presenter.attachView(this)
    }

    override fun onCreate() {
    }

    override fun showMainContent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showError(error: Throwable) {
        Log.e("XABLAU", "Error: ${error.message}")
    }


}

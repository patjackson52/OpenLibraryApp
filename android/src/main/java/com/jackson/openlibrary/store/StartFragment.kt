package com.jackson.openlibrary.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackson.openlibrary.OpenLibraryApp
import com.jackson.openlibrary.R
import com.willowtreeapps.common.ui.StartPresenter
import com.willowtreeapps.common.ui.StartView
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class StartFragment : BaseLibraryViewFragment<StartPresenter>(), CoroutineScope, StartView {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override lateinit var presenter: StartPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        btn_start.setOnClickListener {
//            presenter.startGame()
//        }
//        btn_settings.setOnClickListener {
//            presenter.settingsTapped()
//        }
    }

    override fun onResume() {
        super.onResume()
        OpenLibraryApp.gameEngine().attachView(this)
        presenter.startGame()
    }

    override fun onPause() {
        super.onPause()
        OpenLibraryApp.gameEngine().detachView(this)
    }

    override fun hideLoading() {
        loading_spinner.visibility = View.GONE
    }

    override fun showLoading() {
        loading_spinner.visibility = View.VISIBLE
    }

    override fun showError(msg: String) {
        txt_error.text = msg
    }
}

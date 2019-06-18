package com.jackson.openlibrary.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.willowtreeapps.common.Logger
import com.willowtreeapps.common.ui.Presenter
import com.willowtreeapps.common.ui.View
import com.jackson.openlibrary.OpenLibraryApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class BaseLibraryViewFragment<TPresenter: Presenter<*>>: Fragment(), CoroutineScope, View<TPresenter> {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override lateinit var presenter: TPresenter
    private var viewRecreated: Boolean = false

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null)
            Logger.d("savedInstanceState == null")
        else {
            Logger.d("savedInstanceState != null")
            viewRecreated = true
        }
    }

    override fun onResume() {
        super.onResume()
        OpenLibraryApp.gameEngine().attachView(this)
        if (viewRecreated) {
            presenter.recreateView()
        }
    }

    override fun onPause() {
        super.onPause()
        OpenLibraryApp.gameEngine().detachView(this)
    }
}

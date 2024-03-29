package org.reduxkotlin.readinglist.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.reduxkotlin.readinglist.MainActivity
import org.reduxkotlin.readinglist.common.ui.UiActions
import org.reduxkotlin.readinglist.common.ui.CompletedView
import kotlinx.android.synthetic.main.fragment_completed.*
import kotlinx.android.synthetic.main.fragment_reading_list.loading_spinner
import kotlinx.android.synthetic.main.fragment_reading_list.txt_error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.reduxkotlin.readinglist.R
import org.reduxkotlin.rootDispatch
import kotlin.coroutines.CoroutineContext

class CompletedFragment : BaseLibraryViewFragment<CompletedView>(), CoroutineScope, CompletedView {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val adapter = BooksAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_completed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        completedRecycler.adapter = adapter
        completedRecycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showFab()
        rootDispatch(UiActions.CompletedListShown())
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

    override fun showBooks(books: List<Any>) {
        adapter.setBooks(books)
    }
}

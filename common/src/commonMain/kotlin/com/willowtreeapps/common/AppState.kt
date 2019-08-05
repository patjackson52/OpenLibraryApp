package com.willowtreeapps.common

import com.willowtreeapps.common.middleware.Screen
import com.willowtreeapps.common.repo.Book
import kotlin.reflect.KProperty0


data class AppState(val isLoadingItems: Boolean = false,
                    val searchBooks: List<Book> = listOf(),
                    val selectedBook: Book? = null,
                    val errorLoadingItems: Boolean = false,
                    val readingList: Set<Book> = setOf(),
                    val completedList: Set<Book> = setOf(),
                    //experimental
                    val currentList: KProperty0<Set<Book>>? = null,
                    val errorMsg: String = "",
                    val currentScreen: Screen = Screen.READING_LIST,
                    val settings: UserSettings = UserSettings.defaults()) {
    companion object {
        val INITIAL_STATE = AppState()
    }


    fun currentSearchIndex() = currentList?.invoke()?.indexOf(selectedBook) ?: 0
}

data class UserSettings(val numQuestions: Int,
                        val isWillowTree: Boolean = false,
                        val microphoneMode: Boolean) {
    companion object {
        fun defaults() = UserSettings(3,
                microphoneMode = false)
    }
}

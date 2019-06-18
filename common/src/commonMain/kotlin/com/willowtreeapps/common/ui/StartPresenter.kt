package com.willowtreeapps.common.ui

import com.willowtreeapps.common.*
import org.reduxkotlin.SelectorSubscriberFn


class StartPresenter(private val engine: GameEngine,
                     private val networkThunks: NetworkThunks) : Presenter<StartView>() {
    override fun recreateView() {
        //no-op
    }

    override fun makeSubscriber() = SelectorSubscriberFn<AppState>(engine.appStore) {
        withSingleField({ it.isLoadingItems }) {
            if (state.isLoadingItems) {
                view?.showLoading()
            } else {
                view?.hideLoading()
            }
        }

        withSingleField({ it.errorLoadingItems }) {
            view?.showError(state.errorMsg)
        }
    }

    fun startGame() {
        engine.dispatch(Actions.ResetGameStateAction())
        engine.dispatch(networkThunks.fetchBooks("oscar wilde"))
    }

    fun settingsTapped() {
        engine.dispatch(Actions.SettingsTappedAction())
    }
}
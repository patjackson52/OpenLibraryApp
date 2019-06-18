package com.willowtreeapps.common.repo

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.willowtreeapps.common.repo.LocalStorageSettingsRepository.Keys.*

expect fun userSettings(context: Any? = null): Settings

class LocalStorageSettingsRepository(private val settings: Settings) {

    var numRounds: Int
        get() = settings.getInt(NUM_ROUNDS.toString(), 4)
        set(numRounds) {
            settings[NUM_ROUNDS.toString()] = numRounds
        }
    var microphoneMode: Boolean
        get() = settings.getBoolean(MICROPHONE_MODE.toString(), false)
        set(categoryId) {
            settings[MICROPHONE_MODE.toString()] = categoryId
        }

    enum class Keys {
        NUM_ROUNDS,
        CATEGORY_ID,
        MICROPHONE_MODE
    }
}
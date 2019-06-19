package com.jackson.openlibrary

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.willowtreeapps.common.Logger
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.Screen
import java.lang.Exception

/**
 * Android implementation of Navigator interface.  This will load the appropriate Activity or Fragment
 * using Android Navigation component.  Utilizes ActivityLifecycleCallbacks to keep reference of the current
 * Activity.
 */
class AndroidNavigator : Navigator, Application.ActivityLifecycleCallbacks {


    private var currentActivity: AppCompatActivity? = null
    private var cachedNavigationScreen: Screen? = null

    //TODO consider using current screen & destination screen to determine routing & animation
    override fun goto(screen: Screen) {
        if (currentActivity == null) {
            cachedNavigationScreen = screen
        } else {
//            val navController = currentActivity!!.findNavController(R.id.nav_host_fragment)
            when (screen) {
//                Screen.QUESTION -> navController.navigate(R.id.action_startScreen_to_questionScreen)
//                Screen.GAME_COMPLETE -> navController.navigate(R.id.action_questionScreen_to_resultsFragment)
                Screen.BOOK_DETAILS -> currentActivity?.startActivity(Intent(currentActivity, DetailsActivity::class.java))
//                Screen.START -> navController.navigate(R.id.action_resultsFragment_to_startScreen)
                else -> throw IllegalArgumentException("Screen $screen is not handled in AndroidNavigator")
            }
        }
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        if (cachedNavigationScreen != null) {
            goto(cachedNavigationScreen!!)
            cachedNavigationScreen = null
        }
        attachActivity(activity)
    }

    private fun attachActivity(activity: Activity?) {
        try {
            currentActivity = activity as AppCompatActivity?
        } catch (e: Exception) {
            Logger.d("Exception casting activity to AppCompatActivity.  $e")
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        attachActivity(activity)
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
        if (activity == currentActivity) {
            currentActivity = null
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }
}
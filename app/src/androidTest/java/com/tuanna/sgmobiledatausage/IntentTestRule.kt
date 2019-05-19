package com.tuanna.sgmobiledatausage

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.intent.rule.IntentsTestRule

class MyIntentsTestRule<T : Activity>(clazz: Class<T>) : IntentsTestRule<T>(clazz, false, false) {

    fun launchActivity(): T {
        return super.launchActivity(null)
    }

    override fun launchActivity(intent: Intent?): T {
        return super.launchActivity(intent)
    }

    fun <A : Activity> checkActivityLaunched(activity: Class<A>) {
        intended(hasComponent(activity.name))
    }

    fun <V> checkIntentHasExtra(key: String, value: V) {
        intended(hasExtra<V>(key, value))
    }
}
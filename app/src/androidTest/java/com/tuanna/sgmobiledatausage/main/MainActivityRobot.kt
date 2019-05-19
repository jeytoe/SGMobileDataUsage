package com.tuanna.sgmobiledatausage.main

import android.content.res.Resources
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.view.View
import com.tuanna.sgmobiledatausage.R
import com.tuanna.sgmobiledatausage.main.RecyclerViewMatcher.Companion.withRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class MainActivityRobot {

    fun launchesActivity(rule: IntentsTestRule<MainActivity>): MainActivityRobot {
        rule.launchActivity(null)
        return this
    }

    fun seesItemAtPosition(message: String, position: Int): MainActivityRobot {
        onView(withId(R.id.dataListView)).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(
            withRecyclerView(R.id.dataListView)
                .atPositionOnView(position, R.id.message)
        ).check(matches(allOf<View>(isDisplayed(), withText(message))))

        return this
    }

    fun seesWarningIconAtPosition(position: Int): MainActivityRobot {
        onView(withId(R.id.dataListView)).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(
            withRecyclerView(R.id.dataListView)
                .atPositionOnView(position, R.id.icon)
        ).check(matches(isDisplayed()))

        return this
    }

    fun doesNotSeeWarningIconAtPosition(position: Int): MainActivityRobot {
        onView(withId(R.id.dataListView)).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(
            withRecyclerView(R.id.dataListView)
                .atPositionOnView(position, R.id.icon)
        ).check(matches(not(isDisplayed())))

        return this
    }

    fun clicksOnIconAtPosition(position: Int): MainActivityRobot {
        onView(withId(R.id.dataListView)).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(
            withRecyclerView(R.id.dataListView)
                .atPositionOnView(position, R.id.icon)
        ).check(matches(isDisplayed()))
            .perform(click())
        return this
    }

    fun seesDialogMessage(message: String): MainActivityRobot{
        onView(withText(message))
            .check(matches(isDisplayed()))
        return this
    }

    fun clicksOnDialogsOkayButton(): MainActivityRobot {
        onView(withText("OK"))
            .check(matches(isDisplayed()))
            .perform(click())
        return this
    }
}

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    companion object {
        fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {

            return RecyclerViewMatcher(recyclerViewId)
        }
    }

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            internal var resources: Resources? = null

            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (this.resources != null) {
                    try {
                        idDescription = this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        idDescription = String.format("%s (resource name not found)", recyclerViewId)
                    }

                }

                description.appendText("with id: $idDescription")
            }

            public override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                if (recyclerView == null
                    || recyclerView.id != recyclerViewId
                    || null == recyclerView.findViewHolderForAdapterPosition(position)
                ) {
                    return false
                }

                val childView = recyclerView.findViewHolderForAdapterPosition(position).itemView

                if (targetViewId == -1) {
                    return view === childView
                } else {
                    val targetView = childView.findViewById<View>(targetViewId)
                    return view === targetView
                }
            }
        }
    }
}
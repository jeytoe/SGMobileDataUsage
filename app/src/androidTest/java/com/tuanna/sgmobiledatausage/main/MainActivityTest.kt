package com.tuanna.sgmobiledatausage.main

import com.tuanna.sgmobiledatausage.MyIntentsTestRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = MyIntentsTestRule(MainActivity::class.java)

    private var robot = MainActivityRobot()

    @Test
    fun activityTest() {
        robot
            .launchActivity(activityRule)
            .seeItemAtPosition("Data usage of 2004 is 0.00093 PB", 0)
            .seeItemAtPosition("Data usage of 2005 is 0.00277 PB", 1)
            .seeItemAtPosition("Data usage of 2006 is 0.00714 PB", 2)
            .seeItemAtPosition("Data usage of 2007 is 0.19715 PB", 3)
            .seeItemAtPosition("Data usage of 2008 is 1.54372 PB", 4)
    }
}
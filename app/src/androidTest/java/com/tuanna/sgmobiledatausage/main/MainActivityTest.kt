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
            .launchesActivity(activityRule)
            .seesItemAtPosition("Data usage of 2004 is 0.00093 PB", 0)
            .seesItemAtPosition("Data usage of 2005 is 0.00277 PB", 1)
            .seesItemAtPosition("Data usage of 2006 is 0.00714 PB", 2)
            .seesItemAtPosition("Data usage of 2007 is 0.19715 PB", 3)
            .seesItemAtPosition("Data usage of 2008 is 1.54372 PB", 4)
            .doesNotSeeWarningIconAtPosition(0)
            .doesNotSeeWarningIconAtPosition(1)
            .doesNotSeeWarningIconAtPosition(2)
            .doesNotSeeWarningIconAtPosition(3)
            .doesNotSeeWarningIconAtPosition(4)
            .doesNotSeeWarningIconAtPosition(5)
            .doesNotSeeWarningIconAtPosition(6)
            .seesWarningIconAtPosition(7)
            .doesNotSeeWarningIconAtPosition(8)
            .seesWarningIconAtPosition(9)
            .doesNotSeeWarningIconAtPosition(10)
            .seesWarningIconAtPosition(11)
            .clicksOnIconAtPosition(7)
            .seesDialogMessage("2011 saw a decrease in volume data")
            .clicksOnDialogsOkayButton()
    }
}
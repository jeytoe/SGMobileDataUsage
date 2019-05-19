package com.tuanna.sgmobiledatausage.main

import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageViewModelFactory
import com.tuanna.sgmobiledatausage.network.QuarterRecord
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

import org.junit.Before

class MobileDataUsageViewModelFactoryTest {

    private lateinit var subject: MobileDataUsageViewModelFactory;

    @Before
    fun setUp() {
        subject = MobileDataUsageViewModelFactory()
    }

    @Test
    fun getViewModels_inputIsNull_returnsEmptyList() {
        val actual = subject.getViewModels(null)
        assertThat(actual).isEmpty()
    }

    @Test
    fun getViewModels_inputIsEmpty_returnsEmptyList() {
        val actual = subject.getViewModels(emptyList())
        assertThat(actual).isEmpty()
    }

    @Test
    fun getViewModels_returnsViewModels() {
        val actual = subject.getViewModels(listOf(
            QuarterRecord(0.011, "2017-Q1"),
            QuarterRecord(0.022, "2017-Q2"),
            QuarterRecord(0.033, "2017-Q3"),
            QuarterRecord(0.044, "2017-Q4"),
            QuarterRecord(0.055, "2018-Q1")
        ))
        assertThat(actual.size).isEqualTo(2)
        assertThat(actual[0].year).isEqualTo(2017)
        assertThat(actual[0].total).isEqualTo(0.11)
        assertThat(actual[1].year).isEqualTo(2018)
        assertThat(actual[1].total).isEqualTo(0.055)
    }

    @Test
    fun getViewModels_returnsViewModelsWithWarningIcon() {
        val actual = subject.getViewModels(listOf(
            QuarterRecord(0.011, "2017-Q1"),
            QuarterRecord(0.022, "2017-Q2"),
            QuarterRecord(0.033, "2017-Q3"),
            QuarterRecord(0.032, "2017-Q4"),
            QuarterRecord(0.055, "2018-Q1")
        ))
        assertThat(actual.size).isEqualTo(2)
        assertThat(actual[0].year).isEqualTo(2017)
        assertThat(actual[0].total).isEqualTo(0.098)
        assertThat(actual[0].shouldShowIcon).isTrue()
        assertThat(actual[1].year).isEqualTo(2018)
        assertThat(actual[1].total).isEqualTo(0.055)
        assertThat(actual[1].shouldShowIcon).isFalse()
    }
}
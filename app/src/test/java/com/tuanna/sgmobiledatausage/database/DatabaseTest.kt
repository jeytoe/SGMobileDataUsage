package com.tuanna.sgmobiledatausage.database

import com.nhaarman.mockito_kotlin.verify
import com.tuanna.sgmobiledatausage.database.entity.QuarterRecordEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DatabaseTest {

    @InjectMocks
    lateinit var subject: RecordRepository

    @Mock
    lateinit var daoService: DaoService

    @Test
    fun getAllRecords() {
        `when`(daoService.getAllRecords()).thenReturn(listOf(mock(QuarterRecordEntity::class.java)))

        val actual = subject.getAllRecords()

        verify(daoService).getAllRecords()
        assertThat(actual.size).isEqualTo(1)
    }

    @Test
    fun insertRecords() {
        val input = listOf(mock(QuarterRecordEntity::class.java))
        subject.updateRecords(input)

        verify(daoService).deleteAllRecords()
        verify(daoService).insertRecords(input)
    }
}
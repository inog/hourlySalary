package de.ingoreschke.hourlysalary

import org.junit.Assert.assertEquals
import org.junit.Test


class SalaryIncreaseInteractorTest {
    val salaryIncreaseInteractor = SalaryIncreaseInteractor()

    @Test
    fun calcIncreasedSalary() {
        val startSalary = 100.0
        val increasePercentage = 10.0
        val expected = 110.0
        val actual = salaryIncreaseInteractor.calcIncreasedSalary(startSalary, increasePercentage)
        assertEquals(expected, actual, 0.0)
    }
}
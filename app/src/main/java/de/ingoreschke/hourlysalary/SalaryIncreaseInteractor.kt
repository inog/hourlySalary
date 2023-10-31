package de.ingoreschke.hourlysalary


class SalaryIncreaseInteractor {

    fun calcIncreasedSalary(startSalary: Double, increasePercentage: Double): Double {
        var salaryIncrease = startSalary + (startSalary * increasePercentage / 100.0)
        salaryIncrease = Math.round(salaryIncrease * 100.0) / 100.0
        return salaryIncrease
    }

}


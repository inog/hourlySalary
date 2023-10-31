package de.ingoreschke.hourlysalary

class SalaryIncreasePresenter(private val salaryIncreaseInteractor: SalaryIncreaseInteractor) {

    fun calculateSalaryIncrease(salary: Double, increasePercentage: Int): Double {
        return salaryIncreaseInteractor.calcIncreasedSalary(salary, increasePercentage.toDouble())
    }
}
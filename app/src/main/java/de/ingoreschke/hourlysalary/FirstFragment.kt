package de.ingoreschke.hourlysalary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.ingoreschke.hourlysalary.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var monthly: Int = 0
    private var yearly: Int = 46000
    private var weeklyHours: Double = 37.5
    private var hourlyWage: Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seekBarSalary = view.findViewById<SeekBar>(R.id.seekbar_salary)
        val seekBarSalaryMonthly = view.findViewById<SeekBar>(R.id.seekbar_salary_monthly)
        val seekBarWeeklyHours = view.findViewById<SeekBar>(R.id.seekbar_weekly_hours)

        seekBarSalary.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, value: Int, fromUser: Boolean) {
                yearly = value
                calcMonthly()
                calc()
                updateView(view)
            }
            override fun onStartTrackingTouch(p0: SeekBar) {}
            override fun onStopTrackingTouch(p0: SeekBar) {}
        })

        seekBarSalaryMonthly.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                monthly = progress
                calcYearly()
                calc()
                updateView(view)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
        seekBarWeeklyHours.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                weeklyHours = progress.toDouble()
                calc()
                updateView(view)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun calcMonthly() {
        monthly = yearly / 12
    }

    fun calcYearly() {
        yearly = monthly * 12
    }

    fun calc() {
        hourlyWage = 3 * monthly / 13 / weeklyHours
    }


    fun updateView(view: View) {
        view.findViewById<TextView>(R.id.textview_salary).text = getString(R.string.salary, yearly.toString())
        view.findViewById<TextView>(R.id.textview_salary_monthly).text = getString(R.string.salary_monthly, monthly.toString())
        view.findViewById<TextView>(R.id.textview_weekly_hours).text = getString(R.string.weekly_hours, weeklyHours.toString())
        view.findViewById<TextView>(R.id.textview_hourly_wage).text = getString(R.string.hourly_wage, hourlyWage.toString())
        view.findViewById<SeekBar>(R.id.seekbar_salary).progress = yearly
        view.findViewById<SeekBar>(R.id.seekbar_salary_monthly).progress = monthly
    }
}
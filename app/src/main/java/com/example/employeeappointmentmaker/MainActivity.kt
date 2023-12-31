package com.example.employeeappointmentmaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.employeeappointmentmaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = FragmentAppointmentListView()
        val fragmentCreateAppointment = FragmentCreateAppointment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, fragmentList)
            commit()
        }

        binding.buttonCreate.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, fragmentCreateAppointment)
                addToBackStack(null)
                commit()
            }
        }

    }
}

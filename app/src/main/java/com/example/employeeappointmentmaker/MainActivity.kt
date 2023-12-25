package com.example.employeeappointmentmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isEmpty
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
            Log.e("test", "fragmentCreateAppointment click")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, fragmentCreateAppointment)
                addToBackStack(null)
                commit()
            }
        }

    }
}
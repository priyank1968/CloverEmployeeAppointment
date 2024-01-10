package com.example.employeeappointmentmaker

import AppointmentAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeappointmentmaker.models.Appointment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("appointments.json")
    suspend fun getAppointments(): Response<Map<String, Appointment>>
}

object RetrofitClient {
    private const val BASE_URL = "https://vue-learning-6de8a-default-rtdb.firebaseio.com/dummyAppointmentData/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

class FragmentAppointmentListView : Fragment(R.layout.fragment_appointment_list) {

    private lateinit var appointmentsAdapter: AppointmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and its adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewAppointments)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        appointmentsAdapter = AppointmentAdapter(emptyList()) // Initially, an empty list
        recyclerView.adapter = appointmentsAdapter

        // Make the API call using Retrofit
        fetchAppointments()
    }

    private fun fetchAppointments() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                Log.d("FetchAppointments", "Fetching appointments...")

                val response = RetrofitClient.apiService.getAppointments()

                if (response.isSuccessful) {
                    Log.d("FetchAppointments", "Successful response received")

                    val appointmentsMap = response.body()
                    appointmentsMap?.let { map ->
                        val appointmentsList = map.values.toList()

                        // Find RecyclerView
                        val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerViewAppointments)

                        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
                        val appointmentAdapter = AppointmentAdapter(appointmentsList)
                        recyclerView?.adapter = appointmentAdapter

                        Log.d("FetchAppointments", "Appointments list updated in RecyclerView")
                    }
                } else {
                    Log.e("FetchAppointments", "Unsuccessful response: ${response.code()}")
                    // Handle unsuccessful response here
                }
            } catch (e: Exception) {
                Log.e("FetchAppointments", "Exception: ${e.message}")
                // Handle exceptions or network errors here
            }
        }
    }

}

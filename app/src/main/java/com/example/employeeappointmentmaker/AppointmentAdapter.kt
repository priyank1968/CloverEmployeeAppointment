package com.example.employeeappointmentmaker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeappointmentmaker.models.Appointment

class AppointmentAdapter(private var appointments: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val dateTimeTextView: TextView = itemView.findViewById(R.id.textViewDate)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.titleTextView.text = appointment.title
        holder.dateTimeTextView.text = "${appointment.date}, ${appointment.time}"
        holder.descriptionTextView.text = appointment.description
    }

    override fun getItemCount(): Int {
        return appointments.size
    }

    fun updateAppointments(newAppointmentsList: List<Appointment>) {
        appointments = newAppointmentsList
        notifyDataSetChanged() // Notify the adapter that the dataset has changed
    }
}

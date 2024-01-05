import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeappointmentmaker.databinding.ItemAppointmentBinding
import com.example.employeeappointmentmaker.models.Appointment

class AppointmentAdapter(private var appointments: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    inner class AppointmentViewHolder(private val binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleTextView = binding.textViewTitle
        val dateTimeTextView = binding.textViewDate
        val descriptionTextView = binding.textViewDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAppointmentBinding.inflate(inflater, parent, false)
        return AppointmentViewHolder(binding)
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
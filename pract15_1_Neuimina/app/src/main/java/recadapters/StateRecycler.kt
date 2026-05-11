package recadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pract15_1_neuimina.R

class StateRecycler(private val stateList: List<state>) :
    RecyclerView.Adapter<StateRecycler.StateViewHolder>() {

    class StateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.state_image)
        val title: TextView = itemView.findViewById(R.id.state_title)
        val description: TextView = itemView.findViewById(R.id.state_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_state, parent, false)
        return StateViewHolder(view)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        val item = stateList[position]
        holder.image.setImageResource(item.image_state)
        holder.title.text = item.title
        holder.description.text = item.text_state
    }

    override fun getItemCount(): Int = stateList.size
}
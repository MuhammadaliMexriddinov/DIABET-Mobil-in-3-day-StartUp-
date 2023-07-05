package uz.alpha.qandlidiabetstartup.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.CategoryData


class CategoryAdapter(private val data: List<CategoryData>) :
    RecyclerView.Adapter<CategoryAdapter.Holder>() {


    private var listener: ((CategoryData) -> Unit)? = null

    fun setItemClickListener(block: (CategoryData) -> Unit) {
        listener = block
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.img_item)
        private val title = view.findViewById<TextView>(R.id.txt_item)

        init {
            view.setOnClickListener {
                listener?.invoke(data[absoluteAdapterPosition])
            }
        }

        fun bind() {
            val item = data[adapterPosition]
            image.setImageResource(item.image)
            title.text = item.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = data.size
}
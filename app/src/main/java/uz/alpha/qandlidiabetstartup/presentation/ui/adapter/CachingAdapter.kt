package uz.alpha.qandlidiabetstartup.presentation.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.local.room.DiseaseEntity
import uz.alpha.qandlidiabetstartup.databinding.ItemNoteBinding
import uz.alpha.qandlidiabetstartup.utils.inflate

class CachingAdapter : ListAdapter<DiseaseEntity, CachingAdapter.ViewHolder>(MyDiffUtil) {

    private var listener: ((DiseaseEntity) -> Unit)? = null
    private var deleteListener: ((DiseaseEntity) -> Unit)? = null
    private var updateListener: ((DiseaseEntity) -> Unit)? = null
    private var colors = emptyList<Int>()

    fun setColors(list: List<Int>) {
        colors = list
    }

    fun setListener(block: (DiseaseEntity) -> Unit) {
        listener = block
    }

    fun setDeleteListener(block: (DiseaseEntity) -> Unit) {
        deleteListener = block
    }

    fun setUpdateListener(block: (DiseaseEntity) -> Unit) {
        updateListener = block
    }


    object MyDiffUtil : DiffUtil.ItemCallback<DiseaseEntity>() {
        override fun areItemsTheSame(oldItem: DiseaseEntity, newItem: DiseaseEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DiseaseEntity, newItem: DiseaseEntity): Boolean {
            return oldItem.title == newItem.title
        }

    }

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            listener?.invoke(getItem(absoluteAdapterPosition))

            binding.root.setOnClickListener {
                updateListener?.invoke(getItem(absoluteAdapterPosition))
            }

            binding.btnDelete.setOnClickListener {
                deleteListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind(data: DiseaseEntity) {
            getItem(absoluteAdapterPosition).apply {
                binding.txtTitle.text = title
                binding.txtDate.text = date
                binding.mainLinear.setCardBackgroundColor(colors[data.id % 5])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemNoteBinding.bind(parent.inflate(R.layout.item_note))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }
}
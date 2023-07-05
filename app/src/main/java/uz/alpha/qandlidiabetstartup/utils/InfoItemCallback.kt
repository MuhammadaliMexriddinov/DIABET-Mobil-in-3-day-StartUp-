package uz.alpha.qandlidiabetstartup.utils

import androidx.recyclerview.widget.DiffUtil
import uz.alpha.qandlidiabetstartup.data.CategoryData


class CategoryItemCallback : DiffUtil.ItemCallback<CategoryData>() {
    override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title &&
                oldItem.image == newItem.image
    }
}
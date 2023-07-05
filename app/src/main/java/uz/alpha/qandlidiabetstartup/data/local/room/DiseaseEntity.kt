package uz.alpha.qandlidiabetstartup.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiseaseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date:String
):java.io.Serializable

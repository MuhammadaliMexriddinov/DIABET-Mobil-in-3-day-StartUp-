package uz.alpha.qandlidiabetstartup.presentation.viewmodel

import kotlinx.coroutines.flow.SharedFlow
import java.io.File

interface DRViewModel {

    val openImageFlow : SharedFlow<Unit>
    val  progressFlow :SharedFlow<Boolean>
    val  getImageFlow :SharedFlow<String>
    val  showImageFlow :SharedFlow<String>

    suspend  fun changeImage()

    suspend fun  sendImage(file:File)

    suspend  fun setImage(str: String)

}
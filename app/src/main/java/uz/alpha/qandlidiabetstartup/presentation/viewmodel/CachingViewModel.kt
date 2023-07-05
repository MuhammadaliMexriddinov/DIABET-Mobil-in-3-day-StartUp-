package uz.alpha.qandlidiabetstartup.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.alpha.qandlidiabetstartup.data.local.room.DiseaseEntity

interface CachingViewModel {
    val addScreenLiveData: LiveData<Unit>
    val allnotes: LiveData<List<DiseaseEntity>>
    val deleteNoteLiveData: LiveData<DiseaseEntity>


    val openUpdateLiveData: LiveData<DiseaseEntity>
    fun updateInfo(note: DiseaseEntity)


    fun openAddScreen()
    fun deleteNote(noteEntity: DiseaseEntity)
}
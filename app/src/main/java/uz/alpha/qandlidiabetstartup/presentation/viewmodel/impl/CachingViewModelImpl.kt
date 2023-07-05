package uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.alpha.qandlidiabetstartup.data.local.room.AppDatabase
import uz.alpha.qandlidiabetstartup.data.local.room.DiseaseEntity
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.CachingViewModel

class CachingViewModelImpl : CachingViewModel, ViewModel() {
    private val dao = AppDatabase.getInstance().noteDao()
    override val addScreenLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val allnotes: LiveData<List<DiseaseEntity>> =dao.getAll()
    override val deleteNoteLiveData: MutableLiveData<DiseaseEntity> = MutableLiveData()
    override val openUpdateLiveData: MutableLiveData<DiseaseEntity> = MutableLiveData()




    override fun openAddScreen() {
        addScreenLiveData.postValue(Unit)
    }

    override fun deleteNote(noteEntity: DiseaseEntity) {
        dao.delete(noteEntity)
    }

    override fun updateInfo(note: DiseaseEntity) {
        openUpdateLiveData.postValue(note)
    }

}
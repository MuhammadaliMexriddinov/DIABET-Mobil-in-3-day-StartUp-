package uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import uz.alpha.diabetaithreecategory.utils.hasConnection
import uz.alpha.qandlidiabetstartup.domain.repository.DiabeteRepository
import uz.alpha.qandlidiabetstartup.domain.repository.impl.DiabeteRepositoryImpl
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.DiabetViewModel
import javax.inject.Inject


class DiabetViewModelImpl  : DiabetViewModel, ViewModel(){

    private  val repository =DiabeteRepositoryImpl()
    override val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    override val notConnectionLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val succesLiveData: MutableLiveData<String> = MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun sendData(age: String,
                          gender: String,
                          polyuria: String,
                          polydipsia: String,
                          sudden_weight_loss: String,
                          weakness: String,
                          polyphagia: String,
                          genital_thrush: String,
                          visual_blurring: String,
                          itching: String,
                          irritability: String,
                          delayed_healing: String,
                          partial_paresis: String,
                          muscle_stiffness: String,
                          alopecia: String,
                          obesity: String) {
        progressLiveData.value = true
        if (!hasConnection()) {
            progressLiveData.value = false
            notConnectionLiveData.value = Unit
            return
        }


        viewModelScope.launch {
            repository.sendData(     age,
                gender,
                polyuria,
                polydipsia,
                sudden_weight_loss,
                weakness,
                polyphagia,
                genital_thrush,
                visual_blurring,
                itching,
                irritability,
                delayed_healing,
                partial_paresis,
                muscle_stiffness,
                alopecia,
                obesity).flowOn(Dispatchers.Main).collect {
                it.onSuccess {
                    progressLiveData.value = false
                    succesLiveData.value = it.toString()
                }

                it.onFailure {
                    progressLiveData.value = true
                    errorMessageLiveData.value = "NO INTERNET"
                }
            }
        }
    }
}
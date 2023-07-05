package uz.alpha.qandlidiabetstartup.presentation.viewmodel

import androidx.lifecycle.MutableLiveData


interface DiabetViewModel {


    val errorMessageLiveData: MutableLiveData<String>
    val notConnectionLiveData: MutableLiveData<Unit>
    val succesLiveData: MutableLiveData<String>


    val progressLiveData: MutableLiveData<Boolean>
    fun sendData(
        age: String,
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
        obesity: String
    )
}
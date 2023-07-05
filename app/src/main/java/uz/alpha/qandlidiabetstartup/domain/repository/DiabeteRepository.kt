package uz.alpha.qandlidiabetstartup.domain.repository

import kotlinx.coroutines.flow.Flow


interface DiabeteRepository {
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
    ): Flow<Result<String>>
}
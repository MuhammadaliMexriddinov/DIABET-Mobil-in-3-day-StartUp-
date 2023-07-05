package uz.alpha.qandlidiabetstartup.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import uz.alpha.qandlidiabetstartup.data.remote.api.ApiClient
import uz.alpha.qandlidiabetstartup.data.remote.api.ApiClient2
import uz.alpha.qandlidiabetstartup.domain.repository.DiabeteRepository
import javax.inject.Inject

class DiabeteRepositoryImpl  : DiabeteRepository {


    private val api1 = ApiClient.getMedApi()
    private val api2 = ApiClient2.getMedApi()

    override fun sendData(
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
    ): Flow<Result<String>> = flow<Result<String>> {
        val response = withTimeoutOrNull(1500) {
            val response = api1.register(
                age,
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
                obesity
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.success(it.result))
                }
            }
        }
        if (response != null) {
            // Handle response
        } else {
            val response2 = api2.register(
                age,
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
                obesity
            )

            if (response2.isSuccessful) {
                response2.body()?.let {
                    emit(Result.success(it.result))
                }
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)
}
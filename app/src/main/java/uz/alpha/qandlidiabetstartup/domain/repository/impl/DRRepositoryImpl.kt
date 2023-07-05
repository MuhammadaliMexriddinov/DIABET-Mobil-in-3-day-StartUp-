package uz.alpha.qandlidiabetstartup.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import uz.alpha.qandlidiabetstartup.data.remote.api.ApiClient
import uz.alpha.qandlidiabetstartup.domain.repository.DRRepository
import java.io.File
import javax.inject.Inject


class DRRepositoryImpl  : DRRepository {


    private  val api = ApiClient.getDRApi()


    override fun sendData(file: File): Flow<Result<String>> = flow<Result<String>> {
        val filePart = MultipartBody.Part.createFormData(
            "dr_img",
            file.name,
            RequestBody.create("image/*".toMediaTypeOrNull(), file)
        )

        val responseBody=  api.uploadImage(filePart)
        if (responseBody.isSuccessful)
        {
            emit(Result.success("Success upload"))
        }else
        {
            emit(Result.failure(Throwable(" serverga ulanishda xatolik buldi")))
        }
    }.catch {
        //emit(Result.failure(Throwable(it.message)))
        emit(Result.failure(Throwable(it.message)))
    }.flowOn(Dispatchers.IO)
}


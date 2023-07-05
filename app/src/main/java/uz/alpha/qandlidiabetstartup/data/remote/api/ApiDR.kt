package uz.alpha.qandlidiabetstartup.data.remote.api


import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import uz.alpha.qandlidiabetstartup.data.remote.response.DRResponse

interface ApiDR {

    @Multipart
    @POST("/dr")
    suspend fun uploadImage(@Part image : MultipartBody.Part):Response<DRResponse>

}
package uz.alpha.qandlidiabetstartup.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.alpha.qandlidiabetstartup.data.remote.response.DiabetResponse


interface ApiDiabet {

    @GET("/preddiabet")
    suspend fun register(
        @Query("age") age: String,
        @Query("gender") gender: String,
        @Query("polyuria") polyuria: String,
        @Query("polydipsia") polydipsia: String,
        @Query("sudden_weight_loss") sudden_weight_loss: String,
        @Query("weakness") weakness: String,
        @Query("polyphagia") polyphagia: String,
        @Query("genital_thrush") genital_thrush: String,
        @Query("visual_blurring") visual_blurring: String,
        @Query("itching") itching: String,
        @Query("irritability") irritability: String,
        @Query("delayed_healing") delayed_healing: String,
        @Query("partial_paresis") partial_paresis: String,
        @Query("muscle_stiffness") muscle_stiffness: String,
        @Query("alopecia") alopecia: String,
        @Query("obesity") obesity: String,
    ): Response<DiabetResponse>




}
package uz.alpha.qandlidiabetstartup.data.remote.request

import retrofit2.http.Query

data class DiabetRequest(
    @Query("age") val age: String,
    @Query("gender") val gender: String,
    @Query("polyuria") val polyuria: String,
    @Query("polydipsia") val polydipsia: String,
    @Query("sudden_weight_loss") val sudden_weight_loss: String,
    @Query("weakness") val weakness: String,
    @Query("polyphagia") val polyphagia: String,
    @Query("genital_thrush") val genital_thrush: String,
    @Query("visual_blurring") val visual_blurring: String,
    @Query("itching") val itching: String,
    @Query("irritability") val irritability: String,
    @Query("delayed_healing") val delayed_healing: String,
    @Query("partial_paresis") val partial_paresis: String,
    @Query("muscle_stiffness") val muscle_stiffness: String,
    @Query("alopecia") val alopecia: String,
    @Query("obesity") val obesity: String,
)

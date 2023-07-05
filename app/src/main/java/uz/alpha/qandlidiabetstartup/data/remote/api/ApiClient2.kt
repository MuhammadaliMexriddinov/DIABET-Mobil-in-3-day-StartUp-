package uz.alpha.qandlidiabetstartup.data.remote.api


import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.alpha.qandlidiabetstartup.app.App


object ApiClient2 {


    private val myClient = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(App.context))
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://195.158.16.43:6788/")
        //http://195.158.16.43:6788
        .client(myClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMedApi(): ApiDiabet = retrofit.create(ApiDiabet::class.java)

}


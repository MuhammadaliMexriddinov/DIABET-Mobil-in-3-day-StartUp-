package uz.alpha.qandlidiabetstartup.presentation.ui.screen.pages

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.readystatesoftware.chuck.ChuckInterceptor
import com.tapadoo.alerter.Alerter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.get
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.alpha.diabetaithreecategory.utils.hasConnection
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.local.room.AppDatabase
import uz.alpha.qandlidiabetstartup.data.local.room.DiseaseEntity
import uz.alpha.qandlidiabetstartup.data.remote.api.ApiDR
import uz.alpha.qandlidiabetstartup.databinding.ScreenThirdBinding
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl.DRViewModelImpl
import uz.alpha.qandlidiabetstartup.utils.getCurrentDate

import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit


class ThirdScreen : Fragment(R.layout.screen_third) {

    private val dao = AppDatabase.getInstance().noteDao()
    private var mProfileUri: Uri? = null
    private val binding by viewBinding(ScreenThirdBinding::bind)
    private val viewModel by viewModels<DRViewModelImpl>()
    lateinit var imageUri: Uri

    private val contract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri!!
            binding.img.setImageURI(uri)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()

        if (!hasConnection()) {
            Alerter.create(requireActivity())
                .setText("NO INTERNET")
                .setBackgroundColorRes(R.color.purple_200)
                .setDuration(5000)
                .show()
        }

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

    }

    private fun setUp() {
        binding.apply {
            btnScore.setOnClickListener {
                contract.launch("image/*")
            }

            btnSend.setOnClickListener {
                binding.verfiedProgress.show()
                upload()
            }
        }
    }

    private fun upload() {
        val filesDir = requireContext().filesDir
        val file = File(filesDir, "image.png")
        val inputStream = requireActivity().contentResolver.openInputStream(imageUri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val requestBody = file.asRequestBody("image/".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("dr_img", file.name, requestBody)


        val myClient = OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(requireContext()))
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit =
            Retrofit.Builder()
                .baseUrl("http://195.158.16.43:6788/")
                .client(myClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiDR::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val response = retrofit.uploadImage(part)

            /**
            val json = Gson()
            val new = json.fromJson(response.string() ,DRResponse::class.java)
            binding.tctScore.text=new.result.toString()
             * */

            if (response.body()?.result.toString() == "absence") {
                alertDialogShow(getString(R.string.drno))
                //   dao.insert(DiseaseEntity(0, getString(R.string.drno), getCurrentDate()))
            } else if (response.body()?.result.toString() == "too_high") {
                alertDialogShow(getString(R.string.dryes))
                //    dao.insert(DiseaseEntity(0, getString(R.string.dryes), getCurrentDate()))
            } else if (response.body()?.result.toString() == "low") {
                alertDialogShow(getString(R.string.dr_low))
            } else if (response.body()?.result.toString() == "medium") {
                alertDialogShow(getString(R.string.dr_medium))
            } else if (response.body()?.result.toString() == "high") {
                alertDialogShow(getString(R.string.dr_high))
            }
            binding.verfiedProgress.hide()

        }
    }

    fun alertDialogShow(pos: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.score))
        builder.setMessage(pos)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton(getString(R.string.main)) { dialogInterface, which ->
            findNavController().navigateUp()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


}
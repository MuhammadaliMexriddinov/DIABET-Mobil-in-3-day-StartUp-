package uz.alpha.qandlidiabetstartup.presentation.ui.screen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import uz.alpha.qandlidiabetstartup.BuildConfig
import uz.alpha.qandlidiabetstartup.MainActivity
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.databinding.ScreenOthersBinding
import uz.alpha.qandlidiabetstartup.presentation.ui.dialog.ChangeNameDialog
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.ProfileViewModel
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl.ProfileViewModelImpl
import uz.alpha.qandlidiabetstartup.utils.setLocalImage
import java.util.*

class OthersPage : Fragment(R.layout.screen_others) {


    private val binding: ScreenOthersBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()
    private var mProfileUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.changeNameLiveData.observe(this, changeNameObserver)
        viewModel.changeImageLiveData.observe(this, changeImageObserver)
        viewModel.contactLiveData.observe(this, contactObserver)
        viewModel.supportLiveData.observe(this, supportObserver)
        viewModel.backLiveData.observe(this, backListener)
        viewModel.nameLiveData.observe(this, nameObserver)
        viewModel.imageLiveData.observe(this, imageObserver)
        viewModel.languageLiveData.observe(this, languageObserver)
        viewModel.informationLiveData.observe(this, informationObserver)
        viewModel.shareLiveData.observe(this, shareObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            tvChangeUserName.setOnClickListener {
                viewModel.changeName()
            }
            tvChangeImageIcon.setOnClickListener {
                viewModel.changeImage()
            }
            tvSupportUs.setOnClickListener {
                viewModel.supportClicked()
            }

            tvLanguage.setOnClickListener {
                viewModel.language()
            }

            tvInfo.setOnClickListener {
                viewModel.info()
            }

            tvShare.setOnClickListener {
                viewModel.shareApp()
            }


        }

    }

    private val nameObserver = Observer<String> {
        binding.tvUserName.text = it
    }


    private val imageObserver = Observer<String> {
        if (it == " ") {
            binding.imgUser.setImageResource(R.drawable.user)
        } else {
            binding.imgUser.setLocalImage(Uri.parse(it), true)
        }
    }

    private val changeNameObserver = Observer<Unit> {
        val dialog = ChangeNameDialog(requireContext(), viewModel.nameLiveData.value!!)
        dialog.show()
        dialog.setChangeListener {
            viewModel.setName(it)
        }
    }

    private val backListener = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val languageObserver = Observer<Unit> {
        findNavController().navigate(R.id.langScreen)
    }

    private val informationObserver = Observer<Unit> {
        findNavController().navigate(R.id.appInformationScreen)
    }

    private  val  shareObserver = Observer<Unit>{
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            var shareMessage = "\nLet me recommend you this application\n\n"
            shareMessage =
                """
                            ${shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID}
                            
                            
                            """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            //e.toString();
        }
    }

    private val profileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                viewModel.setImage(uri.toString())
                mProfileUri = uri
                binding.imgUser.setLocalImage(uri, true)
            }
        }

    private val changeImageObserver = Observer<Unit> {
        ImagePicker.with(requireActivity())
            .crop()
            .cropOval()
            .maxResultSize(512, 512, true)
            .provider(ImageProvider.BOTH)
            .createIntentFromDialog {
                profileLauncher.launch(it)
            }

    }


    private val contactObserver = Observer<Unit> {
        val intent =
            Intent(
                Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "muhammadali12052004@gmail.com", null)
            )
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        startActivity(Intent.createChooser(intent, "Choose an Email client :"))
    }

    private val supportObserver = Observer<Unit> {
        goToPlayMarket(requireActivity() as MainActivity)
    }



    fun goToPlayMarket(activity: MainActivity) {
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=uz.alpha.icmedaibuyrakai")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=uz.alpha.mchatautizm")
                )
            )
        }
    }


}
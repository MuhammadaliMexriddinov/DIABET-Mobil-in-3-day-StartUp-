package uz.alpha.qandlidiabetstartup.presentation.ui.screen

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.local.SharedPref
import uz.alpha.qandlidiabetstartup.databinding.ScreenLanguageBinding
import java.util.*

class LangScreen : Fragment(R.layout.screen_language) {
    private val binding by viewBinding(ScreenLanguageBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        boldLangText()
        clickEvents()


    }

    private fun boldLangText(locale: Locale = resources.configuration.locale) = with(binding) {
        txtUz.setTypeface(null, NORMAL)
        txtRu.setTypeface(null, NORMAL)
        when (locale.toLanguageTag()) {
            "uz" -> {
                txtUz.setTypeface(null, BOLD)
            }
            "ru" -> {
                txtRu.setTypeface(null, BOLD)
            }
        }
    }

    private fun clickEvents() = with(binding) {
        btnBack.setOnClickListener {findNavController().navigateUp() }

        l1.setOnClickListener {
            clearAllCheck()
            setLocate("uz")
            binding.txtUz.text = "O'zbekcha"
            binding.txtRu.text = "Ruscha"
            txtUz.setTypeface(null, BOLD)
            binding.txtUz.textSize = 22f
        }

        l2.setOnClickListener {
            clearAllCheck()
            setLocate("ru")
            binding.txtUz.text = "Узбекский"
            binding.txtRu.text = "Русский"
            txtRu.setTypeface(null, BOLD)
            binding.txtRu.textSize = 22f

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

    }

    private var backObserver = androidx.lifecycle.Observer<Unit> {
        findNavController().popBackStack()
    }


    private fun setLocate(language: String) {
        val resources: Resources = resources
        val metric: DisplayMetrics = resources.displayMetrics
        val configuration = resources.configuration
        configuration.locale = Locale(language)
        resources.updateConfiguration(configuration, metric)
        onConfigurationChanged(configuration)
        SharedPref.getInstance()?.language = language
    }

    fun clearAllCheck() {
        binding.txtUz.textSize = 18f
        binding.txtRu.textSize = 18f
        binding.txtUz.setTypeface(null, NORMAL)
        binding.txtRu.setTypeface(null, NORMAL)
    }

}
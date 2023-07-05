package uz.alpha.qandlidiabetstartup.presentation.ui.dialog

import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.local.SharedPref
import uz.alpha.qandlidiabetstartup.databinding.LayoutLanguageBinding
import java.util.*
import javax.inject.Inject


class LanguageDialog :
    BottomSheetDialogFragment() {

    private val binding by viewBinding(LayoutLanguageBinding::bind)
    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = View.inflate(requireContext(), R.layout.layout_language, null)
        view.setBackgroundResource(R.drawable.shapebottomsheet)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boldLangText()
        clickEvents()


        binding.apply {

            btnMenu.setOnClickListener {
                dismiss()
            }
        }

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
        binding.txtUz.setTypeface(null, Typeface.NORMAL)
        binding.txtRu.setTypeface(null, Typeface.NORMAL)
    }

}
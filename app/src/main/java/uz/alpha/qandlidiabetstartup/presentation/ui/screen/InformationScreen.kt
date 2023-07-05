package uz.alpha.qandlidiabetstartup.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.databinding.ScreenInformationBinding

class InformationScreen : Fragment(R.layout.screen_information) {

    private val binding by viewBinding(ScreenInformationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}
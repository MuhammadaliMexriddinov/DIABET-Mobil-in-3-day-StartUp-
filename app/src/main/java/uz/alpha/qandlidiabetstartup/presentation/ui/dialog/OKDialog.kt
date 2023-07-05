package uz.alpha.qandlidiabetstartup.presentation.ui.dialog

import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.CategoryData
import uz.alpha.qandlidiabetstartup.data.local.SharedPref
import uz.alpha.qandlidiabetstartup.databinding.LayoutLanguageBinding
import uz.alpha.qandlidiabetstartup.databinding.LayoutRuxsatBinding
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.MenuPage
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.MenuPageDirections
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl.StartViewModelImpl
import java.util.*

class OKDialog() : BottomSheetDialogFragment() {

    private val binding by viewBinding(LayoutRuxsatBinding::bind)
    private val viewModel by viewModels<StartViewModelImpl>()
    override fun getTheme() = R.style.NoBackgroundDialogTheme

    private var listener: (() -> Unit)? = null

    fun setClickListener(block: () -> Unit) {
        listener = block
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = View.inflate(requireContext(), R.layout.layout_ruxsat, null)
        view.setBackgroundResource(R.drawable.shapebottomsheet)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.openDiabetFlow.onEach {
            delay(500L)
            findNavController().navigate(MenuPageDirections.actionMenuPageToDiabetScreen())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.openFindRiscFlow.onEach {
            delay(500L)
            findNavController().navigate(MenuPageDirections.actionMenuPageToFindRiskScreen())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.openDRFlow.onEach {
            delay(500L)
            findNavController().navigate(MenuPageDirections.actionMenuPageToThirdScreen())
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        binding.apply {

            btnN.setOnClickListener {
                dismiss()
            }
            btnY.setOnClickListener {
                val  fm = MenuPage()
                fm.setClickListener {
                    when(it){
                        0->{
                            viewModel.openDiabetScreen()
                        }
                        1->{
                            viewModel.openFindRiscScreen()
                        }
                        2->{
                            viewModel.openDRScreen()
                        }
                    }
                }
            }
        }

    }


}
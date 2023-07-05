package uz.alpha.qandlidiabetstartup.presentation.ui.screen.bottom

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.CategoryData
import uz.alpha.qandlidiabetstartup.databinding.ScreenStartBinding
import uz.alpha.qandlidiabetstartup.presentation.ui.adapter.CategoryAdapter
import uz.alpha.qandlidiabetstartup.presentation.ui.dialog.LanguageDialog
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.MenuPageDirections
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl.StartViewModelImpl


class StartScreen :
    Fragment(R.layout.screen_start) {

    var fabOpen: Animation? = null
    var fabClose: Animation? = null
    var rotateForward: Animation? = null
    var rotateBackward: Animation? = null
    var isOpen = false
    private val binding by viewBinding(ScreenStartBinding::bind)
    private val viewModel by viewModels<StartViewModelImpl>()
    val adapter = CategoryAdapter(
        listOf(
            CategoryData(0, "DIABET AI", R.drawable.diabet),
            CategoryData(1, "FINDRISC", R.drawable.risk),
            CategoryData(2, "DR AI", R.drawable.skin),
        )
    )


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
            carouselRecyclerview.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            carouselRecyclerview.adapter = adapter

        }

        binding.carouselRecyclerview.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        binding.apply {


            carouselRecyclerview.setItemSelectListener(object :
                CarouselLayoutManager.OnSelected {
                override fun onItemSelected(position: Int) {
                    when (position) {
                        0 -> {
                            adapter.setItemClickListener {
                                viewModel.openDiabetScreen()
                            }
                        }

                        1 -> {
                            adapter.setItemClickListener {
                                viewModel.openFindRiscScreen()
                            }
                        }
                        2 -> {
                            adapter.setItemClickListener {
                                viewModel.openDRScreen()
                            }

                        }
                    }
                }
            })
        }

    }
}
package uz.alpha.qandlidiabetstartup.presentation.ui.screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
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
import uz.alpha.qandlidiabetstartup.databinding.LayoutMenuBinding
import uz.alpha.qandlidiabetstartup.presentation.ui.adapter.CategoryAdapter
import uz.alpha.qandlidiabetstartup.presentation.ui.adapter.MainPagerAdapter
import uz.alpha.qandlidiabetstartup.presentation.ui.dialog.OKDialog
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl.StartViewModelImpl

class MenuPage : Fragment(R.layout.layout_menu) {

    private val binding by viewBinding(LayoutMenuBinding::bind)
    private val viewModel by viewModels<StartViewModelImpl>()
    val adapter = CategoryAdapter(
        listOf(
            CategoryData(0, "DIABET AI", R.drawable.diabetos),
            CategoryData(1, "FINDRISC", R.drawable.find),
            CategoryData(2, "DR AI", R.drawable.dr2),
        )
    )

    private var listener: ((Int) -> Unit)? = null

    fun setClickListener(block: (Int) -> Unit) {
        listener = block
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


            btnInfo.setOnClickListener {
                findNavController().navigate(R.id.informationScreen)
            }

            btnHistory.setOnClickListener {
                findNavController().navigate(R.id.cachingScreen)
            }

            btnSetting.setOnClickListener {
                findNavController().navigate(R.id.othersPage)
            }

            carouselRecyclerview.setItemSelectListener(object :
                CarouselLayoutManager.OnSelected {
                override fun onItemSelected(position: Int) {
                    when (position) {
                        0 -> {
                            adapter.setItemClickListener {
                                alertDialogShow(0)
                            }
                        }

                        1 -> {
                            adapter.setItemClickListener {
                                alertDialogShow(1)
                            }
                        }
                        2 -> {
                            adapter.setItemClickListener {
                                alertDialogShow(2)
                            }

                        }
                    }
                }
            })
        }


    }

    fun alertDialogShow(pos: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.dialogUzbTitle))
        builder.setMessage(getString(R.string.dialogUzb2))
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton(getString(R.string.start)) { dialogInterface, which ->
            when (pos) {
                0 -> {
                    viewModel.openDiabetScreen()
                }
                1 -> {
                    viewModel.openFindRiscScreen()
                }
                2 -> {
                    viewModel.openDRScreen()
                }
            }
        }
        builder.setNegativeButton(getString(R.string.dialogUzbYoq)) { dialogInterface, which ->

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}
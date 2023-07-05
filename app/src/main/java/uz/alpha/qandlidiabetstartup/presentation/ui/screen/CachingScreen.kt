package uz.alpha.qandlidiabetstartup.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.onEach
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.local.room.DiseaseEntity
import uz.alpha.qandlidiabetstartup.databinding.ScreenCachingBinding
import uz.alpha.qandlidiabetstartup.presentation.ui.adapter.CachingAdapter
import uz.alpha.qandlidiabetstartup.presentation.ui.dialog.DeleteDialog
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl.CachingViewModelImpl
import uz.alpha.qandlidiabetstartup.utils.generateColor

class CachingScreen:Fragment(R.layout.screen_caching) {

    private  val binding by viewBinding(ScreenCachingBinding::bind)
    private val viewModel by viewModels<CachingViewModelImpl>()
    private val adapter = CachingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listRV.adapter = adapter
        binding.listRV.layoutManager = GridLayoutManager(requireContext(), 1)

        viewModel.allnotes.observe(viewLifecycleOwner, allnotesObserver)

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        adapter.setDeleteListener {
            val dialog= DeleteDialog()
            dialog.show(childFragmentManager,"")
            dialog.setDeleteListener {
                viewModel.deleteNote(it)
            }
        }


        adapter.setColors(requireContext().generateColor())
    }

    private val allnotesObserver = Observer<List<DiseaseEntity>> {
        if (it.isEmpty()) {
            binding.img.visibility = View.VISIBLE
        } else {
            binding.img.visibility = View.INVISIBLE
        }
        adapter.submitList(it)
    }

}
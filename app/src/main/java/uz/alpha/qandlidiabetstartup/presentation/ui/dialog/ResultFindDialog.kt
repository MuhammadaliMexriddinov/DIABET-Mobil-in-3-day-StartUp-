package uz.alpha.qandlidiabetstartup.presentation.ui.dialog

import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.local.room.AppDatabase
import uz.alpha.qandlidiabetstartup.data.local.room.DiseaseEntity
import uz.alpha.qandlidiabetstartup.databinding.ScreenScoreFindBinding
import uz.alpha.qandlidiabetstartup.utils.Extension
import uz.alpha.qandlidiabetstartup.utils.getCurrentDate
import java.util.*

class ResultFindDialog  : BottomSheetDialogFragment() {

    private val dao = AppDatabase.getInstance().noteDao()
    private val binding by viewBinding(ScreenScoreFindBinding::bind)
    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = View.inflate(requireContext(), R.layout.screen_score_find, null)
        view.setBackgroundResource(R.drawable.shapebottomsheet)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {
            if (Extension.ball<7){
                txt2.text=getString(R.string.find_1)
                dao.insert(DiseaseEntity(0, getString(R.string.find_1), getCurrentDate()))
            }
            if (Extension.ball>=7 &&  Extension.ball<11){
                txt2.text=getString(R.string.find_2)
                dao.insert(DiseaseEntity(0, getString(R.string.find_2), getCurrentDate()))
            }
            if (Extension.ball>12 && Extension.ball<14){
                txt2.text=getString(R.string.find_3)
                dao.insert(DiseaseEntity(0, getString(R.string.find_3), getCurrentDate()))

            }

            if (Extension.ball>15 && Extension.ball<20){
                txt2.text=getString(R.string.find_4)
                dao.insert(DiseaseEntity(0, getString(R.string.find_4), getCurrentDate()))

            }

            if (Extension.ball>20){
                txt2.text=getString(R.string.find_5)
                dao.insert(DiseaseEntity(0, getString(R.string.find_5), getCurrentDate()))
            }

            btnMenu.setOnClickListener {
                findNavController().navigateUp()
            }
        }

    }


}
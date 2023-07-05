package uz.alpha.qandlidiabetstartup.presentation.ui.screen.pages

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import kotlinx.coroutines.launch
import uz.alpha.diabetaithreecategory.utils.hasConnection
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.data.local.room.AppDatabase
import uz.alpha.qandlidiabetstartup.data.local.room.DiseaseEntity
import uz.alpha.qandlidiabetstartup.databinding.ScreenDiabetBinding
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl.DiabetViewModelImpl
import uz.alpha.qandlidiabetstartup.utils.getCurrentDate


class DiabetScreen : Fragment(R.layout.screen_diabet) {

    private val dao = AppDatabase.getInstance().noteDao()
    private val binding by viewBinding(ScreenDiabetBinding::bind)
    private val viewModel by viewModels<DiabetViewModelImpl>()


    var score = 0
    var selectedAnswer = -1
    var index = 0
    var ball: Float = 0f
    var isChecked: Boolean = false
    var i = 0

    private val radioButtons: ArrayList<View> = ArrayList()
    private val scores =
        arrayListOf<String>("", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.progressLiveData.observe(this, progressObserver)


        viewModel.succesLiveData.observe(this) {
            if (it == "positive") {
                alertDialogShow(getString(R.string.d5), R.drawable.checked)
                dao.insert(DiseaseEntity(i++, getString(R.string.d5), getCurrentDate()))
            }

            if (it == "negative") {
                alertDialogShow(getString(R.string.d2), R.drawable.red)
                dao.insert(DiseaseEntity(i++, getString(R.string.d2), getCurrentDate()))
            }


        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {

            findNavController().navigateUp()
        }



        if (!hasConnection()) {
            Alerter.create(requireActivity())
                .setText("NO INTERNET")
                .setBackgroundColorRes(R.color.purple_200)
                .setDuration(5000)
                .show()
        }

        binding.apply {
            numbers.maxValue = 90
            numbers.minValue = 0

            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        initData()
    }

    private val progressObserver = Observer<Boolean> {
        if (it) binding.verfiedProgress.show() else binding.verfiedProgress.hide()
    }

    fun alertDialogShow(txtResponse: String, image: Int) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle(getString(R.string.d1))

        builder.setMessage(txtResponse)
        builder.setIcon(image)

        //performing positive action
        builder.setPositiveButton(getString(R.string.main)) { dialogInterface, which ->
            findNavController().navigateUp()
        }



        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun initData() {
        radioButtons.add(binding.btnYes1)
        radioButtons.add(binding.btnNo1)

        radioButtons.add(binding.btnYes2)
        radioButtons.add(binding.btnNo2)


        radioButtons.add(binding.btnYes3)
        radioButtons.add(binding.btnNo3)

        radioButtons.add(binding.btnYes4)
        radioButtons.add(binding.btnNo4)

        radioButtons.add(binding.btnYes5)
        radioButtons.add(binding.btnNo5)

        radioButtons.add(binding.btnYes6)
        radioButtons.add(binding.btnNo6)

        radioButtons.add(binding.btnYes7)
        radioButtons.add(binding.btnNo7)

        radioButtons.add(binding.btnYes9)
        radioButtons.add(binding.btnNo9)

        radioButtons.add(binding.btnYes10)
        radioButtons.add(binding.btnNo10)

        radioButtons.add(binding.btnYes11)
        radioButtons.add(binding.btnNo11)

        radioButtons.add(binding.btnYes12)
        radioButtons.add(binding.btnNo12)

        radioButtons.add(binding.btnYes13)
        radioButtons.add(binding.btnNo13)

        radioButtons.add(binding.btnYes14)
        radioButtons.add(binding.btnNo14)

        radioButtons.add(binding.btnYes15)
        radioButtons.add(binding.btnNo15)


        radioButtons[0].setOnClickListener {
            scores[0] = "1"
            isChecked = true
            clearAllCheck(0, 1)
            trueCheck(0)
            index = 0
        }


        radioButtons[1].setOnClickListener {
            scores[0] = "0"
            isChecked = true
            clearAllCheck(1, 0)
            trueCheck(1)
            index = 1
        }

        radioButtons[2].setOnClickListener {
            scores[1] = "1"
            isChecked = true
            clearAllCheck(2, 3)
            trueCheck(0)
            index = 0
        }


        radioButtons[3].setOnClickListener {
            scores[1] = "0"
            isChecked = true
            clearAllCheck(3, 2)
            trueCheck(1)
            index = 1
        }

        radioButtons[4].setOnClickListener {
            scores[2] = "1"
            isChecked = true
            clearAllCheck(4, 5)
            trueCheck(0)
            index = 0
        }


        radioButtons[5].setOnClickListener {
            scores[2] = "0"
            isChecked = true
            clearAllCheck(5, 4)
            trueCheck(1)
            index = 1
        }

        radioButtons[6].setOnClickListener {
            scores[3] = "1"
            isChecked = true
            clearAllCheck(6, 7)
            trueCheck(0)
            index = 0
        }


        radioButtons[7].setOnClickListener {
            scores[3] = "0"
            isChecked = true
            clearAllCheck(7, 6)
            trueCheck(1)
            index = 1
        }

        radioButtons[8].setOnClickListener {
            scores[4] = "1"
            isChecked = true
            clearAllCheck(8, 9)
            trueCheck(0)
            index = 0
        }


        radioButtons[9].setOnClickListener {
            scores[4] = "0"
            isChecked = true
            clearAllCheck(9, 8)
            trueCheck(1)
            index = 1
        }

        radioButtons[10].setOnClickListener {
            scores[5] = "1"
            isChecked = true
            clearAllCheck(10, 11)
            trueCheck(0)
            index = 0
        }


        radioButtons[11].setOnClickListener {
            scores[5] = "0"
            isChecked = true
            clearAllCheck(11, 10)
            trueCheck(1)
            index = 1
        }

        radioButtons[12].setOnClickListener {
            scores[6] = "1"
            isChecked = true
            clearAllCheck(12, 13)
            trueCheck(0)
            index = 0
        }


        radioButtons[13].setOnClickListener {
            scores[6] = "0"
            isChecked = true
            clearAllCheck(13, 12)
            trueCheck(1)
            index = 1
        }

        radioButtons[14].setOnClickListener {
            scores[7] = "1"
            isChecked = true
            clearAllCheck(14, 15)
            trueCheck(0)
            index = 0
        }


        radioButtons[15].setOnClickListener {
            scores[7] = "0"
            isChecked = true
            clearAllCheck(15, 14)
            trueCheck(1)
            index = 1
        }

        radioButtons[16].setOnClickListener {
            scores[8] = "1"
            isChecked = true
            clearAllCheck(16, 17)
            trueCheck(0)
            index = 0
        }


        radioButtons[17].setOnClickListener {
            scores[8] = "0"
            isChecked = true
            clearAllCheck(17, 16)
            trueCheck(1)
            index = 1
        }

        radioButtons[18].setOnClickListener {
            scores[9] = "1"
            isChecked = true
            clearAllCheck(18, 19)
            trueCheck(0)
            index = 0
        }


        radioButtons[19].setOnClickListener {
            scores[9] = "0"
            isChecked = true
            clearAllCheck(19, 18)
            trueCheck(1)
            index = 1
        }

        radioButtons[20].setOnClickListener {
            scores[10] = "1"
            isChecked = true
            clearAllCheck(20, 21)
            trueCheck(0)
            index = 0
        }


        radioButtons[21].setOnClickListener {
            scores[10] = "0"
            isChecked = true
            clearAllCheck(21, 20)
            trueCheck(1)
            index = 1
        }

        radioButtons[22].setOnClickListener {
            scores[11] = "1"
            isChecked = true
            clearAllCheck(22, 23)
            trueCheck(0)
            index = 0
        }


        radioButtons[23].setOnClickListener {
            scores[11] = "0"
            isChecked = true
            clearAllCheck(23, 22)
            trueCheck(1)
            index = 1
        }

        radioButtons[24].setOnClickListener {
            scores[12] = "1"
            isChecked = true
            clearAllCheck(24, 25)
            trueCheck(0)
            index = 0
        }


        radioButtons[25].setOnClickListener {
            scores[12] = "0"
            isChecked = true
            clearAllCheck(25, 24)
            trueCheck(1)
            index = 1
        }

        radioButtons[26].setOnClickListener {
            scores[13] = "1"
            isChecked = true
            clearAllCheck(26, 27)
            trueCheck(0)
            index = 0
        }


        radioButtons[27].setOnClickListener {
            scores[13] = "0"
            isChecked = true
            clearAllCheck(27, 26)
            trueCheck(1)
            index = 1
        }






        binding.btnScore.setOnClickListener {

            if (!hasConnection()) {
                Alerter.create(requireActivity())
                    .setText("NO INTERNET")
                    .setBackgroundColorRes(R.color.purple_200)
                    .setDuration(5000)
                    .show()
            }

            if (checkIsCorrect()) {

                viewModel.sendData(
                    scores[0],
                    binding.numbers.value.toString(),
                    scores[1],
                    scores[2],
                    scores[3],
                    scores[4],
                    scores[5],
                    scores[6],
                    scores[7],
                    scores[8],
                    scores[9],
                    scores[10],
                    scores[11],
                    scores[12],
                    scores[13],
                    "0"
                )
            } else {
                Alerter.create(requireActivity())
                    .setText(getString(R.string.alerter))
                    .setBackgroundColorRes(R.color.purple_200)
                    .show()
            }



            isChecked = false
        }


    }


    fun trueCheck(position: Int) {
        radioButtons!![position].isClickable = true
    }


    fun clearAllCheck(position1: Int, position2: Int) {
        radioButtons[position1].setBackgroundResource(R.drawable.shape_red)
        radioButtons[position2].setBackgroundResource(R.drawable.shape_default)
    }


    private fun checkIsCorrect(): Boolean {
        for (i in 0..scores.size) {
            if (scores[i].isEmpty()) return false
            if (scores[i].isNotEmpty()) return true
        }
        return true
    }


}
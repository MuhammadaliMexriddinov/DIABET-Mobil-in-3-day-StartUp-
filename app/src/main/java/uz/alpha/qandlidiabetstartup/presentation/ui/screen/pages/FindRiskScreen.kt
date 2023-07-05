package uz.alpha.qandlidiabetstartup.presentation.ui.screen.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.databinding.ScreenFindriskBinding
import uz.alpha.qandlidiabetstartup.presentation.ui.dialog.ResultFindDialog
import uz.alpha.qandlidiabetstartup.utils.Extension


class FindRiskScreen : Fragment(R.layout.screen_findrisk) {


    private val binding by viewBinding(ScreenFindriskBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list1 = arrayOf("male", "female")
        val list2 = arrayListOf("")
        val list3 = arrayOf("HA", "YO`Q")
        val list4 = arrayOf("HA", "YO`Q")
        val list5 = arrayOf("HA", "YO`Q")
        val list6 = arrayOf("HA", "YO`Q")
        val list7 = arrayOf("HA", "YO`Q")


        var txt1 = ""
        var txt2 = ""
        var txt3 = ""
        var txt4 = ""
        var txt5 = ""

        binding.apply {
            numbersJ.minValue = 0
            numbersJ.maxValue = list1.size - 1
            numbersJ.wrapSelectorWheel = false
            numbersJ.displayedValues = list1

            numbersJ.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
            }
        }


        binding.apply {
            numbers.minValue = 0
            numbers.maxValue = 90

            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            numbers.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
            }
        }

        binding.apply {
            numbers2.minValue = 50
            numbers2.maxValue = 120

            numbers2.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
            }
        }


        binding.apply {
            numbers3.minValue = 60
            numbers3.maxValue = 150

            numbers3.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
            }
        }

        binding.apply {
            numbers4.minValue = 0
            numbers4.maxValue = list3.size - 1
            numbers4.wrapSelectorWheel = false
            numbers4.displayedValues = list3

            numbers4.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
                txt1 = list3[newVal]
            }
        }

        binding.apply {
            numbers5.minValue = 0
            numbers5.maxValue = list4.size - 1
            numbers5.wrapSelectorWheel = false
            numbers5.displayedValues = list4

            numbers5.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
                txt2 = list4[newVal]
            }
        }

        binding.apply {
            numbers7.minValue = 0
            numbers7.maxValue = list5.size - 1
            numbers7.wrapSelectorWheel = false
            numbers7.displayedValues = list5

            numbers7.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
                txt3 = list5[newVal]
            }
        }


        binding.apply {
            numbers8.minValue = 0
            numbers8.maxValue = list6.size - 1
            numbers8.wrapSelectorWheel = false
            numbers8.displayedValues = list6

            numbers8.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
                txt3 = list6[newVal]
            }
        }

        binding.apply {
            numbers9.minValue = 0
            numbers9.maxValue = list7.size - 1
            numbers9.wrapSelectorWheel = false
            numbers9.displayedValues = list7

            numbers9.setOnValueChangedListener { _, _, newVal ->
                //  textView.text = "Выбранное значение: ${list1[newVal]}"
                txt3 = list7[newVal]
            }
        }


        binding.btnScore.setOnClickListener {
            var ball = 0

            binding.apply {

                if (binding.numbers.value > 45 && binding.numbers.value <= 54) ball += 2
                if (binding.numbers.value > 55 && binding.numbers.value <= 64
                ) ball += 3
                if (binding.numbers.value > 65) ball += 4


                if (binding.numbers2.value > 25 && binding.numbers2.value <= 30
                ) ball += 1
                if (binding.numbers2.value > 30) ball += 2


                if (numbers3.value > 80 && numbers3.value < 94) ball += 0
                if ((numbers3.value > 94 && numbers3.value < 102) || (numbers3.value > 80 && numbers3.value < 88)) ball += 3
                if (numbers3.value > 88 && numbers3.value < 102) ball += 4


                if (numbers4.value == 0) ball += 2
                else if (numbers4.value == 1) ball += 0
                if (numbers5.value == 0) ball += 1
                else if (numbers5.value == 1) ball += 0
                if (numbers7.value == 0) ball += 2
                else if (numbers7.value == 1) ball += 0
                if (numbers8.value == 0) ball += 5
                else if (numbers8.value == 1) ball += 0
                if (numbers9.value == 0) ball += 3
                else if (numbers9.value == 1) ball += 0

                Extension.ball = ball


                if (ball==0){
                    Alerter.create(requireActivity())
                        .setText(getString(R.string.alerter))
                        .setBackgroundColorRes(R.color.purple_200)
                        .show()
                }
                else{
                    showResultDialog()
                }
            }


        }


    }

    private fun showResultDialog() {
        val dialog = ResultFindDialog()
        dialog.show(childFragmentManager, "")
    }

}
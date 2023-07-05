package uz.alpha.qandlidiabetstartup.presentation.ui.screen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.alpha.qandlidiabetstartup.R
import uz.alpha.qandlidiabetstartup.databinding.ScreenInfoBinding
import uz.alpha.qandlidiabetstartup.utils.*

class AppInformationScreen :Fragment(R.layout.screen_info) {

    private val binding by viewBinding(ScreenInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickEvents()

    }

    private fun clickEvents() = with(binding) {
        btnBack.setOnClickListener {findNavController().navigateUp()}
        web.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(WEB))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
        fb.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
        insta.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(INSTAGRAM))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
        tg.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(TELEGRAM))
                val pm: PackageManager = requireActivity().packageManager
                if (intent.resolveActivity(pm) != null) {
                    startActivity(intent)
                } else {
                    try {
                        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(TELEGRAM))
                        startActivity(myIntent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(requireContext(), ERROR_MESSAGE, Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }



}
package uz.alpha.qandlidiabetstartup.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.pages.DiabetScreen
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.pages.FindRiskScreen
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.pages.ThirdScreen


class MainTabAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DiabetScreen()
            1 -> FindRiskScreen()
            else-> ThirdScreen()
        }
    }
}
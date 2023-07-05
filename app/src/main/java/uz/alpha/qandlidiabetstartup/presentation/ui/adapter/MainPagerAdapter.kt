package uz.alpha.qandlidiabetstartup.presentation.ui.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.bottom.ProfilPage
import uz.alpha.qandlidiabetstartup.presentation.ui.screen.bottom.StartScreen

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> StartScreen()
        else-> ProfilPage()
    }
}
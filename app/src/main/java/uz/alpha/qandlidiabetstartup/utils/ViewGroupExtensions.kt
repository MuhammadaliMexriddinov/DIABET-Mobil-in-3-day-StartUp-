package uz.alpha.qandlidiabetstartup.utils

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



fun ViewGroup.inflate(resId:Int):View{
    return LayoutInflater.from(this.context).inflate(resId,this,false)
}

const val WEB = "https://ic.tashpmi.uz/"
const val FACEBOOK = "https://www.facebook.com/profile.php?id=100091727285722"
const val INSTAGRAM = "https://instagram.com/catsi_of_tashpmi?igshid=ZDdkNTZiNTM="
const val TELEGRAM = "https://t.me/Retrofit2_portfolio"
const val ERROR_MESSAGE = "You do not have the required software to proceed with your request. "
const val ERROR_BROWSER = "Please install a web browser"


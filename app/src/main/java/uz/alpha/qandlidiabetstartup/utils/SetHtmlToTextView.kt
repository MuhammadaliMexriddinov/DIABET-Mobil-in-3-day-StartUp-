package uz.alpha.qandlidiabetstartup.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.annotation.RequiresApi
import uz.alpha.qandlidiabetstartup.R

import java.text.SimpleDateFormat
import java.util.*

fun TextView.setHtml(value:String){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(value, Html.FROM_HTML_MODE_COMPACT);
    } else {
        this.text = Html.fromHtml(value);
    }
}
fun Long?.format(pattern: String? = "dd/MM/yyyy"): String{
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val dateString = simpleDateFormat.format(this)
    return String.format("Date: %s", dateString)
}
fun String.myLog() {
}

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("SuspiciousIndentation")
fun Context.generateColor():kotlin.collections.List<Int>{
   val colors = listOf(
            this.getColor(R.color.color_bg_note1),
            this.getColor(R.color.color_bg_note2),
            this.getColor(R.color.color_bg_note3),
            this.getColor(R.color.color_bg_note4),
            this.getColor(R.color.color_bg_note5)
        )
        Collections.shuffle(colors)
    return colors
}
package uz.alpha.qandlidiabetstartup.utils

import java.text.SimpleDateFormat
import java.util.*

/**
Mobile Developer
Creator:Mekhriddinov Muhammadali
 */

fun getCurrentDate(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
}
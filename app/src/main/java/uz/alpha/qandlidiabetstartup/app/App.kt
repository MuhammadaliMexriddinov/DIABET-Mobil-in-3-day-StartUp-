package uz.alpha.qandlidiabetstartup.app

import android.app.Application
import android.content.Context
import uz.alpha.qandlidiabetstartup.data.local.room.AppDatabase


class App: Application(){
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        context = this
    }

}
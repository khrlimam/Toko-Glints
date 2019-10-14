package pretest.app.tokoglints

import android.app.Application
import pretest.app.tokoglints.db.ObjectBox

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}
package pretest.app.tokoglints.db

import android.content.Context
import io.objectbox.BoxStore
import pretest.app.tokoglints.db.models.MyObjectBox

object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}
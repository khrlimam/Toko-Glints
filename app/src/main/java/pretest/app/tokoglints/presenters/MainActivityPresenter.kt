package pretest.app.tokoglints.presenters

import io.objectbox.Box
import io.objectbox.android.AndroidScheduler
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import io.objectbox.query.Query
import io.objectbox.reactive.DataSubscription
import pretest.app.tokoglints.behaviors.MainActivityBehavior
import pretest.app.tokoglints.db.ObjectBox
import pretest.app.tokoglints.db.models.Goods
import pretest.app.tokoglints.db.models.Goods_
import java.util.*

class MainActivityPresenter(val behavior: MainActivityBehavior) {

    private lateinit var goodBox: Box<Goods>
    private lateinit var goodsQuery: Query<Goods>
    private lateinit var goodSubscription: DataSubscription

    private fun setupData() {
        goodBox = ObjectBox.boxStore.boxFor()
        goodsQuery = goodBox.query { orderDesc(Goods_.id) }
    }

    val addGood: (String, Int, String) -> Unit = { name: String, qty: Int, supplier: String ->
        val good = Goods(
            name = name,
            qty = qty,
            supplier = supplier,
            date = Date()
        )
        goodBox.put(good)
    }

    fun getData() {
        setupData()
        goodSubscription = goodsQuery.subscribe().on(AndroidScheduler.mainThread())
            .observer {
                behavior.showData(it)
            }
    }
}
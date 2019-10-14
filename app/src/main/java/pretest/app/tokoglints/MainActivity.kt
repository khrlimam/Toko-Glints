package pretest.app.tokoglints

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.objectbox.Box
import io.objectbox.android.AndroidScheduler
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import io.objectbox.query.Query
import io.objectbox.reactive.DataSubscription
import kotlinx.android.synthetic.main.activity_main.*
import pretest.app.tokoglints.adapters.SimpleRecyclerViewAdapter
import pretest.app.tokoglints.db.ObjectBox
import pretest.app.tokoglints.db.models.Goods
import pretest.app.tokoglints.db.models.Goods_
import pretest.app.tokoglints.fragments.AddItemDialogFragment
import pretest.app.tokoglints.utils.LOGGED_IN
import pretest.app.tokoglints.utils.editAppPreference
import pretest.app.tokoglints.utils.gone
import pretest.app.tokoglints.utils.isWritable
import java.util.*

class MainActivity : LoginRequiredActivity() {

    private lateinit var goodBox: Box<Goods>
    private lateinit var goodsQuery: Query<Goods>
    private lateinit var goodSubscription: DataSubscription
    private var mDialogFragment: DialogFragment? = null
    private var goods = mutableListOf<Goods>()
    private val adapter = SimpleRecyclerViewAdapter(goods)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isWritable())
            btnAddItem.gone()

        setupRecyclerView()
        setupData()
    }

    private fun setupRecyclerView() {
        rvItems.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        rvItems.layoutManager = layoutManager
        rvItems.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }

    private fun setupData() {
        goodBox = ObjectBox.boxStore.boxFor()
        goodsQuery = goodBox.query { orderDesc(Goods_.id) }
        goodSubscription = goodsQuery.subscribe().on(AndroidScheduler.mainThread())
            .observer {
                goods.clear()
                goods.addAll(it)
                adapter.notifyDataSetChanged()
            }
    }

    fun showAddItemDialog(view: View) {
        mDialogFragment = AddItemDialogFragment.new(onSaveClick = addGood)
        mDialogFragment?.show(supportFragmentManager, "add_item_dialog")
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

    override fun onBackPressed() {
        editAppPreference().putBoolean(LOGGED_IN, false).commit().also {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        super.onBackPressed()
    }

}

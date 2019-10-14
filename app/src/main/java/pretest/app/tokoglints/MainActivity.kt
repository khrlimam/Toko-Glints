package pretest.app.tokoglints

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import pretest.app.tokoglints.adapters.SimpleRecyclerViewAdapter
import pretest.app.tokoglints.behaviors.MainActivityBehavior
import pretest.app.tokoglints.db.models.Goods
import pretest.app.tokoglints.fragments.AddItemDialogFragment
import pretest.app.tokoglints.presenters.MainActivityPresenter
import pretest.app.tokoglints.utils.LOGGED_IN
import pretest.app.tokoglints.utils.editAppPreference
import pretest.app.tokoglints.utils.gone
import pretest.app.tokoglints.utils.isWritable

class MainActivity : LoginRequiredActivity(), MainActivityBehavior {

    private var mDialogFragment: DialogFragment? = null
    private var goods = mutableListOf<Goods>()
    private val adapter = SimpleRecyclerViewAdapter(goods)
    private val presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isWritable())
            btnAddItem.gone()

        setupRecyclerView()
        presenter.getData()
    }

    private fun setupRecyclerView() {
        rvItems.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        rvItems.layoutManager = layoutManager
        rvItems.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }


    fun showAddItemDialog(view: View) {
        mDialogFragment = AddItemDialogFragment.new(onSaveClick = presenter.addGood)
        mDialogFragment?.show(supportFragmentManager, "add_item_dialog")
    }

    override fun onBackPressed() {
        editAppPreference().putBoolean(LOGGED_IN, false).commit().also {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        super.onBackPressed()
    }

    override fun showData(data: List<Goods>) {
        goods.clear()
        goods.addAll(data)
        adapter.notifyDataSetChanged()
    }
}

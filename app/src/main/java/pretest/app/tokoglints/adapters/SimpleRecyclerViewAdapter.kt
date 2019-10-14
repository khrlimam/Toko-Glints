package pretest.app.tokoglints.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view.view.*
import pretest.app.tokoglints.R
import pretest.app.tokoglints.adapters.SimpleRecyclerViewAdapter.ViewHolder
import pretest.app.tokoglints.db.models.Goods
import java.text.DateFormat

class SimpleRecyclerViewAdapter(private val data: MutableList<Goods>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context)
                .inflate(R.layout.item_view, p0, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(data[p1])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(good: Goods) {
            itemView.apply {
                tvTitle.text = good.name
                val df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
                tvSubtitle.text =
                    "Qty: ${good.qty} - From: ${good.supplier}"
                tvDate.text = df.format(good.date)
            }
        }

    }
}
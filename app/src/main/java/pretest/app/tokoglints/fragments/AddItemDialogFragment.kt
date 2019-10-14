package pretest.app.tokoglints.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import kotlinx.android.synthetic.main.add_items.*
import pretest.app.tokoglints.R

class AddItemDialogFragment : DialogFragment() {

    private var mTitle: String? = null
    private var mOnSaveClick: ((String, Int, String) -> Unit)? = null
    private val mapItemToView = mutableMapOf<String, EditText>()

    companion object Instance {
        fun new(
            onSaveClick: ((name: String, qty: Int, supplier: String) -> Unit)? = null,
            title: String? = null
        ): AddItemDialogFragment {
            val dialog = AddItemDialogFragment()
            dialog.mTitle = title
            dialog.mOnSaveClick = onSaveClick
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerItem("name", etItemName)
        registerItem("qty", etItemQty)
        registerItem("supplier", etSupplierName)

        etItemName.requestFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        mTitle?.apply { toolbar.setTitle(this) } ?: toolbar.setTitle("Add new item")
        toolbar.setNavigationOnClickListener { dismiss() }
        btnSave.setOnClickListener {
            mapItemToView.values.forEach {
                val textInputLayout = it.parent.parent as TextInputLayout
                textInputLayout.error = null
                if (it.text.toString().isEmpty()) textInputLayout.error =
                    "This item can not be empty!"
            }

            if (mapItemToView.values.filter {
                    it.text.toString().isNotEmpty()
                }.size == mapItemToView.size)
                mOnSaveClick?.let { it1 ->
                    it1(
                        etItemName.text.toString(),
                        etItemQty.text.toString().toInt(),
                        etSupplierName.text.toString()
                    )
                }.also {
                    Snackbar.make(addItemForm, "Data successfully added", Snackbar.LENGTH_LONG)
                        .show()
                    mapItemToView.values.forEach {
                        it.text.clear()
                    }
                }

        }
    }

    private fun registerItem(item: String, view: EditText) {
        mapItemToView.put(item, view)
    }

}
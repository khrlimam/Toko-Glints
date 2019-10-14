package pretest.app.tokoglints

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import pretest.app.tokoglints.behaviors.LoginBehavior
import pretest.app.tokoglints.presenters.LoginPresenter
import pretest.app.tokoglints.utils.LOGGED_IN
import pretest.app.tokoglints.utils.LOGGED_USER
import pretest.app.tokoglints.utils.editAppPreference

class LoginActivity : AppCompatActivity(), LoginBehavior {

    private val presenter = LoginPresenter(this)

    override fun onLoginSuccess(username: String) {
        resetError()
        editAppPreference()
            .putBoolean(LOGGED_IN, true)
            .putString(LOGGED_USER, username)
            .commit()
            .apply { if (this) startActivity(Intent(this@LoginActivity, MainActivity::class.java)) }
    }

    private fun resetError() {
        tLayoutUsername.error = null
        tLayoutPassword.error = null
    }

    override fun onLoginFailed() {
        tLayoutUsername.setError("Incorrect username")
        tLayoutPassword.setError("Incorrect password")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUsername.addTextChangedListener(hideErrorWhenTyping(tLayoutUsername))
        etPassword.addTextChangedListener(hideErrorWhenTyping(tLayoutPassword))
    }

    fun login(view: View) {
        presenter.doLogin(etUsername.text.toString(), etPassword.text.toString())
    }

    fun hideErrorWhenTyping(textInputLayout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textInputLayout.error = null
            }

        }
    }
}

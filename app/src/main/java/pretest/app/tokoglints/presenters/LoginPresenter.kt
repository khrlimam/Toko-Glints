package pretest.app.tokoglints.presenters

import pretest.app.tokoglints.behaviors.LoginBehavior
import pretest.app.tokoglints.utils.Auth

class LoginPresenter(private val view: LoginBehavior) {
    fun doLogin(username: String, password: String) {
        Auth.login(username, password).apply {
            if (this) view.onLoginSuccess(username) else view.onLoginFailed()
        }
    }
}
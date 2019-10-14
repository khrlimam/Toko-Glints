package pretest.app.tokoglints.behaviors

interface LoginBehavior {
    fun onLoginSuccess(username: String)
    fun onLoginFailed()
}

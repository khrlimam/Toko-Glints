package pretest.app.tokoglints

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import pretest.app.tokoglints.utils.LOGGED_IN
import pretest.app.tokoglints.utils.appPreference
import pretest.app.tokoglints.utils.loggedIn

abstract class LoginRequiredActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        redirectIfGuest()
    }

    override fun onResume() {
        super.onResume()
        redirectIfGuest()
    }

    private fun redirectIfGuest() {
        if (!loggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
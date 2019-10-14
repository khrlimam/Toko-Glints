package pretest.app.tokoglints.utils

import android.content.Context
import android.view.View
import pretest.app.tokoglints.BuildConfig

const val LOGGED_IN = "LOGGED_IN"
const val LOGGED_USER = "LOGGED_USER"

fun Context.appPreference() = getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
fun Context.editAppPreference() = appPreference().edit()
fun Context.loggedIn() = appPreference().getBoolean(LOGGED_IN, false)
fun Context.getLoggedUser() = appPreference().getString(LOGGED_USER, "")
fun Context.isWritable() = Auth.getUser(getLoggedUser())?.get("access").equals("write", true)
fun View.gone() {
    visibility = View.GONE
}
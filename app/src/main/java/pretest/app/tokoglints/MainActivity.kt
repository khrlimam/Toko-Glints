package pretest.app.tokoglints

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(localClassName, "A.onCreate")
    }

    override fun onPause() {
        super.onPause()
        Log.i(localClassName, "A.onPause")
        if (isFinishing())
            Log.i(localClassName, "Finishing")
        else
            Log.i(localClassName, "in bg")
    }

    override fun onStart() {
        super.onStart()
        Log.i(localClassName, "A.onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(localClassName, "A.onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(localClassName, "A.onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(localClassName, "A.onDestroy")
    }


}

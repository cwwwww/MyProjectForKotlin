package com.example.msi.myprojectforkotlin.base

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.msi.myprojectforkotlin.utils.DisplayManager
import com.wanjian.cockroach.Cockroach

/**
 * Created by MSI on 2018/5/24.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initCockroach()
        DisplayManager.init(this)
    }

    private fun initCockroach() {
        Cockroach.install { thread, throwable ->
            Handler(Looper.getMainLooper()).post {
                try {
                    Log.e("AndroidRuntime", "--->CockroachException:$thread<---", throwable)
                    Toast.makeText(applicationContext, "Exception Happend\n" + thread + "\n" + throwable.toString(),
                            Toast.LENGTH_LONG).show();
//                    Toast.makeText(applicationContext, "应用出现异常！", Toast.LENGTH_LONG).show()
                } catch (e: Throwable) {
                }
            }
        }
    }
}
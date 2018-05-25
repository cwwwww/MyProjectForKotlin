package com.example.msi.myprojectforkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

/**
 * Created by MSI on 2018/5/23.
 */
const val TAG = "cww"

fun <T> Observable<T>.io_main(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}

fun durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    if (minute <= 9) {
        if (second <= 9) {
            return "0${minute}' 0${second}''"
        } else {
            return "0${minute}' ${second}''"
        }
    } else {
        if (second <= 9) {
            return "${minute}' 0${second}''"
        } else {
            return "${minute}' ${second}''"
        }
    }
}

/**
 * 几天前  几小时前
 */
fun timePreFormat(time: Long): String {

    val now = System.currentTimeMillis()
    val pre = now - time//多久前


    val min = pre / 1000 / 60
    if (min < 1) {
        return "刚刚"
    } else if (min < 60) {
        return "" + min + "分钟前"
    } else if (min < 60 * 24) {
        return "" + min / 60 + "小时前"
    } else {
        return "" + min / 60 / 24 + "天前"
    }
}

inline fun <reified T : Activity> Context.toActivityWithSerializable(data: Serializable) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("data", data)
    startActivity(intent)
}

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}
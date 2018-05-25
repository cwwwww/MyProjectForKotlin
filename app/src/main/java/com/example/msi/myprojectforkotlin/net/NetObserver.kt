package com.example.msi.myprojectforkotlin.net

import com.example.msi.myprojectforkotlin.base.BaseActivity
import com.example.msi.myprojectforkotlin.base.BaseFragment
import io.reactivex.observers.DisposableObserver

/**
 * Created by xuekai on 2017/8/23.
 */
 class NetObserver<T> : DisposableObserver<T> {

    var activity: BaseActivity? = null
    var fragment: BaseFragment? = null
    var onSuccess: OnSuccess? = null

    constructor(activity: BaseActivity?) : super() {
        this.activity = activity
        this.onSuccess = onSuccess
    }

    constructor(fragment: BaseFragment?) : super() {
        this.fragment = fragment
        this.onSuccess = onSuccess
    }


    override fun onComplete() {
        activity?.disposable(this)
        fragment?.disposable(this)
    }

    //取消订阅关系
     override fun onError(e: Throwable) {
        activity?.disposable(this)
        fragment?.disposable(this)
    }

    override fun onNext(t: T) {
        onSuccess?.onSuccess()
    }

    interface OnSuccess {
        fun onSuccess()
    }

    override fun onStart() {
        super.onStart()
        activity?.addDisposable(this)
        fragment?.addDisposable(this)
    }
}
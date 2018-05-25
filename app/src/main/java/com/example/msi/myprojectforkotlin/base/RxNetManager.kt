package com.example.msi.myprojectforkotlin.base

import io.reactivex.disposables.Disposable

/**
 * Created by MSI on 2018/5/22.
 */
interface RxNetManager {
    fun disposable(disposable: Disposable)
    fun addDisposable(disposable: Disposable)
}
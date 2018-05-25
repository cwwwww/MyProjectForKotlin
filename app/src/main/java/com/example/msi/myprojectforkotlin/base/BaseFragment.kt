package com.example.msi.myprojectforkotlin.base

import android.support.v4.app.Fragment
import com.example.msi.myprojectforkotlin.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by MSI on 2018/5/22.
 */
var currentFragment = R.id.rb_home
val tabsId = listOf(R.id.rb_home, R.id.rb_category, R.id.rb_hot)

abstract class BaseFragment(tabId: Int) : Fragment(), RxNetManager {
    var tabId = 0

    init {
        this.tabId = tabId
    }

    private val disposables = CompositeDisposable()

    override fun disposable(disposable: Disposable) {
        disposables.remove(disposable)
    }

    override fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }


    open fun setupToolbar(): Boolean {
        if (tabId != currentFragment) {//解决mainactivity fragment切换时，toolbar更新bug（homefragment中recyclerview滚动会更新toolbar，如果不控制，在滚动过程中切换了tab，toolbar会依旧被更新）
            return true
        }
        return false
    }
}
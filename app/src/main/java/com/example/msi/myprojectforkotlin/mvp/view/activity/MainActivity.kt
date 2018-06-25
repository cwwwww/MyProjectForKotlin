package com.example.msi.myprojectforkotlin.mvp.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.msi.myprojectforkotlin.R
import com.example.msi.myprojectforkotlin.base.BaseActivity
import com.example.msi.myprojectforkotlin.base.BaseFragment
import com.example.msi.myprojectforkotlin.base.currentFragment
import com.example.msi.myprojectforkotlin.base.tabsId
import com.example.msi.myprojectforkotlin.mvp.view.frament.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by MSI on 2018/5/22.
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRadio()
    }

    private fun setRadio() {
        rb_home.isChecked = true
        chooseFragment(R.id.rb_home)
        rg_root.setOnCheckedChangeListener { _, checkId -> chooseFragment(checkId) }
    }

    private fun chooseFragment(checkId: Int) {
        currentFragment = checkId
        val beginTransaction = supportFragmentManager.beginTransaction()
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(checkId.toString())
        if (fragment == null) {
            when (checkId) {
                R.id.rb_home -> {
                    beginTransaction.add(R.id.fl_content, HomeFragment(), checkId.toString())
                }
                R.id.rb_hot -> {

                }
                R.id.rb_category -> {

                }
            }
        }

        tabsId.forEach { tab ->
            var varFragment = supportFragmentManager.findFragmentByTag(tab.toString()) as BaseFragment?

            if (tab == checkId) {
                varFragment?.let {
                    varFragment.setupToolbar()
                    beginTransaction.show(it)
                }
            } else {
                varFragment?.let {
                    beginTransaction.hide(it)
                }
            }
        }
        beginTransaction.commit()
    }
}
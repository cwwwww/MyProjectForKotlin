package com.example.msi.myprojectforkotlin.mvp.contract

import com.example.msi.myprojectforkotlin.base.BasePresenter
import com.example.msi.myprojectforkotlin.base.BaseView
import com.example.msi.myprojectforkotlin.bean.HomeBean
import com.example.msi.myprojectforkotlin.bean.Item

/**
 * Created by MSI on 2018/5/23.
 */
interface HomeContract {

    interface IPresenter : BasePresenter {
        /**
         * 刷新数据 或者 第一次请求数据
         * */
        fun requestFirstData()

        /**
         * 请求更多的数据
         * */
        fun requestMoreData()

    }

    interface IView : BaseView<IPresenter> {
        fun setFirstData(homeBean: HomeBean)
        fun setMoreData(itemList: ArrayList<Item>)
        fun onError()
    }
}
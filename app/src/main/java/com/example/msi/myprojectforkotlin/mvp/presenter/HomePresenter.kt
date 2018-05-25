package com.example.msi.myprojectforkotlin.mvp.presenter

import com.example.msi.myprojectforkotlin.base.BasePresenter
import com.example.msi.myprojectforkotlin.bean.HomeBean
import com.example.msi.myprojectforkotlin.mvp.contract.HomeContract
import com.example.msi.myprojectforkotlin.mvp.model.HomeModel

/**
 * Created by MSI on 2018/5/23.
 */
class HomePresenter(view: HomeContract.IView) : HomeContract.IPresenter {
    private val homeView: HomeContract.IView = view
    private var nextPageUrl: String? = null
    private val homeModel: HomeModel by lazy {
        HomeModel()
    }
    private var bannerHomeBean: HomeBean? = null


    override fun requestFirstData() {
        homeModel.loadFirstData()
                .flatMap { homeBean ->
                    bannerHomeBean = homeBean
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                }.subscribe({ homeBean ->
                    nextPageUrl = homeBean.nextPageUrl
                    bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size //这里记录轮播图的长度，在adapter中用

                    //过滤掉banner2item
                    val newItemList = homeBean.issueList[0].itemList
                    newItemList.filter { item -> item.type == "type2" }.forEach { item -> newItemList.remove(item) }


                    bannerHomeBean!!.issueList[0].itemList.addAll(newItemList)
                    homeView.setFirstData(bannerHomeBean!!)
                }, { error ->
                    error.printStackTrace()
                    homeView.onError()
                })
    }

    override fun requestMoreData() {
        nextPageUrl?.let { url ->
            homeModel.loadMoreData(url)
                    .subscribe({ homeBean ->
                        //过滤掉Banner2
                        val newItemList = homeBean.issueList[0].itemList
                        newItemList.filter { it -> it.type == "banner2" }.forEach { it -> newItemList.remove(it) }
                        homeView.setMoreData(newItemList)
                        nextPageUrl = homeBean.nextPageUrl
                    })
        }
    }

}
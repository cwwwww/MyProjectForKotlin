package com.example.msi.myprojectforkotlin.mvp.model

import com.example.msi.myprojectforkotlin.bean.HomeBean
import com.example.msi.myprojectforkotlin.net.NetWorker
import com.example.msi.myprojectforkotlin.utils.io_main
import io.reactivex.Observable

/**
 * Created by MSI on 2018/5/23.
 */
class HomeModel {
    fun loadFirstData(): Observable<HomeBean> {
        return NetWorker.apiService.getFirstHomeData(System.currentTimeMillis()).io_main()
    }

    fun loadMoreData(url: String): Observable<HomeBean> {
        return NetWorker.apiService.getMoreHomeData(url).io_main()
    }
}
package com.example.msi.myprojectforkotlin.bean

/**
 * Created by MSI on 2018/5/22.
 */
data class HotCategory(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)
    data class Tab(val id: Long, val name: String, val apiUrl: String)
}
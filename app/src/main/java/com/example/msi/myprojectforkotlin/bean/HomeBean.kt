package com.example.msi.myprojectforkotlin.bean

/**
 * Created by MSI on 2018/5/22.
 */
data class HomeBean(var issueList: ArrayList<Issue>, val nextPageUrl: String, val nextPublishTime: Long, val newestIssueType: String, val dialog: Any)
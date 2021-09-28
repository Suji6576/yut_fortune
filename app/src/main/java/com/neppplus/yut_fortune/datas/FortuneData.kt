package com.neppplus.yut_fortune.datas

import java.text.SimpleDateFormat
import java.util.*

class FortuneData (
    var saveYut : String,
    var saveFortune : String) {

//    텍스트로 저장할 내용 yy-M-dd 도도도 : 해석내용

    fun getFileFormatData() : String{

        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yy-M-dd", Locale("ko", "KR"))
        val saveDate = dateFormat.format(date)

        return "${saveDate} ${saveYut} : ${saveFortune}"
    }
}
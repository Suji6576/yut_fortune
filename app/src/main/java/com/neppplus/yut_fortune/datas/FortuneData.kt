package com.neppplus.yut_fortune.datas

import java.text.SimpleDateFormat
import java.util.*

class FortuneData (
    var saveYut : String,
    var saveFortune : String) {

//    텍스트로 저장할 내용 yy-M-dd [카테고리] 질문 : 윷점결과
//    21/09/29 | [직업운] | 취업이 잘 될까? | 환경을 바꾸면 운이 들어올 수 있다  이런식으로?

//    날짜 가공 함수.
    fun getFileFormatData() : String{

        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yy-M-dd", Locale("ko", "KR"))
        val saveDate = dateFormat.format(date)

        return "${saveDate} ${saveYut} : ${saveFortune}"
    }
}
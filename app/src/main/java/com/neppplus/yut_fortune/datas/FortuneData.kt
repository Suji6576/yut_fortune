package com.neppplus.yut_fortune.datas

import java.text.SimpleDateFormat
import java.util.*

class FortuneData (
    var saveCategory : String,
    var saveWorry: String,
    var saveFortune : String) {

//    텍스트로 저장할 내용 yy-M-dd [카테고리] 질문 : 윷점결과
//     [직업운] | 취업이 잘 될까? | 환경을 바꾸면 운이 들어올 수 있다 |21-09-29  이런식으로?

//    날짜 가공 함수.

    val saveDate = Calendar.getInstance()

//    날짜변환 양식은, 외부노출X, 나만혼자 사용.(private)
    private val fileDateFormat = SimpleDateFormat("yy-MM-dd")


    fun getFileFormatData() : String {

        return "${saveCategory }|${saveWorry}|${saveFortune}|${fileDateFormat.format(this.saveDate.time)}"
    }

    val saveDateFormatter = SimpleDateFormat("yy/MM/dd")

    fun getFormattedsaveDate() : String {

        return  saveDateFormatter.format((this.saveDate.time))

    }
}
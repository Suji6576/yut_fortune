package com.neppplus.yut_fortune

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.neppplus.yut_fortune.adapters.FortuneAdapter
import com.neppplus.yut_fortune.databinding.ActivityViewFortuneListBinding
import com.neppplus.yut_fortune.datas.FortuneData
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ViewFortuneListActivity() : BaseActivity() {

    lateinit var binding: ActivityViewFortuneListBinding

    val mMyFortuneList = ArrayList<FortuneData>()

    lateinit var mFortuneAdapter : FortuneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_fortune_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        titleTxt.text = "내 운세 목록"

        menuBtn.visibility = View.GONE

//        mMyFortuneList.add(FortuneData("테스트","이거 언제 잘될까요","잘되고있다"))


//        어댑터 초기화
        mFortuneAdapter = FortuneAdapter(mContext, R.layout.view_fortune_list_item, mMyFortuneList)

//        리스트뷰의 어댑터로 연결
        binding.roadListView.adapter = mFortuneAdapter

//        파일을 불러내서, 그 내용을 읽고 -> PhoneNumDate()로 변환 -> 목록에 추가
        readFortuneFromFile()

    }

//    파일에서 저장내용을 불러와 목록에 추가
    fun readFortuneFromFile() {

        val myFile = File(filesDir,"save_fortune.txt")

        val fr = FileReader(myFile)
        val br = BufferedReader(fr)

        while (true){

            val line = br.readLine()

            if (line == ""){

//                읽어온 내용이 없다면 종료처리
                break
            }
//    읽어온 line을 => | 기준으로 분리

            val infos = line.split("|")

//            이름, 폰번만 우선 폰번 데이터로.
            val fortuneData = FortuneData(infos[0], infos[1], infos[2])

//            만들어진 폰번 데이터 목록에 추가
            mMyFortuneList.add(fortuneData)

        }


    }

}
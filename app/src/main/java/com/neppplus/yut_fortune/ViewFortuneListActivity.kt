package com.neppplus.yut_fortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.neppplus.yut_fortune.adapters.FortuneAdapter
import com.neppplus.yut_fortune.databinding.ActivityViewFortuneListBinding
import com.neppplus.yut_fortune.datas.FortuneData
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat

class ViewFortuneListActivity() : BaseActivity() {

    lateinit var binding: ActivityViewFortuneListBinding

    val mFortuneList = ArrayList<FortuneData>()

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
        mFortuneAdapter = FortuneAdapter(mContext, R.layout.view_fortune_list_item, mFortuneList)

//        리스트뷰의 어댑터로 연결
        binding.roadListView.adapter = mFortuneAdapter

//        파일을 불러내서, 그 내용을 읽고 -> PhoneNumDate()로 변환 -> 목록에 추가
        readFortuneFromFile()

    }

    override fun onResume() {
        super.onResume()

//        화면에 올때마다 화면을 새로 읽어주자.
        readFortuneFromFile()

    }

//    파일에서 저장내용을 불러와 목록에 추가
    fun readFortuneFromFile() {

        val myFile = File(filesDir,"save_fortune.txt")

//        처음깔았으면, save_fortune.txt가 없을 예정 => 파일 읽어오기는 막아두자.
    if (!myFile.exists()) {
        Log.d("파일없음", "아직 메모된 내용이 없습니다.")
        return
    }

        val fr = FileReader(myFile)
        val br = BufferedReader(fr)

//        21-09-30 String을 분석하는데 쓰일 양식.
    val sdf = SimpleDateFormat("yy-MM-dd")

//        이 코드는 반복 실행되면 데이터가 누적으로 쌓인다.
//        기존에 있던 데이터는 날리고 -> 새로 데이터 담아주자.
    mFortuneList.clear()

        while (true){

            val line = br.readLine()

            if (line == null){

//                읽어온 내용이 없다면 종료처리
                break
            }
            Log.d("읽어온 한줄", line)

//    읽어온 line을 => | 기준으로 분리

            val infos = line.split("|")

//            이름, 폰번만 우선 폰번 데이터로.
            val fortuneData = FortuneData(infos[0], infos[1], infos[2])

//            만들어진 폰번 데이터 목록에 추가
            mFortuneList.add(fortuneData)

        }


    }

}
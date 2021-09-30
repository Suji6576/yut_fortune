package com.neppplus.yut_fortune

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.yut_fortune.adapters.FortuneAdapter
import com.neppplus.yut_fortune.databinding.ActivityViewFortuneListBinding
import com.neppplus.yut_fortune.datas.FortuneData
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat

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

        mMyFortuneList.add(FortuneData("테스트","이거 언제 잘될까요","잘되고있다"))


//        어댑터 초기화
        mFortuneAdapter = FortuneAdapter(mContext, R.layout.view_fortune_list_item, mMyFortuneList)

        binding.roadListView.adapter = mFortuneAdapter


    }

}
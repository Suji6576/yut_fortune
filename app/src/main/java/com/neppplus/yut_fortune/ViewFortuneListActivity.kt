package com.neppplus.yut_fortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.yut_fortune.adapters.FortuneAdapter
import com.neppplus.yut_fortune.databinding.ActivityViewFortuneListBinding
import com.neppplus.yut_fortune.datas.FortuneData
import java.io.BufferedReader
import java.io.File

class ViewFortuneListActivity : BaseActivity() {

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

        binding.roadRecyclerView.layoutManager = LinearLayoutManager(mContext)

        mFortuneAdapter = FortuneAdapter(mContext, mMyFortuneList)
        binding.roadRecyclerView.adapter = mFortuneAdapter

        val bufferedReader: BufferedReader = File(filesDir,"save_fortune.txt").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        println(inputString)

//        https://www.python2.net/questions-1268056.htm 이 링크 참고하기

    }}
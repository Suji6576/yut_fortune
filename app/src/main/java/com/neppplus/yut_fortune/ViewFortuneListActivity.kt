package com.neppplus.yut_fortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.yut_fortune.adapters.FortuneAdapter
import com.neppplus.yut_fortune.databinding.ActivityViewFortuneListBinding
import com.neppplus.yut_fortune.datas.FortuneData

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

    }
}
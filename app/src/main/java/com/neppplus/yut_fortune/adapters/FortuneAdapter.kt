package com.neppplus.yut_fortune.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.yut_fortune.R
import com.neppplus.yut_fortune.datas.FortuneData

class FortuneAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<FortuneData>) : RecyclerView.Adapter<FortuneAdapter.ViewFortuneHolder>() {

    class ViewFortuneHolder(view: View) :RecyclerView.ViewHolder(view){

        val viewListDateTxt = view.findViewById<TextView>(R.id.viewListDateTxt)
        val viewListCategoryTxt = view.findViewById<TextView>(R.id.viewListCategoryTxt)
        val viewListWorryTxt = view.findViewById<TextView>(R.id.viewListWorryTxt)
        val viewListFortuneTxt = view.findViewById<TextView>(R.id.viewListFortuneTxt)
        val viewListItemLayout = view.findViewById<LinearLayout>(R.id.viewListItemLayout)

        fun setRealData(data: FortuneData){


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewFortuneHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.view_fortune_list_item, parent, false)
        return  ViewFortuneHolder(view)

    }

    override fun onBindViewHolder(holder: ViewFortuneHolder, position: Int) {

        holder.setRealData(mList[position])

        holder.viewListItemLayout.setOnClickListener {

            Toast.makeText(mContext, "리스트 클릭", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = mList.size


}
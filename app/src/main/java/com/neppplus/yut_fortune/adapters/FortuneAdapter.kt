package com.neppplus.yut_fortune.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.neppplus.yut_fortune.R
import com.neppplus.yut_fortune.datas.FortuneData

class FortuneAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<FortuneData>) : ArrayAdapter<FortuneData>(mContext,resId,mList) {

    private val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

//        getView : xml(우리가 만든 xml로 커스텀) -> position에 맞는 데이터를 입혀서 -> 리스트뷰의 각 position에 뿌려주기
        var row = convertView
        if(row == null){
            row = mInflater.inflate(R.layout.view_fortune_list_item, null)
        }
        row!!  // 이미 row는 무조건 null 아니라고 우김.

        val viewListDateTxt = row.findViewById<TextView>(R.id.viewListDateTxt)
        val viewListCategoryTxt = row.findViewById<TextView>(R.id.viewListCategoryTxt)
        val viewListWorryTxt = row.findViewById<TextView>(R.id.viewListWorryTxt)
        val viewListFortuneTxt = row.findViewById<TextView>(R.id.viewListFortuneTxt)
        val viewListItemLayout = row.findViewById<LinearLayout>(R.id.viewListItemLayout)

//        저장 데이터 뽑아오기
        val data = mList.get(position)

        viewListDateTxt.text = data.getFormattedsaveDate()
        viewListCategoryTxt.text = data.saveCategory
        viewListWorryTxt.text = data.saveWorry
        viewListFortuneTxt.text = data.saveFortune

        viewListItemLayout.setOnClickListener {

            Toast.makeText(mContext, "리스트 클릭", Toast.LENGTH_SHORT).show()
        }
        return row
    }


}
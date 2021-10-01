package com.neppplus.yut_fortune.adapters

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.neppplus.yut_fortune.R
import com.neppplus.yut_fortune.ViewFortuneListActivity
import com.neppplus.yut_fortune.datas.FortuneData
import java.io.*

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

        viewListItemLayout.setOnLongClickListener {
            Log.d("길게 누름", data.getFileFormatData())

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("삭제하시겠습니까?")
            alert.setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->

                val inputFile = File("save_fortune.txt")

                val reader = BufferedReader(FileReader(inputFile))
                val writer = BufferedWriter(FileWriter(inputFile))

                val lineToRemove = data.getFileFormatData()
                var currentLine: String

                while (reader.readLine().also { currentLine = it } != null) {
                    // trim newline when comparing with lineToRemove
                    val trimmedLine = currentLine.trim { it <= ' ' }
                    if (trimmedLine == lineToRemove) continue
                    writer.write(currentLine + System.getProperty("line.separator"))
                }
                writer.close()
                reader.close()

                Log.d("데이터삭제",data.getFileFormatData())
                (context as ViewFortuneListActivity).readFortuneFromFile()
                return@OnClickListener
            })

            alert.setNegativeButton("취소", null)
            alert.show()

//            Toast.makeText(mContext, "리스트 클릭", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
        return row
    }


}
package com.neppplus.yut_fortune

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.neppplus.yut_fortune.databinding.ActivityMainBinding
import com.neppplus.yut_fortune.datas.FortuneData
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
        setupSpinnerCategory()
        setupSpinnerHandler()
    }

    override fun setupEvents() {

        binding.randomBtn.setOnClickListener {

            binding.firstTxt.text = randomValues()
            binding.secondTxt.text = randomValues()
            binding.thirdTxt.text = randomValues()

            binding.resultTxt.text = " "

            viewResult()

            binding.saveBtn.setOnClickListener {

                val customView = LayoutInflater.from(mContext).inflate(R.layout.my_custom_alert_save, null)

                val alert = AlertDialog.Builder(mContext)

                alert.setTitle("저장할 내용입력")
                alert.setView(customView)
                alert.setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                    val worryEdt = customView.findViewById<EditText>(R.id.worryEdt)

//                    확인 눌렀을때, 화면에 나와있는 데이터들을(운세카테고리, worryEdt 입력한내용, 운세 해석) String가공해서 save_fortune.txt에 저장하고 저장했다는 토스트 띄워주기.

                    val inputFortuneData = FortuneData(
                        binding.categoryTxt.text.toString(),
                        worryEdt.text.toString(),
                        binding.resultTxt.text.toString())
                    val saveStr =inputFortuneData.getFileFormatData()

                    saveResultToFile(saveStr)
//                    Log.d("파일에 저장할 문장", saveStr)

                    Toast.makeText(mContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()

                })
                alert.setNegativeButton("취소", null)
                alert.show()
            }

            return@setOnClickListener
        }

        menuBtn.setOnClickListener {
            binding.viewSaveFortuneBtn.visibility = View.VISIBLE

            binding.viewSaveFortuneBtn.setOnClickListener {
                val myIntent = Intent(mContext, ViewFortuneListActivity::class.java)
                Log.d("화면이동","저장목록으로 이동합니다.")
                startActivity(myIntent)
            }
        }

        binding.mainLayout.setOnClickListener {
            binding.viewSaveFortuneBtn.visibility = View.GONE
        }

    }
//    뽑은 랜덤숫자를 도,개,걸,모 로 변환
    fun randomValues() : String {

        val randomNumber = (1..4).random()
        val yutTxt = when(randomNumber){
            1 -> "도"
            2 -> "개"
            3 -> "걸"
            else -> "모"
        }
        return yutTxt
    }

//    결과에 따라 다른 해석 나오게 - 64가지 경우의 수
    fun viewResult() {

        val inputFirst = binding.firstTxt.text
        val inputSecond = binding.secondTxt.text
        val inputThird = binding.thirdTxt.text

        binding.resultLayout.setOnClickListener {

            if (inputFirst == "도") {

                if (inputSecond == "도"){

                    when(inputThird){
                        "도" -> binding.resultTxt.text = "어려운 상황에서 벗어나 앞날이 편해진다."
                        "개" -> binding.resultTxt.text = "집안의 재물이 조금씩 빠져나간다."
                        "걸" -> binding.resultTxt.text = "어려운 상황에서 귀인의 도움을 받지만, 서두르면 실패한다."
                        else -> binding.resultTxt.text = "상황이 차차 좋아지니 여유를 가져야 한다."
                    }
                }

                else if (inputSecond == "개") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "어려운 일을 시작하지만, 이를 극복하면 크게 성공할 것이다."
                        "개" -> binding.resultTxt.text = "뜻하지 않는 공을 세워 횡재한다."
                        "걸" -> binding.resultTxt.text = "과욕을 부리다 궁지에 빠진다."
                        else -> binding.resultTxt.text = "자기희생하면 좋은 기회가 온다."
                    }

                }

                else if (inputSecond == "걸") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "뜻한 바는 있으나 장애물이 많다."
                        "개" -> binding.resultTxt.text = "어려운 상황이 펴질 징조. 서두르는 마음을 잘 조절해야 탈이 없다."
                        "걸" -> binding.resultTxt.text = "자신의 역량을 펼치고 모든 희망을 이룰 징조이다."
                        else -> binding.resultTxt.text = "환경이 힘들고 어렵게 바뀔 수 있다."
                    }

                }

                else {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "하는 일이 잘 풀리지 않는다. 미련을 버리는 것이 좋다."
                        "개" -> binding.resultTxt.text = "기적같은 일이 일어나 어려운 처지에서 벗어난다."
                        "걸" -> binding.resultTxt.text = "귀인의 도움을 받아 어려움을 헤쳐나갈 수 있다."
                        else -> binding.resultTxt.text = "가난한 살림에서 재물을 얻을 징조이다."
                    }

                }

            }

            else if (inputFirst == "개") {

                if (inputSecond == "도"){
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "자신이 뜻한 바가 남의 방해로 이루어지지 못한다."
                        "개" -> binding.resultTxt.text = "주변은 어려운 상황이지만 본인에게는 좋은 일이 생긴다."
                        "걸" -> binding.resultTxt.text = "사소한 실수로 인해 큰 일을 망칠 수 있는 징조이다."
                        else -> binding.resultTxt.text = "갑자기 손실을 입는다."
                    }
                }

                else if (inputSecond == "개") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "미리 준비해두지 않으면 큰 낭패를 볼 수 있다."
                        "개" -> binding.resultTxt.text = "힘들게 이뤄낸 결과를 잘 간수해야 한다."
                        "걸" -> binding.resultTxt.text = "믿음을 갖고 일하면 좋은 결과를 얻는다."
                        else -> binding.resultTxt.text = "한번에 목표를 이룰 수 없으니 조금씩 나아가라."
                    }

                }

                else if (inputSecond == "걸") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "젖먹던 힘으로 노력하면 살길이 생긴다."
                        "개" -> binding.resultTxt.text = "기쁜 일이 생긴다."
                        "걸" -> binding.resultTxt.text = "뜻한바가 원만히 이루어진다."
                        else -> binding.resultTxt.text = "계획한 일이 만사형통한다."
                    }

                }

                else {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "나에게 도움이 될 사람을 만난다."
                        "개" -> binding.resultTxt.text = "잘못한 일이 나에게 돌아온다."
                        "걸" -> binding.resultTxt.text = "귀찮은 일이 많이 생긴다."
                        else -> binding.resultTxt.text = "자신의 뜻한 바가 이루어지고 크게 성공한다."
                    }

                }

            }

            else if (inputFirst == "걸"){

                if (inputSecond == "도"){
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "먼저 호의를 베풀면 좋은 일이 생긴다."
                        "개" -> binding.resultTxt.text = "지금은 좋지않은 상황이나 조금씩 기쁜 일이 생긴다."
                        "걸" -> binding.resultTxt.text = "뜻한 일이 제대로 풀리지 않는다."
                        else -> binding.resultTxt.text = "지금은 잃는것 같으나 이후를 위한 투자이므로 감수할 가치가 있다."
                    }
                }

                else if (inputSecond == "개") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "모든 것이 순리대로 이뤄진다. 하는만큼 얻는다."
                        "개" -> binding.resultTxt.text = "기회가 와도 제대로 해내지 못하면 놓칠 수 있으니 조심한다."
                        "걸" -> binding.resultTxt.text = "시험합격, 승진. 관운이 들어오는 시기."
                        else -> binding.resultTxt.text = "오래 노력해야 원하는 것을 얻을 수 있다."
                    }

                }

                else if (inputSecond == "걸") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "큰 장애물이라도 도움을 받는다면 이겨낼 수 있다."
                        "개" -> binding.resultTxt.text = "밖으로만 떠돌지 말고 내실을 다져야한다. 선택과 집중."
                        "걸" -> binding.resultTxt.text = "계획했던, 노력했던 일들이 결실을 맺는다."
                        else -> binding.resultTxt.text = "환경을 바꾸면 운이 들어올 수 있다."
                    }

                }

                else {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "새로 시작할 때는 모든 일에 신중히 대비해야한다."
                        "개" -> binding.resultTxt.text = "막혔던 일이 풀린다."
                        "걸" -> binding.resultTxt.text = "헤메지 말고 제대로 된 길을 찾아 진행해야한다."
                        else -> binding.resultTxt.text = "여러사람들과 뜻을 같이하니 좋은 때가 온다."
                    }

                }

            }

            else{
                if (inputSecond == "도"){
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "책임지는 사람이 없는 곤란한 일. 발을 들이지 않도록 조심해라."
                        "개" -> binding.resultTxt.text = "옛날의 영화를 회복한다. 고민되는 일이 있다면 과거의 내 행동을 참고해보자."
                        "걸" -> binding.resultTxt.text = "끝까지 긴장을 놓지 않는다면 일을 잘 마무리할 수 있다."
                        else -> binding.resultTxt.text = "크고 어려운 일이 있지만 일이 우연치않게 잘 풀린다."
                    }
                }

                else if (inputSecond == "개") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "혼란스러운 분위기. 한동안 몸을 사려야한다."
                        "개" -> binding.resultTxt.text = "노력에 비해 결과가 미흡하고 수확이 작다."
                        "걸" -> binding.resultTxt.text = "잘 풀리는 때가 오므로 준비하며 기다린다."
                        else -> binding.resultTxt.text = "걱정거리는 조언자와 함께 해소하자."
                    }

                }

                else if (inputSecond == "걸") {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "신중히 처신하자. 주변의 말에 일희일비 하지말자."
                        "개" -> binding.resultTxt.text = "아무리 어려워도 노력하면 희망이 다시 생긴다."
                        "걸" -> binding.resultTxt.text = "신의와 의리를 지켜야 사람을 잃지않는다. 정도를 걸어야한다."
                        else -> binding.resultTxt.text = "지성이면 감천이다. 원하는 일에 충분한 노력을 기울여야한다."
                    }

                }

                else {
                    when(inputThird){
                        "도" -> binding.resultTxt.text = "곤궁한 처지이니 조심히 행동해야한다."
                        "개" -> binding.resultTxt.text = "자만하다가 일을 그르치니 매사에 겸손하고 조심해야한다."
                        "걸" -> binding.resultTxt.text = "그동안 갇혔던 상황에서 벗어나게 된다."
                        else -> binding.resultTxt.text = "병을 치료하려면 원인을 잘 살펴야 한다."
                    }

                }

            }

            val yutResult = "${inputFirst} ${inputSecond} ${inputThird}"

        }

    }

//    저장버튼 눌렀을 때 결과를 txt로 저장 -> 텍스트를 어떻게 가공해서 저장할것인가?
    fun saveResultToFile(content : String){

//    val myFile = File(filesDir,"saveFortune.txt")
        val fileName = "save_fortune.txt"

        var myFile = File(filesDir, fileName)

        // create a new file
        val isNewFileCreated :Boolean = myFile.createNewFile()

        if(isNewFileCreated){
            Log.d("새파일 생성","save_fortune.txt이 생성되었습니다.")
//            Toast.makeText(mContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }
        else{
            Log.d("기존파일에 저장","save_fortune.txt에 저장되었습니다.")
//            Toast.makeText(mContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        val fw = FileWriter(myFile, true)
        val bw = BufferedWriter(fw)

        bw.append(content)
        bw.newLine()

        bw.close()
        fw.close()

        Log.d("데이터추가", content)

    }

    private fun setupSpinnerCategory() {
        val category = resources.getStringArray(R.array.categorySpinner)
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, category)
        binding.categorySpinner.adapter = adapter
    }

    private fun setupSpinnerHandler() {
        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.categoryTxt.text =
                    " ${binding.categorySpinner.getItemAtPosition(position)}"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun setValues() {

        titleTxt.text = "운세보기"

    }

}
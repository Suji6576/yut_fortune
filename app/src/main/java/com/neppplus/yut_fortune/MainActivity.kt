package com.neppplus.yut_fortune

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.yut_fortune.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.randomBtn.setOnClickListener {

            binding.firstTxt.text = randomValues().toString()
            binding.secondTxt.text = randomValues().toString()
            binding.thirdTxt.text = randomValues().toString()

            val inputFirstNum = binding.firstTxt.text
            val inputSecondNum = binding.secondTxt.text
            val inputThirdNum = binding.thirdTxt.text

            binding.resultLayout.setOnClickListener {


                if (inputFirstNum == "1") {

                    if (inputSecondNum == "1"){
                        when(inputThirdNum){
                            "1" -> ""
                            "2" -> ""
                            "3" -> ""
                            "4" -> ""
                        }
                    }

                    else if (inputSecondNum == "2") {

                    }

                    else if (inputSecondNum == "3") {

                    }

                    else {

                    }


                }
                else if (inputFirstNum == "2") {

                }
                else if (inputFirstNum == "3"){

                }
                else{

                }


            }

            return@setOnClickListener
        }

    }

    fun randomValues(){

        val randomNumber = (1..4).random()
        val yutTxt = when(randomNumber){
            1 -> "도"
            2 -> "개"
            3 -> "걸"
            else -> "모"
        }

    }

    override fun setValues() {

    }
}
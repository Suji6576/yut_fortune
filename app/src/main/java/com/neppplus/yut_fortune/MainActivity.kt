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

            return@setOnClickListener
        }

    }

    fun randomValues(): Int {

        val randomNumber = (1..4).random()
        return randomNumber

    }

    override fun setValues() {

    }
}
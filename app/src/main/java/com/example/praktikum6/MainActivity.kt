package com.example.praktikum6

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.text.Html.ImageGetter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.praktikum6.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var adapter: ImageAdapter
    private val listGambar = ArrayList<ImageData>()
    private lateinit var dots : ArrayList<TextView>
    private val sliderHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listGambar.add(
            ImageData(
                imgUrl = "https://images.unsplash.com/photo-1669417009275-16d0c39700f4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDZ8eEh4WVRNSExnT2N8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60"
            )
        )

        listGambar.add(
            ImageData(
                imgUrl = "https://images.unsplash.com/photo-1650128664360-d18e952f2156?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDl8eEh4WVRNSExnT2N8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60"
            )
        )

        listGambar.add(
            ImageData(
                imgUrl = "https://images.unsplash.com/photo-1669416917525-b4686d4e9211?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDd8eEh4WVRNSExnT2N8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60"
            )
        )

        listGambar.add(
            ImageData(
                imgUrl = "https://images.unsplash.com/photo-1669459326837-2d2f212846c0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDEzfHhIeFlUTUhMZ09jfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60"
            )
        )

        listGambar.add(
            ImageData(
                imgUrl = "https://images.unsplash.com/photo-1669417009275-16d0c39700f4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDZ8eEh4WVRNSExnT2N8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60"
            )
        )

        adapter = ImageAdapter(listGambar)
        binding.vpGambar.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.vpGambar.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectDot(position)
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(slideRun)
                sliderHandler.postDelayed(slideRun,3000)
            }
        })


    }

    private val slideRun = Runnable {
        binding.vpGambar.currentItem = binding.vpGambar.currentItem + 1
    }

    private fun selectDot(position: Int){
        for (i in 0 until listGambar.size){
            if(i==position){
                dots[i].setTextColor(ContextCompat.getColor(this,com.google.android.material.R.color.design_default_color_on_primary))
            }else{
                dots[i].setTextColor(ContextCompat.getColor(this,com.google.android.material.R.color.design_default_color_on_secondary))
            }
        }
    }

    private fun setIndicator(){
        for (i in 0 until listGambar.size){
            dots.add(TextView(this))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679",Html.FROM_HTML_MODE_LEGACY).toString()
            }

            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }


}
package com.example.googleadmobtest

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var adviewLayout: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var activity: Activity = this
        var context: Context = this

        // init admob
        AdmobHelper.initAdmob("ca-app-pub-3940256099942544/1033173712", this)
        // load adview
        adviewLayout = adView
        AdmobHelper.initAdview(adviewLayout)
        // interst request
        AdmobHelper.initInterstitial()
        // load interstitial ad
        load_inters.setOnClickListener {
            AdmobHelper.showInterstitial()
        }

    }


}
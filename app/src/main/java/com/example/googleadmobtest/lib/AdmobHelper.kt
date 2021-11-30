/*
 * AdmobHelper.kt class
 * Copyright (c) 2021 Zuhrul Anam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Last Modified: 11/28/21, 10:46 PM
 */

package com.example.googleadmobtest

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdmobHelper {
    companion object {
        private var mInterstitialAd: InterstitialAd? = null
        private var mContext:Context? = null
        private lateinit var mAdView: AdView
        private var adunitInterstitial: String? = null
        private val TAG: String? = AdmobHelper::class.java.getSimpleName()

        fun initAdmob(unitInters: String, context: Context) {
            mContext = context
            adunitInterstitial = unitInters
        }

        fun initInterstitial() {
             InterstitialAd.load(mContext, adunitInterstitial, request(), object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError?.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })

            mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {

                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d(TAG, "Ad failed to show.")
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    mInterstitialAd = null
                }
            }

        }

        fun request() : AdRequest? {
            return AdRequest.Builder().build()
        }


        fun showInterstitial() {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(mContext as Activity)
            }
        }

        fun initAdview(adView: AdView) {
            mAdView = adView
            mAdView.loadAd(request())
        }

    }
}
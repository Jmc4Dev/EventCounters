package com.jmc4dev.eventscounterapp

import android.app.Application
import com.google.android.gms.ads.MobileAds

class EventsCounterAppapp: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}
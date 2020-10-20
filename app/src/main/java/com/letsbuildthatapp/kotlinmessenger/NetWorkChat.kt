package com.letsbuildthatapp.kotlinmessenger

import android.app.Application
import com.google.android.gms.flags.Flag
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import java.lang.Integer.MAX_VALUE

class NetWorkChat :Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
        val built = builder.build()
        built.setIndicatorsEnabled(false)
        built.isLoggingEnabled
        Picasso.setSingletonInstance(built)
    }
}
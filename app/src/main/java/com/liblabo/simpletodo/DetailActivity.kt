package com.liblabo.simpletodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class DetailActivity : AppCompatActivity(), Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFailure(call: Call, e: IOException) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call, response: Response) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
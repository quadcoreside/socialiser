package com.wara.socialiser.ui.post

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wara.socialiser.R


class PostViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_view_activity)

        // Get the Intent that started this activity and extract the string
        val message = intent.getIntExtra("post_id")
        Log.d("TEST PARAM", "msg =" +  message)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
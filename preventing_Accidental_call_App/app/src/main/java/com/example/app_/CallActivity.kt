package com.example.app_

import android.content.ContentValues
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_.databinding.CallBinding

class CallActivity:AppCompatActivity() {
    lateinit var binding: CallBinding
    val dbHelper = MyDbHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = CallBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val getList = dbHelper.selectNum()
        val getOneNum = getList[getList.lastIndex].c1
        val getOneMsg = getList[getList.lastIndex].c4
        val adapter = MyAdapterDb(getList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter



        binding.call.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel::${getOneNum}")).apply { }
            startActivity(intent)
        }

        binding.text.setOnClickListener {
            val intent:Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto::${getOneNum}"))
            intent.putExtra("sms_body","${getOneMsg}")
            startActivity(intent)
        }

    }



    }

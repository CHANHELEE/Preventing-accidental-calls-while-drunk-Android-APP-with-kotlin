package com.example.app_

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.app_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val dbHelper = MyDbHelper(this)
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSet = arrayOf(
            ImageElement(R.drawable.item1, "(텍스트를 옆으로 스와이프 하세요.)"),
            ImageElement(R.drawable.item2, ""),
        )


        binding.viewpager2.adapter = MyAdapter(dataSet)
        //viewpager
// ---------------------------------------------------------------------
        /*val reqLauncher = registerForActivityResult( // intent
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            /*if(result.resultCode== RESULT_OK){
                Log.d("TAG","return")
                val intent = result.data
                val clickedBtn :String? = intent?.getStringExtra("res")
                "btn: $clickedBtn".also {binding.txtMainView1.text =it}
            }*/
        }*///intent


        //db
        var db = dbHelper.writableDatabase
        db.close()

        val getList = dbHelper.selectAll()
        val adapter = MyAdapterDb(getList)


        binding.submit.setOnClickListener {
            db = dbHelper.writableDatabase
            val elem = MyElement(
                binding.PhoneNum.text.toString(),
                binding.msg.text.toString(),
                binding.date.text.toString(),
                binding.relationship.text.toString()
            )

            val values = ContentValues().apply {
                put(MyDbHelper.MyDBContract.MyEntry.c1, elem.c1)
                put(MyDbHelper.MyDBContract.MyEntry.c2, elem.c2)
                put(MyDbHelper.MyDBContract.MyEntry.c3, elem.c3)
                put(MyDbHelper.MyDBContract.MyEntry.c4, elem.c4)
            }
            val newRowId = db?.insert(MyDbHelper.MyDBContract.MyEntry.TABLE_NAME, null, values)
            Log.d("TAG", values.toString())

            val newList = dbHelper.selectAll()
            adapter.setList(newList)
            adapter.notifyDataSetChanged()

            db.close()
            //db
            //---------------------------
            //intent
            val intent: Intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
//-------------------------------------------------------------------------


        }
        /*override fun onStart() {
            super.onStart()
            Log.d("MYTAG", "onStart")
        }

        override fun onResume() {
            super.onResume()
            Log.d("MYTAG", "onResume")
        }

        override fun onPause() {
            super.onPause()
            Log.d("MYTAG", "onPause")
        }

        override fun onStop() {
            super.onStop()
            Log.d("MYTAG", "onStop")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.d("MYTAG", "onDestroy22")
        }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            Log.d("MYTAG", "onSaveInstance")
        }

        override fun onRestoreInstanceState(savedInstanceState: Bundle) {
            super.onRestoreInstanceState(savedInstanceState)
            Log.d("MYTAG", "onRestoreInstance ")
        }*/
    }


}

class ImageElement(img: Int, message: String) {
    var image = img
    var msg = message
}
package com.example.app_

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app_.databinding.TestBinding

class TestActivity :AppCompatActivity() {
    lateinit var binding: TestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =TestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.test1.setSelectAllOnFocus(true)

        val error1 =(121..200).random()
        val error1_1 =(4..9).random()
        val result1 = error1*error1_1

        val error2 =(501..601).random()
        val error2_1 =(201..401).random()
        val error2_2 =(121..200).random()
        val result2 = error2+error2_1-error2_2

        var arr = arrayOf<String>("정말 취했을까 안 취했을까 이대로 연락해도 되는걸까 아닌걸까 내일 후회하지는 않을까? 오타가 나면 연락하지말고 자야지!!!!! 그래도 연락하면 내일 후회하겠지.")



        binding.test1.text="${error1} x ${error1_1}"


        binding.test2.text="${error2} + ${error2_1} - ${error2_2}        ${error2} + ${error2_1} - ${error2_2}"
        binding.test2.setSingleLine()
        binding.test2.marqueeRepeatLimit=-1
        binding.test2.ellipsize = TextUtils.TruncateAt.MARQUEE
        binding.test2.isSelected = true

        binding.test3.text=arr[0]
        /*android:ellipsize="marquee"
        android:singleLine="true"
        android:marqueeRepeatLimit="marquee_forever"-->/>*/
        //binding.test1.ellipsize.
        //binding.test1.setSingleLine(true)
        //binding.test1.marqueeRepeatLimit.
        binding.test1.setSelectAllOnFocus(true)


        binding.testbtn.setOnClickListener {
            var t1= binding.test1Edit.text.toString()
            var t2= binding.test2Edit.text.toString()
            var t3= binding.test3Edit.text.toString()
            var flag = 2 // flag ==1 이면 정답 , flag == 2 이면 오답

            if(t1 ==result1.toString() && t2 == result2.toString() && t3 == arr[0]){
                flag=1
            }
            else{
                flag=2
            }

            if(flag ==1){
                binding.testError.visibility= View.INVISIBLE
                val intent: Intent = Intent(this, CallActivity::class.java)
                startActivity(intent)
            }
            else{
                binding.testError.visibility= View.VISIBLE
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        setResult(RESULT_OK,intent)
        finish()
        return true
    }
    override fun onStart() {
        super.onStart()
        Log.d("MYTAG","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYTAG","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYTAG","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MYTAG","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MYTAG","onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MYTAG","onSaveInstance")
        //outState.putInt("count")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("MYTAG","onRestoreInstance ")
        // = savedInstanceState.getInt("count",0)
        //Log.d("MYTAG","onRestoreInstanceLoad $count")
    }
}
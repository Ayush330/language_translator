package com.example.language_translator

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.*
import okhttp3.*
import java.util.*


class MainActivity : AppCompatActivity()
{

    private var response: Response? = null;
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       translate.setOnClickListener {
           if(TextUtils.isEmpty(data.text.toString())) {
               data.error = "Enter the text.";
           }
           else if(TextUtils.isEmpty(target.text.toString()))
           {
               target.error = "Enter the language."
           }
           else
           {
               progressBar.visibility = VISIBLE
               GlobalScope.launch{

                   try {
                       network()
                       Log.i("Ayush", "Hey")
                   } catch (e: Exception) {

                   }

               }

           }
       }

        clear.setOnClickListener {
            data.setText("")
            Text.text=""
            target.setText("")
        }

    }

    suspend fun network()
    {

        val x: String = data.text.toString()
        var y:String = target.text.toString();
        y = y.toLowerCase(Locale.ROOT)
        val result = targetLanguage.check(y)

        if(result == "not found")
        {

                val newFragment = myDialog()
                newFragment.show(supportFragmentManager, "missiles")
                progressBar.visibility= INVISIBLE
        }



        else
        {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://google-translate20.p.rapidapi.com/translate?text=$x.&tl=$result&sl=en")
                .get()
                .addHeader("x-rapidapi-key", "1390ce28a0msh2bbec5814a3ea04p1f578fjsn89943b312605")
                .addHeader("x-rapidapi-host", "google-translate20.p.rapidapi.com")
                .build()

            val response:Response? = try {
                client.newCall(request).execute()
            } catch (e: Exception) {
                null
            }

            val string = try {
                response?.body()?.string().toString()
            } catch (e: Exception) {
                "There was an error at response body"
            }

            Log.i("Ayush", string)
            //string= "[$string]"
            doit(string)
            Log.i("Ayush", "Done")
        }



    }

    suspend fun doit(s: String)
    {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<translation> =
            moshi.adapter(translation::class.java)

        val text: translation? = jsonAdapter.fromJson(s)

        progressBar.visibility= INVISIBLE
        if (text != null) {
            Log.i("Ayush", text.data.translation.toString())
            Text.text = text.data.translation.toString()
        }
        else
        {
            Text.text = "Some Error occured while translating."
        }
    }
}
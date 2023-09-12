package com.example.hackillinoisapp.model

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import android.util.Log
import com.example.hackillinoisapp.WrapperJSON
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

import kotlinx.serialization.*
import org.json.JSONArray

import org.json.JSONObject


class api {

    var json : WrapperJSON = WrapperJSON(JSONObject("{  \n" +
            "    \"employee\": {  \n" +
            "        \"name\":       \"sonoo\",   \n" +
            "        \"salary\":      56000,   \n" +
            "        \"married\":    true  \n" +
            "    }  \n" +
            "}  "))

    fun makeRequest() {
        //https://square.github.io/okhttp/

        val client = OkHttpClient()
        val url = "https://adonix.hackillinois.org/event/"
        val request = Request.Builder().url(url).build()
        /*
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                Log.d("API Fetching Error", "Something Went Wrong")
            }

            val responseUsage = response.body?.string()
            Log.d("Events", ""+responseUsage)
            val jsonData = JSONObject(responseUsage)
            Log.d("JsonDataAPI", ""+jsonData)
            json.json = jsonData
            Log.d("toReturnAPI", ""+json)
            val jsonArr = json.json.getJSONArray("events")
            for (i in 0 until jsonArr.length()) {
                //val jsonObj = jsonArray.getJSONObject(i)
                if (responseUsage != null) {
                    val item = jsonArr.getJSONObject(i)
                    Log.d("Event$i",""+item.get("name")+" "+item.get("description"))
                }
            }
        }

         */



        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("Error", "Something Went Wrong")
            }

            override fun onResponse(call: Call, response: Response){
                //https://developer.android.com/reference/kotlin/org/json/JSONObject
                val responseUsage = response.body?.string()

                Log.d("Events", "" + responseUsage)

                val jsonData = JSONObject(responseUsage)

                Log.d("jsonDataAPI", ""+jsonData)
                //return jsonData
                Log.d("toReturnAPIBefore", ""+json)
                json.json = jsonData
                Log.d("toReturnAPIAfter", ""+json)

                //This point onwards was debugging and qa
                val jsonArr = jsonData.getJSONArray("events")

                for (i in 0 until jsonArr.length()) {
                    //val jsonObj = jsonArray.getJSONObject(i)
                    if (responseUsage != null) {
                        val item = jsonArr.getJSONObject(i)
                        Log.d("Event$i",""+item.get("name")+" "+item.get("description"))
                    }
                }
                Log.d("checkingAPIJSON", ""+json.json)
            }

        })



    }

    fun getJSON() : WrapperJSON {
        return json
    }
}
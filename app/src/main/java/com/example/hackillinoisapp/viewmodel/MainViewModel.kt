package com.example.hackillinoisapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hackillinoisapp.model.api
import org.json.JSONObject
import com.example.hackillinoisapp.WrapperJSON


class MainViewModel : ViewModel() {

    //var thread : Thread

    private var api : api = api()
    var json : WrapperJSON = WrapperJSON(JSONObject("{  \n" +
            "    \"employee\": {  \n" +
            "        \"name\":       \"sonoo\",   \n" +
            "        \"salary\":      56000,   \n" +
            "        \"married\":    true  \n" +
            "    }  \n" +
            "}  "))

    fun init() {
        //Log.d("Testing", "Testing")
        api.makeRequest()
        //This is a workaround for the async function
        //It's a very hamfisted solution, maybe use Room/Dao?
        //Ask if its better to use .execute in a separate Thread rather than sleep, which seems very bad
        Thread.sleep(1000)
        Log.d("ViewModelAPIJSON", ""+api.getJSON().json)
        json = api.json
        Log.d("ViewModelJSON", ""+json.json)
    }
}
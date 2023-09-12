package com.example.hackillinoisapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackillinoisapp.ui.theme.HackIllinoisAppTheme
import com.example.hackillinoisapp.viewmodel.MainViewModel
import org.json.JSONObject
import java.util.Date

class MainActivity : ComponentActivity() {
    //MVVM understanding from HackIllinois repository and basic youtube videos
    private lateinit var viewModel: MainViewModel

    //Should've probably made this a nullable JSONObject
    private var mainJSON : WrapperJSON = WrapperJSON(JSONObject("{  \n" +
            "    \"employee\": {  \n" +
            "        \"name\":       \"sonoo\",   \n" +
            "        \"salary\":      56000,   \n" +
            "        \"married\":    true  \n" +
            "    }  \n" +
            "}  "))
    private lateinit var adapter : Adapter
    private val itemsList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        //View part of MVVM is executing functions here which is really bad
        //It's what I have for now though, could've implemented LiveData
        var viewModel : MainViewModel = MainViewModel()
        viewModel.init()
        mainJSON = viewModel.json
        Log.d("Debugging", ""+mainJSON)

        val recyclerView : RecyclerView = findViewById(R.id.mainRecycler)
        adapter = Adapter(itemsList, this@MainActivity)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        /*
        viewModel = ViewModelProvider(this)[MainViewModel::class.java].apply {
            init()
            val owner = this@MainActivity
            //This should be LiveData but it's not for now
            //mainJSON = viewModel.json
        }

         */
//

        /*
        setContent {
            HackIllinoisAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

         */
        addItems(mainJSON)
    }

    //Possible Refresh Button? Just move oncreate body into here and call this function

    private fun addItems(wrapperJson : WrapperJSON) {

        if (wrapperJson == null) {
            Log.d("APIFetchError", "Failed to retrieve a JSON File")
            return;
        }

        var json = wrapperJson.json

        val jsonArr = json.getJSONArray("events")
        for (i in 0 until jsonArr.length()) {
            val item = jsonArr.getJSONObject(i)
            itemsList.add(""+item.get("name")+"\nStart: "+
                    getDateTime(item.get("startTime") as Integer)+
                    "\nEnd: "+getDateTime(item.get("endTime") as Integer)+
                    "\n"+item.get("description"))


        }
    }


    private fun getDateTime(time: Integer): String? {
        val n = time.toLong()
        try {
            val netDate = Date(n*1000)
            return netDate.toString()
        } catch (e: Exception) {
            return e.toString()
        }
    }


}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HackIllinoisAppTheme {
        Greeting("Android")
    }
}
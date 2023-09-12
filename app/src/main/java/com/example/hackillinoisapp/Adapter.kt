package com.example.hackillinoisapp

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import android.content.Context


internal class Adapter(private var itemsList: List<String>, context: Context) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    //private var onClickListener : OnClickListener? = null
    //private val mListener: OnItemClickListener? = listener

    var context : Context = context


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return MyViewHolder(itemView)


    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("Scrolled", "scrolled")
        val item = itemsList[position]
        holder.itemTextView.text = item
        //Onclick functionality using Toast to display additional information I didn't finish
        holder.itemView.setOnClickListener {
            Log.d("OnClick", "Worked"+item)
            //Could've used google API here for location information?
            //Toast.makeText(context, ""+, Toast.LENGTH_LONG).show()


            /*
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, item )
                }

                 */
        }

    }
    override fun getItemCount(): Int {
        return itemsList.size
    }


    inline fun Context.showToast(message:String){
        Toast.makeText(this, message , Toast.LENGTH_LONG).show()
    }
    //Inline is very cool especially for memory optimization
    //Advantage over java?


    /*
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: String)
    }

     */

}

package com.mihir.imagesbymihir

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray

class ImagesViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>()
    private var _images = mutableStateListOf<String>()
    private val requestQueue = Volley.newRequestQueue(context)
    val images : List<String> = _images


    fun add(keyword : String){
        val key = context.getString(R.string.image_api_key)

        if(images.isNotEmpty())
        {
            _images.removeAll(images)
        }

        val list = mutableListOf<String>()

        val url = "https://pixabay.com/api/?key=${key}&q=${keyword}&image_type=all&pretty=true&per_page=3"

        val request : JsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            url,
            null,
            Response.Listener{
                val array : JSONArray = it.getJSONArray("hits")
                for (i in 0 until array.length()){
                    val item = array.getJSONObject(i)
                    list.add(item.getString("webformatURL"))
                }
                _images.addAll(list)
            },
            Response.ErrorListener {
                println(it)
            }
        ){}

        requestQueue.add(request)





    }
}
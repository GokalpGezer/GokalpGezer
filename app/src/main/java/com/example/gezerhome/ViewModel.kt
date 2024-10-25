package com.example.gezerhome
/*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class VideoViewModel : ViewModel() {
    private val _videoData = MutableLiveData<List<VideoItem>>()
    val videoData: LiveData<List<VideoItem>> = _videoData

    init {
        fetchVideoData()
    }

    fun fetchVideoData() {
        viewModelScope.launch(Dispatchers.IO) {
            val url = URL("http://192.168.1.27:5000/footages")
            val connection = url.openConnection() as HttpURLConnection
            try {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                Log.d("VideoViewModel", "Response: $response")
                val jsonArray = JSONArray(response)
                val videoList = mutableListOf<VideoItem>()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val name = jsonObject.getString("name")
                    val videoUrl = jsonObject.getString("video_url")
                    val videoType = if (jsonObject.has("video_type")) {
                        jsonObject.getString("video_type")
                    } else {
                        "unknown" // Default or fallback value
                    }
                    videoList.add(VideoItem(name, videoUrl, videoType))
                }
                _videoData.postValue(videoList)
                Log.d("VideoViewModel", "Parsed video list: $videoList")
            } catch (e: Exception) {
                Log.e("VideoViewModel", "Error fetching video data", e)
            } finally {
                connection.disconnect()
            }
        }
    }
}

data class VideoItem(val name: String, val videoUrl: String, val videoType: String)
*/
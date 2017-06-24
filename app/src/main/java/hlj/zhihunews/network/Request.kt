package hlj.zhihunews.network

import hlj.zhihunews.config.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2017/6/21 0021.
 */
class Request private constructor(){

    var api:Api ?= null

    init {
        val client = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
        api = retrofit.create(Api::class.java)
    }



    class RequestHolder {
        companion object{
            val request = Request()
        }
    }

    companion object{
        fun getSingle(): Request {
            return RequestHolder.request
        }
    }
}
package hlj.zhihunews.network

import hlj.zhihunews.bean.NewsDetail
import hlj.zhihunews.bean.NewsList
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by Administrator on 2017/6/20 0020.
 */
interface Api {
    @GET("api/4/news/latest")
    fun latest(): Observable<NewsList>

    @GET("api/4/news/{id}")
    fun detail(@Path("id") id: String): Observable<NewsDetail>

    @GET("api/4/news/before/{data}")
    fun befare(@Path("data") data: String): Observable<NewsList>
}
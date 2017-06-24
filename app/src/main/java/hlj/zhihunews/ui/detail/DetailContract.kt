package hlj.zhihunews.ui.detail

import android.content.Context
import hlj.comics.base.BasePresenter
import hlj.comics.base.BaseView
import hlj.zhihunews.bean.NewsDetail
import hlj.zhihunews.bean.NewsList

/**
 * Created by Administrator on 2017/6/21 0021.
 */
interface DetailContract {
    interface View : BaseView {
        fun showProgress()
        fun hideProgress()
        fun showMsg(strId:Int)
        fun loadNewsDetail(newsDetail: NewsDetail)
    }
    abstract class Presenter(view: DetailContract.View) : BasePresenter(view) {
        abstract fun loadNewsDetail(id:Int)
        abstract fun collectionNews(context:Context, stories:NewsList.StoriesBean)
    }
}
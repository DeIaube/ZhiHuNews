package hlj.zhihunews.ui.news

import hlj.comics.base.BasePresenter
import hlj.comics.base.BaseView
import hlj.zhihunews.bean.NewsList

/**
 * Created by Administrator on 2017/6/20 0020.
 */
interface NewsContract {
    interface View :BaseView{
        fun showProgress()
        fun hideProgress()
        fun showMsg(strId:Int)
        fun loadNewsList(storieList: List<NewsList.StoriesBean>)
    }
    abstract class Presenter(view: NewsContract.View) : BasePresenter(view) {
        abstract fun loadNewsList()
        abstract fun loadNewsList(year:Int, month:Int, day:Int)
    }
}
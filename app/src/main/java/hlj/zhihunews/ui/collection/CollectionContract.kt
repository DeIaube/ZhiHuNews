package hlj.zhihunews.ui.collection

import android.content.Context
import hlj.comics.base.BasePresenter
import hlj.comics.base.BaseView
import hlj.zhihunews.bean.NewsList

/**
 * Created by Administrator on 2017/6/23 0023.
 */
interface CollectionContract {
    interface View : BaseView {
        fun showProgress()
        fun hideProgress()
        fun showMsg(strId:Int)
        fun loadNewsList(storieList: List<NewsList.StoriesBean>)
    }
    abstract class Presenter(view: CollectionContract.View) : BasePresenter(view) {
        abstract fun loadCollectionList(context:Context)
    }
}
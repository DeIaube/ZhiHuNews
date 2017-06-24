package hlj.zhihunews.ui.detail

import android.content.Context
import com.avos.avoscloud.AVUser
import hlj.zhihunews.R
import hlj.zhihunews.bean.NewsList
import hlj.zhihunews.network.Request
import hlj.zhihunews.ui.login.LoginActivity
import hlj.zhihunews.util.CollectionUtil
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/6/21 0021.
 */
class DetailPresenter(val view: DetailContract.View) : DetailContract.Presenter(view) {
    override fun collectionNews(context: Context, stories: NewsList.StoriesBean) {
        if (AVUser.getCurrentUser() == null) {
            view.showMsg(R.string.please_login)
            LoginActivity.start(context)
            return
        }
        Observable.just(stories)
                .map { CollectionUtil.collection(stories) }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view.hideProgress()
                    view.showMsg(R.string.collection_success)
                })
    }

    override fun loadNewsDetail(id: Int) {
        view.showProgress()
        Request.getSingle().api?.detail(id.toString())
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view.loadNewsDetail(it)
                    view.hideProgress()
                },{
                    view.showMsg(R.string.load_error)
                    view.hideProgress()
                })

    }
}
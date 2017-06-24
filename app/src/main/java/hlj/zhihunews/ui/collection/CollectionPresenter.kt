package hlj.zhihunews.ui.collection

import android.content.Context
import com.avos.avoscloud.AVUser
import hlj.zhihunews.R
import hlj.zhihunews.ui.login.LoginActivity
import hlj.zhihunews.util.CollectionUtil
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/6/23 0023.
 */
class CollectionPresenter(var view: CollectionContract.View) : CollectionContract.Presenter(view) {
    override fun loadCollectionList(context: Context) {
        if (AVUser.getCurrentUser() == null) {
            view.showMsg(R.string.please_login)
            LoginActivity.start(context)
            return
        }
        view.showProgress()
        Observable.just("")
                .map { CollectionUtil.getStories() }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view.loadNewsList(it)
                    view.hideProgress()
                },{
                    view.hideProgress()
                })
    }

}
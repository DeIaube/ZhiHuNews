package hlj.zhihunews.ui.news

import hlj.zhihunews.R
import hlj.zhihunews.network.Request
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/6/20 0020.
 */
class NewsPresenter(val view: NewsContract.View) : NewsContract.Presenter(view) {
    override fun loadNewsList(year: Int, month: Int, day: Int) {
        view.showProgress()
        var sb = StringBuilder()
        sb.append(year)
        if (month < 10) { sb.append("0") }
        sb.append(month)
        if (day < 10) { sb.append("0") }
        sb.append(day)
        Request.getSingle().api?.befare(sb.toString())
                ?.map { it.stories }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view.hideProgress()
                    view.loadNewsList(it)
                },{
                    view.showMsg(R.string.load_error)
                    view.hideProgress()
                })
    }

    override fun loadNewsList() {
        view.showProgress()
        Request.getSingle().api?.latest()
                ?.map { it.stories }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view.hideProgress()
                    view.loadNewsList(it)
                },{
                    view.showMsg(R.string.load_error)
                    view.hideProgress()
                })
    }


}
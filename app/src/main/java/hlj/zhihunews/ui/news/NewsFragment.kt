package hlj.zhihunews.ui.news

import android.app.DatePickerDialog
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import hlj.comics.base.BaseFragment
import hlj.comics.util.toast
import hlj.zhihunews.R
import hlj.zhihunews.adapter.NewsListAdapter
import hlj.zhihunews.bean.NewsList
import hlj.zhihunews.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Administrator on 2017/6/19 0019.
 */
class NewsFragment : BaseFragment(), NewsContract.View {
    override fun loadNewsList(storieList: List<NewsList.StoriesBean>) {adapter!!.update(storieList)}

    override fun showMsg(strId:Int) { toast(strId) }

    override fun showProgress() {freshLayout.isRefreshing = true}

    override fun hideProgress() {freshLayout.isRefreshing = false }

    var newsListState = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    var presenter:NewsContract.Presenter ?= null
    var adapter:NewsListAdapter ?= null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun init() {
        super.init()
        freshLayout.setColorSchemeColors(activity.getColor(R.color.colorPrimary))
        freshLayout.setProgressViewEndTarget(true, 500)
        newsList.layoutManager = LinearLayoutManager(activity)
        adapter = NewsListAdapter(context, listOf())
        newsList.adapter = adapter
        presenter = NewsPresenter(this)
        freshLayout.setOnRefreshListener { presenter!!.loadNewsList() }
        presenter!!.loadNewsList()
        //控制滑动屏幕过程中 日历按钮自动隐藏
        newsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                newsListState = newState
            }
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (newsListState != 2) {
                    return
                }
                if (dy > 0) {
                    val fabBottomMargin = (calendar.getLayoutParams() as FrameLayout.LayoutParams ).bottomMargin
                    calendar.animate().translationY((calendar.getHeight() + fabBottomMargin).toFloat()).setInterpolator(LinearInterpolator()).start()
                } else {
                    calendar.animate().translationY(0f).setInterpolator(LinearInterpolator()).start()
                }
            }
        })

        calendar.setOnClickListener {
            var dialog = DatePickerDialog(context)
            dialog.setOnDateSetListener { datePicker, year, month, day ->  presenter!!.loadNewsList(year, month + 1, day)}
            dialog.show()
        }

        adapter!!.clickListener = object :NewsListAdapter.OnClickListener{
            override fun click(view: View, storie: NewsList.StoriesBean) {
                DetailActivity.start(context, storie)
            }
        }
    }
}
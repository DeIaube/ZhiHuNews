package hlj.zhihunews.ui.collection

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import hlj.comics.base.BaseFragment
import hlj.comics.util.toast
import hlj.zhihunews.R
import hlj.zhihunews.adapter.NewsListAdapter
import hlj.zhihunews.bean.NewsList
import hlj.zhihunews.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_collection.*

/**
 * Created by Administrator on 2017/6/19 0019.
 */
class CollectionFragment : BaseFragment(), CollectionContract.View {
    override fun showProgress() {
        freshLayout.isRefreshing = true
    }

    override fun hideProgress() {
        freshLayout.isRefreshing = false
    }

    override fun showMsg(strId: Int) {
        toast(strId)
    }

    override fun loadNewsList(storieList: List<NewsList.StoriesBean>) {
        adapter?.update(storieList)
    }

    var presenter:CollectionContract.Presenter ?= null
    var adapter:NewsListAdapter ?= null

    override fun init() {
        super.init()
        freshLayout.setColorSchemeColors(activity.getColor(R.color.colorPrimary))
        freshLayout.setProgressViewEndTarget(true, 500)
        newsList.layoutManager = LinearLayoutManager(context)
        adapter = NewsListAdapter(context, listOf())
        newsList.adapter = adapter
        presenter = CollectionPresenter(this)
        presenter!!.loadCollectionList(context)
        freshLayout.setOnRefreshListener { presenter!!.loadCollectionList(context) }

        adapter!!.clickListener = object :NewsListAdapter.OnClickListener{
            override fun click(view: View, storie: NewsList.StoriesBean) {
                DetailActivity.start(context, storie)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_collection
    }
}
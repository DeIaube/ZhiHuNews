package hlj.zhihunews.ui.detail

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import hlj.comics.base.BaseActivity
import hlj.comics.util.toast
import hlj.zhihunews.R
import hlj.zhihunews.bean.NewsDetail
import hlj.zhihunews.bean.NewsList
import hlj.zhihunews.util.HtmlUtil
import kotlinx.android.synthetic.main.activity_detail.*



class DetailActivity : BaseActivity(), DetailContract.View {
    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showMsg(strId: Int) {
        toast(strId)
    }

    override fun loadNewsDetail(newsDetail: NewsDetail) {
        Glide.with(this).load(newsDetail.image).into(headImg)
        val content = HtmlUtil.createHtmlData(newsDetail.body, HtmlUtil.createCssTag(newsDetail.css), HtmlUtil.createJsTag(newsDetail.js))
        webView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null)
    }

    var stories:NewsList.StoriesBean ?= null
    var presenter:DetailContract.Presenter ?= null

    override fun getLayoutId(): Int {
        return R.layout.activity_detail
    }

    override fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initWebView()
        stories = intent.getParcelableExtra(STORIES)
        collection.setOnClickListener { presenter!!.collectionNews(this, stories!!) }
        presenter = DetailPresenter(this)
        presenter!!.loadNewsDetail(stories!!.id)
    }

    private fun initWebView() {
        val settings = webView.settings
        settings.domStorageEnabled = true
        settings.setAppCacheEnabled(true)
        settings.javaScriptEnabled = true
        settings.pluginState = WebSettings.PluginState.ON
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.setAppCachePath(cacheDir.absolutePath + "/webViewCache")
        settings.setAppCacheEnabled(true)
        settings.loadWithOverviewMode = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        val STORIES = "stories"
        fun start(context: Context, stories: NewsList.StoriesBean) {
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(STORIES, stories)
            context.startActivity(intent)
        }
    }
}

package hlj.zhihunews.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import hlj.zhihunews.R
import hlj.zhihunews.bean.NewsList
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Created by Administrator on 2017/6/19 0019.
 */
class NewsListAdapter(var context:Context, var storieList: List<NewsList.StoriesBean>) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    val NORMAL = 1
    val BLANK = 0

    var clickListener:OnClickListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when(viewType) {
            NORMAL -> return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false))
            BLANK -> return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false))
        }

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (position == 0) {
            return
        }
        val bean = storieList!!.get(position - 1)
        holder!!.index = position - 1
        holder!!.itemView.newsTitle.text = bean.title
        val images = bean.images
        if (images?.size != 0) {
            Glide.with(context).load(images?.first()).into(holder!!.itemView.newsImg)
        }
    }

    override fun getItemCount(): Int {
        return storieList!!.size + 1
    }

    interface OnClickListener{
        fun click(view:View, storie:NewsList.StoriesBean)
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0) {
            return BLANK
        }
        return NORMAL
    }

    fun update(storieList: List<NewsList.StoriesBean>) {
        this.storieList = storieList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var index:Int ?= null
        init {
            itemView!!.setOnClickListener { clickListener?.click(itemView!!, storieList.get(index!!)) }
        }
    }
}
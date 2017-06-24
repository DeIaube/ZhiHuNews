package hlj.zhihunews.util

import com.avos.avoscloud.AVObject
import com.avos.avoscloud.AVQuery
import com.avos.avoscloud.AVUser
import com.google.gson.Gson
import hlj.zhihunews.bean.NewsList

/**
 * Created by Administrator on 2017/6/23 0023.
 */
class CollectionUtil {
    companion object{

        var collectionBean:AVObject ?= null
        var list:MutableList<String> ?= null
        var storiesList:MutableList<NewsList.StoriesBean> = mutableListOf()

        fun init() {
            val user = AVUser.getCurrentUser()
            var query = AVQuery<AVObject>("collection")
            var temp = query.whereEqualTo("id", user.username).find()
            if (temp == null || temp.isEmpty()) {
                collectionBean = AVObject("collection")
                collectionBean!!.put("id", user.username)
                list = mutableListOf()
                collectionBean!!.add("list", list)
            }  else {
                collectionBean = temp.first()
                list = collectionBean!!.getList("list") as MutableList<String>?
                list!!.forEach { storiesList.add(Gson().fromJson(it,NewsList.StoriesBean::class.java)) }
            }
        }

        fun getStories() :MutableList<NewsList.StoriesBean>{
            if (list == null) {
                init()
            }
            return storiesList
        }

        fun collection(stories: NewsList.StoriesBean) {
            if (list == null) {
                init()
            }
            if(!storiesList.contains(stories)) {
                list!!.add(Gson().toJson(stories))
                storiesList.add(stories)
            }
        }

        fun saveCollection() {
            if(list != null) {
                collectionBean!!.put("list", list)
                collectionBean!!.saveInBackground()
            }
        }
    }
}
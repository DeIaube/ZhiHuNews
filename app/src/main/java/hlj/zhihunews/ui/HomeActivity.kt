package hlj.zhihunews.ui

import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.view.MenuItem
import hlj.comics.base.BaseActivity
import hlj.zhihunews.R
import hlj.zhihunews.ui.collection.CollectionFragment
import hlj.zhihunews.ui.news.NewsFragment
import hlj.zhihunews.ui.setting.SettingFragment
import hlj.zhihunews.util.CollectionUtil
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    var newsFragment = NewsFragment()
    var collectionFragment = CollectionFragment()
    var settingFragment = SettingFragment()

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun init() {
        setSupportActionBar(toolbar)
        navigation.setOnNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.navigation_home -> {
                        replaceFragment(R.id.content, newsFragment)
                        return true
                    }
                    R.id.navigation_collection -> {
                        replaceFragment(R.id.content, collectionFragment)
                        return true
                    }
                    R.id.navigation_setting -> {
                        replaceFragment(R.id.content, settingFragment)
                        return true
                    }
                }
                return false
            }
        })
        supportFragmentManager.beginTransaction().add(R.id.content, newsFragment).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        CollectionUtil.saveCollection()
    }
}

package hlj.comics.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus

/**
 * Created by Administrator on 2017/6/10 0010.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
        if (isUseEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isUseEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    open fun isUseEventBus() :Boolean{
        return false
    }

    fun replaceFragment(id:Int, fragment: BaseFragment) {
        supportFragmentManager.beginTransaction().replace(id, fragment).commit()
    }

    abstract fun getLayoutId():Int
    abstract fun init()
}
package hlj.comics.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.greenrobot.eventbus.EventBus

/**
 * Created by Administrator on 2017/6/10 0010.
 */
abstract class BaseFragment : Fragment() {

    var rootView: View? = null
    private var isInited = false
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(getLayoutId(), container, false)
        }
        if (isUseEventBus()) {
            EventBus.getDefault().register(this)
        }
        return rootView
    }

    override fun onStart() {
        super.onStart()
        if (!isInited) {
            init()
        }
    }

    open fun init(){
        isInited = true
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

    abstract fun getLayoutId(): Int
}
package hlj.zhihunews.ui.regiest

import hlj.comics.base.BasePresenter
import hlj.comics.base.BaseView

/**
 * Created by Administrator on 2017/6/23 0023.
 */
interface RegiestContract {
    interface View : BaseView {
        fun showProgress()
        fun hideProgress()
        fun showMsg(strId:Int)
        fun regiestSuccess()
    }
    abstract class Presenter(view: RegiestContract.View) : BasePresenter(view) {
        abstract fun regiest(username: String, password: String, rePassword:String)
    }
}
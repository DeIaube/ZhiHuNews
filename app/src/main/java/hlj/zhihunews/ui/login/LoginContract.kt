package hlj.zhihunews.ui.login

import hlj.comics.base.BasePresenter
import hlj.comics.base.BaseView

/**
 * Created by Administrator on 2017/6/22 0022.
 */
interface LoginContract {
    interface View : BaseView {
        fun showProgress()
        fun hideProgress()
        fun showMsg(strId:Int)
        fun loginSuccess()
    }
    abstract class Presenter(view: LoginContract.View) : BasePresenter(view) {
        abstract fun login(username: String, password: String)
    }
}
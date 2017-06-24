package hlj.zhihunews.ui.login

import android.util.Log
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.LogInCallback
import hlj.zhihunews.R

/**
 * Created by Administrator on 2017/6/22 0022.
 */
class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter(view) {

    override fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view.showMsg(R.string.username_or_password_not_empty)
            return
        }
        view.showProgress()
        AVUser.logInInBackground(username, password, object : LogInCallback<AVUser>() {
            override fun done(p0: AVUser?, e: AVException?) {
                view.hideProgress()
                if (e == null) {
                    //登录成功
                    view.showMsg(R.string.login_success)
                    view.loginSuccess()
                } else {
                    //登录失败
                    view.showMsg(R.string.login_error)
                    Log.i("LoginPresenter", e.toString())
                }
            }

        })
    }
}
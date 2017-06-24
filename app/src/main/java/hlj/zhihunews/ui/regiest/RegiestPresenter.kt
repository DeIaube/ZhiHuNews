package hlj.zhihunews.ui.regiest

import android.util.Log
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVUser
import com.avos.avoscloud.SignUpCallback
import hlj.zhihunews.R

/**
 * Created by Administrator on 2017/6/23 0023.
 */
class RegiestPresenter(var view: RegiestContract.View) : RegiestContract.Presenter(view) {
    override fun regiest(username: String, password: String, rePassword: String) {
        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            view.showMsg(R.string.username_or_password_not_empty)
            return
        }
        if (!password.equals(rePassword)) {
            view.showMsg(R.string.password_not_consistent)
            return
        }
        val user = AVUser()
        user.username = username
        user.setPassword(password)
        view.showProgress()
        user.signUpInBackground(object : SignUpCallback() {
            override fun done(e: AVException?) {
                view.hideProgress()
                if (e == null) {
                    //注册成功
                    view.regiestSuccess()
                } else {
                    //注册失败
                    view.showMsg(R.string.regiest_error)
                    Log.i("LoginPresenter", e.toString())
                }
            }
        })
    }
}
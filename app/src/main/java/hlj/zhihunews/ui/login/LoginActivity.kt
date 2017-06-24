package hlj.zhihunews.ui.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import hlj.comics.base.BaseActivity
import hlj.comics.util.toast
import hlj.zhihunews.R
import hlj.zhihunews.ui.regiest.RegisteActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity(), LoginContract.View {

    var presenter:LoginContract.Presenter ?= null
    var progressDialog:ProgressDialog ?= null

    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this, R.style.AppTheme_Dark_Dialog)
            progressDialog!!.setMessage(getString(R.string.loading))
        }
        progressDialog!!.show()
    }

    override fun hideProgress() {
        progressDialog?.dismiss()
    }

    override fun showMsg(strId: Int) {
        toast(strId)
    }

    override fun loginSuccess() {
        finish()
    }



    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        presenter = LoginPresenter(this)
        login.setOnClickListener {
            presenter!!.login(inputUsername.text.toString(), inputPassword.text.toString())
        }
        linkSignup.setOnClickListener {
            RegisteActivity.start(this)
            finish()
        }
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

}
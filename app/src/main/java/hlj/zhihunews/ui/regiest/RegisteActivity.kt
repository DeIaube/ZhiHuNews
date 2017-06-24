package hlj.zhihunews.ui.regiest

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import hlj.comics.base.BaseActivity
import hlj.comics.util.toast
import hlj.zhihunews.R
import hlj.zhihunews.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_registe.*

/**
 * Created by Administrator on 2017/6/23 0023.
 */
class RegisteActivity :BaseActivity(), RegiestContract.View {

    var progressDialog:ProgressDialog ?= null
    var presenter:RegiestContract.Presenter ?= null

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

    override fun regiestSuccess() {
        finish()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_registe
    }

    override fun init() {
        presenter = RegiestPresenter(this)
        registe.setOnClickListener {
            presenter!!.regiest(inputUsername.text.toString(),
                    inputPassword.text.toString(),
                    inputRePassword.text.toString())
        }
        linkLogin.setOnClickListener {
            LoginActivity.start(this)
            finish()
        }
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, RegisteActivity::class.java))
        }
    }
}
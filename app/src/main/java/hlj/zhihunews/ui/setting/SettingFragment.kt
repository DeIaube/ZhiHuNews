package hlj.zhihunews.ui.setting

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.avos.avoscloud.AVUser
import hlj.comics.base.BaseFragment
import hlj.comics.util.toast
import hlj.zhihunews.R
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * Created by Administrator on 2017/6/19 0019.
 */
class SettingFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_setting
    }

    override fun init() {
        super.init()
        exit.setOnClickListener {
            AlertDialog.Builder(context)
                    .setTitle(getString(R.string.prompt))
                    .setMessage("确定要退出当前账号么")
                    .setPositiveButton(getString(R.string.confirm), object :DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            AVUser.logOut()
                            toast("登出成功")
                        }

                    })
                    .setNegativeButton(getString(R.string.cancel), null)
                    .show()
        }
    }
}
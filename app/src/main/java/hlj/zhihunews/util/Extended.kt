package hlj.comics.util

import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast

/**
 * Created by Administrator on 2017/6/10 0010.
 */
fun Context.toast(msg: String): Unit {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun Context.toast(msgId: Int): Unit {
    Toast.makeText(this, getText(msgId), Toast.LENGTH_SHORT).show()
}

fun Context.log(tag:String, msg: String): Unit {
    Log.i(tag, msg)
}

fun Fragment.toast(msgId: Int): Unit {
    activity.toast(msgId)
}

fun Fragment.toast(msg: String): Unit {
    activity.toast(msg)
}

fun Fragment.log(tag: String, msg: String): Unit {
    activity.log(tag, msg)
}


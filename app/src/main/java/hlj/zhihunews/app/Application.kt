package hlj.zhihunews.app

import android.app.Application
import com.avos.avoscloud.AVOSCloud

/**
 * Created by Administrator on 2017/6/22 0022.
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        AVOSCloud.initialize(this,"queAJ7UaqolsPuMeIAqRRFB0-gzGzoHsz","tYUpFJnzJ04F8rQme8urWf2F");
    }
}
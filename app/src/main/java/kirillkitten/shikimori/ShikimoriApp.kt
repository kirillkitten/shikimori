package kirillkitten.shikimori

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ShikimoriApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashTree())
        }
    }

    class CrashTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
//            TODO Implement logic
//            if (priority < Log.INFO) return
//            FakeCrashLibrary.log(priority, tag, message);
//            if (t != null) {
//                if (priority == Log.ERROR) {
//                    FakeCrashLibrary.logError(t);
//                } else if (priority == Log.WARN) {
//                    FakeCrashLibrary.logWarning(t);
//                }
//            }
        }
    }
}

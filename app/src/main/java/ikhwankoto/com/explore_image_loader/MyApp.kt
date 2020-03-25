package ikhwankoto.com.explore_image_loader

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}
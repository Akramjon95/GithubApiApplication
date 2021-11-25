package maxcoders.uz.githubapplication

import android.app.Application
import maxcoders.uz.githubapplication.di.RetroComponent
import maxcoders.uz.githubapplication.di.RetroModule

class MyApplication: Application() {

    private lateinit var retroComponent: RetroComponent

    override fun onCreate() {
        super.onCreate()

        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetroComponent(): RetroComponent{
        return retroComponent
    }
}
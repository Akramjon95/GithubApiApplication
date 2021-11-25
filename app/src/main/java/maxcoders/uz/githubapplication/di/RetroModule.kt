package maxcoders.uz.githubapplication.di

import com.google.android.datatransport.runtime.dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetroModule {

    @Singleton
    @Provides
    fun getRetroServiceInterface(retrofit: Retrofit):RetroServiceInterface {
        return retrofit.create(RetroServiceInterface::class.java)
    }

    val baseURL = "https://api.github.com/search/"

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
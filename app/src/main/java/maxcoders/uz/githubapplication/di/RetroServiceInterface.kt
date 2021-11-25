package maxcoders.uz.githubapplication.di

import maxcoders.uz.githubapplication.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("repositories")
    fun getDataFromAPi(@Query("q")query: String): Call<RecyclerList>?
}
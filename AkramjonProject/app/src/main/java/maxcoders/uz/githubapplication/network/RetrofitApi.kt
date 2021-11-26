package maxcoders.uz.githubapplication.network

import maxcoders.uz.githubapplication.model.UserResponse
import maxcoders.uz.githubapplication.model.login.LoginResponse
import maxcoders.uz.githubapplication.model.login.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET("search/repositories")
    suspend fun searchUsers(@Query("q")query: String): Response<UserResponse>

    @GET("users/{name}")
    suspend fun loginUsers(@Path("name") login: String): Response<User>
}
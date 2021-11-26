package maxcoders.uz.githubapplication.data

import maxcoders.uz.githubapplication.network.RetrofitApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val retrofitApi: RetrofitApi) {
    suspend fun searchUsers(name:String) = retrofitApi.searchUsers(name)

    suspend fun loginUser(login: String) = retrofitApi.loginUsers(login)
}
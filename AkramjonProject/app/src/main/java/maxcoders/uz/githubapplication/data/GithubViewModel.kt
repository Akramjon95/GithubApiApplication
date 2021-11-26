package maxcoders.uz.githubapplication.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import maxcoders.uz.githubapplication.model.UserResponse
import maxcoders.uz.githubapplication.model.login.LoginResponse
import maxcoders.uz.githubapplication.model.login.User
import maxcoders.uz.githubapplication.util.Resource
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private  val repository: GithubRepository):ViewModel() {

    private val TAG = "GithubViewModel"

    val users:MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val usersLogin:MutableLiveData<Resource<User>> = MutableLiveData()


    fun     searchUser(name:String) = viewModelScope.launch {
        users.postValue(Resource.Loading())
        try {
            val response = repository.searchUsers(name)
            users.postValue(handleUsersResponse(response))
        }catch (e:Exception){
            users.postValue(Resource.Error(message = e.toString()))
        }
    }

    fun loginUser(login: String) = viewModelScope.launch {
        usersLogin.postValue(Resource.Loading())
        try {
            val response = repository.loginUser(login)
            usersLogin.postValue(handleLoginUsersResponse(response))
        } catch (e:java.lang.Exception){
            usersLogin.postValue(Resource.Error(message = e.toString()))
        }
    }



    private fun handleUsersResponse(response: Response<UserResponse>): Resource<UserResponse> {
        Log.d(TAG, "handleUsersResponse: +" + response)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(response.body()!!)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleLoginUsersResponse(response: Response<User>): Resource<User> {
        Log.d(TAG, "handleLoginResponse: +" + response)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }


}
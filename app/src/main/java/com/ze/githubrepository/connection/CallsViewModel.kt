package com.ze.githubrepository.connection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ze.githubrepository.cache.CacheLocal
import com.ze.githubrepository.extensions.doRequest
import com.ze.githubrepository.extensions.makeAsyncOperation
import com.ze.githubrepository.model.RepositoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val REPOSITORY_KEY = "github_key"

class CallsViewModel : ViewModel() {

    private val build = ApiConnection.buildService(GithubApi::class.java)
    private val call = build.getRepositories()

    /*
    Essa primeira variavel do tipo MutableLiveData, é a variável que poderá ser manipulada somente
    aqui dentro da classe Call que é a ViewModel, e a resposta que vier da API poderá somente ser acessada
    fora da classe pela variável githubResponse, e não poderá ter seu valor manipulado por nada
     */
    private val _githubResponse = MutableLiveData<List<RepositoryModel.Github>>()
    val githubReponse: LiveData<List<RepositoryModel.Github>>
        get() = _githubResponse


//    fun fetchRepositories() {
//
//        if(CacheLocal.githubRepository.containsKey(REPOSITORY_KEY)){
//            _githubResponse.postValue(CacheLocal.githubRepository[REPOSITORY_KEY]?.items)
//        } else {
//            if(_githubResponse.isEmpty()){
//                call.enqueue(object : Callback<RepositoryModel.Items> {
//                    override fun onResponse(call: Call<RepositoryModel.Items>, response: Response<RepositoryModel.Items>) {
//                        if (response.isSuccessful) {
//                            response.body()?.let {
//                                CacheLocal.githubRepository[REPOSITORY_KEY] = it
//                            }
//                            response.body()?.items?.let { listGithub ->
//                                _githubResponse.postValue(listGithub)
//                                Log.e(">>>>>>", "Fez a chamada")
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<RepositoryModel.Items>, t: Throwable) {
//                        Log.e("OPA", "Deu ruim aqui seu feio")
//                    }
//                })
//            }
//        }
//    }

//    fun fetchRepository2() {
//        build.getRepositories().makeAsyncOperation(
//            REPOSITORY_KEY,
//            CacheLocal.githubRepository,
//            onSuccess = {
//                _githubResponse.postValue(it.items)
//            }
//        )
//    }

    fun fetchRepository2() {
        build.getRepositories().doRequest(
            REPOSITORY_KEY,
            RepositoryModel.Items::class.java,
            onSuccess = { _githubResponse.postValue(it.items) }
        )
    }
}

fun <T> LiveData<T>.isEmpty() = value == null
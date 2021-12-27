package com.ze.githubrepository.connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ze.githubrepository.dao.GithubDao
import com.ze.githubrepository.extensions.doRequest
import com.ze.githubrepository.model.RepositoryEntity
import com.ze.githubrepository.model.RepositoryModel
import com.ze.githubrepository.model.RepositoryModel.Github

const val REPOSITORY_KEY = "github_key"

class CallsViewModel(private val dao: GithubDao) : ViewModel() {

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
//
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
        val repository = dao.findGithub()

        if(repository.isEmpty()) {
            build.getRepositories().doRequest(
                REPOSITORY_KEY,
                RepositoryModel.Items::class.java,
                onSuccess = {
                    dao.save(it.items.map(::RepositoryEntity))
                    _githubResponse.postValue(it.items)
                }
            )
        } else {
            _githubResponse.postValue(repository.map(::Github))
        }
    }
}

class RepositoryViewModelFactory(private val dao: GithubDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CallsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CallsViewModel(dao) as T
        }

        throw IllegalArgumentException ("Unknown ViewModel class")
    }

}

fun <T> LiveData<T>.isEmpty() = value == null
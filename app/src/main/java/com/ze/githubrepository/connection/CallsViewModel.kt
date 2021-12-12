package com.ze.githubrepository.connection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ze.githubrepository.model.Github
import com.ze.githubrepository.model.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallsViewModel : ViewModel() {

    private val build = ApiConnection.buildService(GithubApi::class.java)
    private val call = build.getRepositories()

    /*
    Essa primeira variavel do tipo MutableLiveData, é a variável que poderá ser manipulada somente
    aqui dentro da classe Call que é a ViewModel, e a resposta que vier da API poderá somente ser acessada
    fora da classe pela variável githubResponse, e não poderá ter seu valor manipulado por nada
     */
    private val _githubResponse = MutableLiveData<List<Github>>()
    val githubReponse: LiveData<List<Github>>
        get() = _githubResponse


    fun fetchRepositories() {
        if(_githubResponse.isEmpty()){
            call.enqueue(object : Callback<Items> {
                override fun onResponse(call: Call<Items>, response: Response<Items>) {
                    if (response.isSuccessful) {
                        response.body()?.items?.let { listGithub ->
                            _githubResponse.postValue(listGithub)
                        }
                    }
                }

                override fun onFailure(call: Call<Items>, t: Throwable) {
                    Log.e("OPA", "Deu ruim aqui seu feio")
                }
            })
        }
    }
}

fun <T> LiveData<T>.isEmpty() = value == null
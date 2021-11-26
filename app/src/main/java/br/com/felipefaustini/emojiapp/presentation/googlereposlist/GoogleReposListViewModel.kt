package br.com.felipefaustini.emojiapp.presentation.googlereposlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.felipefaustini.domain.models.Repos
import br.com.felipefaustini.domain.usecases.googlerepos.IGoogleReposUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.emojiapp.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class GoogleReposListViewModel @Inject constructor(
    private val googleReposUseCase: IGoogleReposUseCase
) : BaseViewModel() {

    private val _reposLiveData = MutableLiveData<List<Repos>>()
    val reposLiveData: LiveData<List<Repos>> = _reposLiveData

    var isLastPage: Boolean = false

    var currentPage = 1

    var isLoading: Boolean = false

    override fun onInit() {
        loadRepos()
    }

    fun loadRepos() {
        launchDataLoad {
            googleReposUseCase.getGoogleRepos(currentPage, PAGE_SIZE)
                .onStart { isLoading = true }
                .onCompletion { isLoading = false }
                .collect {
                    when (val result = it) {
                        is Result.Error -> {

                        }
                        is Result.Success -> {
                            val data = result.data

                            _reposLiveData.postValue(data)

                            isLastPage = data.size < PAGE_SIZE
                            currentPage++
                        }
                    }
                }
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}
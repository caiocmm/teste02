package com.example.testegit.features.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testegit.core.network.ResponseWrapper
import com.example.testegit.features.main.data.repository.MainRepository
import com.example.testegit.features.main.data.response.GitResponse
import com.example.testegit.features.main.viewState.MainViewState
import com.teste.commons.constants.Constants
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val state: MutableLiveData<MainViewState> = MutableLiveData()
    val gitList: MutableLiveData<List<GitResponse>> = MutableLiveData()
    var currentPage = 1
    private val gitListData = arrayListOf<GitResponse>()

    fun getGitList() {
        viewModelScope.launch {
            state.value = MainViewState.ShowLoading(true)

            when (val result = repository.getGitList(
                search = Constants.searchKey,
                sort = Constants.sortKeyStars,
                page = currentPage
            )) {
                is ResponseWrapper.Success -> {
                    currentPage++
                    gitListData.addAll(result.value.items)
                    gitList.value = gitListData
                }
                is ResponseWrapper.Error -> state.value = MainViewState.Error(result.error)
            }

            state.value = MainViewState.ShowLoading(false)
        }
    }
}
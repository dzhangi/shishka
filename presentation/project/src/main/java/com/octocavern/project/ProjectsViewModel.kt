package com.octocavern.project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    application: Application,
    private val getProjectsUseCase: GetProjectsUseCase,
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(ProjectsUIState())
    val state = _state.asStateFlow()

    init {
        getProjects()
        viewModelScope.launch {
            println(Thread.currentThread().name)
        }
    }

    private fun getProjects() {
        viewModelScope.launch {
            _state.value = ProjectsUIState(projects = getProjectsUseCase(), isLoading = false)
        }
    }
}
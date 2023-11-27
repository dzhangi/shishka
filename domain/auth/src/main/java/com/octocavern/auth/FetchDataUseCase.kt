package com.octocavern.auth

import com.octocavern.auth.data.DataModel
import com.octocavern.auth.data.toDataModel
import com.octocavern.data.repository.ChuckRepository
import javax.inject.Inject

/**
 * Dumb class for testing gradle and dagger dependencies
 */
class FetchDataUseCase @Inject constructor(
    private val chuckRepo: ChuckRepository
) {

    suspend operator fun invoke(): DataModel {
        return chuckRepo
            .fetchJoke()
            .toDataModel()
    }
}
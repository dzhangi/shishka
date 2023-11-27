package com.sandbox.auth

import com.sandbox.auth.data.DataModel
import com.sandbox.auth.data.toDataModel
import com.sandbox.data.repository.ChuckRepository
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
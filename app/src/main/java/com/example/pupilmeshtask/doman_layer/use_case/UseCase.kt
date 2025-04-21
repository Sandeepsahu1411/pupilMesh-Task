package com.example.pupilmeshtask.doman_layer.use_case

import com.example.pupilmeshtask.data_layer.room_db.entity.UserEntity
import com.example.pupilmeshtask.doman_layer.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repo) {

    suspend fun loginOrRegister(email: String, password: String): Boolean {
        val user = repository.getUser(email)
        return if (user == null) {
            repository.insertUser(UserEntity(email, password))
            repository.saveLoggedInUser(email)
            true
        } else if (user.password == password) {
            repository.saveLoggedInUser(email)
            true
        } else false
    }
    val loggedInUserEmail: Flow<String?> = repository.loggedInUserEmail
}

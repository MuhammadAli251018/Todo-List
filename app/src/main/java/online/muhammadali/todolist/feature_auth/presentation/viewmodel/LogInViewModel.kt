package online.muhammadali.todolist.feature_auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import online.muhammadali.todolist.common.Result
import online.muhammadali.todolist.common.Success
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.ErrorColor

class LogInViewModel : ViewModel() {
    private val _emailState = MutableStateFlow("")
    private val _passwordState = MutableStateFlow("")

    val emailState = _emailState.asStateFlow()
    val passwordState = _passwordState.asStateFlow()

    private val _showPasswordState = MutableStateFlow(false)
    private val _emailLabelState = MutableStateFlow("Email")
    private val _passwordLabelState = MutableStateFlow("Password")
    private val _emailLabelColorState = MutableStateFlow(DarkWhite)
    private val _passwordLabelColorState = MutableStateFlow(DarkWhite)

    val showPasswordState = _showPasswordState.asStateFlow()
    val emailLabelState = _emailLabelState.asStateFlow()
    val passwordLabelState = _passwordLabelState.asStateFlow()
    val emailLabelColorState = _emailLabelColorState.asStateFlow()
    val passwordLabelColorState = _passwordLabelColorState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        viewModelScope.launch {
            validateEmail(newEmail).collectLatest { valid ->
                if (valid) {
                    _emailLabelState.emit("Email")
                    _emailLabelColorState.emit(DarkWhite)
                }
                else {
                    _emailLabelState.emit("Enter a valid Email")
                    _emailLabelColorState.emit(ErrorColor)
                }
                _emailState.emit(newEmail)
            }
        }
    }

    fun onPasswordChange(newPassword: String) {
        viewModelScope.launch {
            _passwordState.emit(newPassword)
        }
    }

    fun onForgotPasswordClicked() {
        //  TODO NAVIGATE
    }

    fun onLogInButtonClicked() {
        viewModelScope.launch {
            authenticateUser(
                email = emailState.value,
                password = passwordState.value
            ).collectLatest {
                //  TODO HANDLE THE RESPONSE
            }
        }
    }

    fun onSignWithGoogleClicked() {
        viewModelScope.launch {
            registerWithGoogle().collectLatest {
                //  TODO HANDLE THE RESULT
            }
        }
    }
    fun onSignUpClicked() {
        //  TODO
    }
    fun onShowPasswordClicked() {
        viewModelScope.launch {
            val value = showPasswordState.value
            _showPasswordState.emit(!value)
        }
    }

    private fun validateEmail(email: String): StateFlow<Boolean> {
        return MutableStateFlow(true)
    }
    private fun authenticateUser(email: String, password: String): StateFlow<Result<Unit>> {
        return MutableStateFlow(Success(Unit))

    }
    private fun registerWithGoogle(): StateFlow<Success<Unit>> {
        return MutableStateFlow(Success(Unit))
    }

}
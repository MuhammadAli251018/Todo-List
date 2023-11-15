package online.muhammadali.todolist.feature_auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import online.muhammadali.todolist.common.Success
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.ErrorColor
import online.muhammadali.todolist.feature_auth.presentation.screens.AuthScreen

enum class PasswordValidationResult (val message: String) {
    PasswordLengthMin("Enter password at least 8"),
    PasswordChars("Enter password using a-z, A-Z, 0-9 and symbols: @#$"),
    PasswordLengthMax("Password maximum length is 16"),
    Valid("")
}


class SignUpViewModel : ViewModel() {

    private var allowRegisterButton = false

    private val _fullNameState = MutableStateFlow("")
    private val _emailState = MutableStateFlow("")
    private val _passwordState = MutableStateFlow("")

    val fullNameState = _fullNameState.asStateFlow()
    val emailState = _emailState.asStateFlow()
    val passwordState = _passwordState.asStateFlow()


    private val _showPasswordState = MutableStateFlow(false)
    private val _nameLabelState = MutableStateFlow("Full Name")
    private val _emailLabelState = MutableStateFlow("Email")
    private val _passwordLabelState = MutableStateFlow("Password")
    private val _nameLabelColorState = MutableStateFlow(DarkWhite)
    private val _emailLabelColorState = MutableStateFlow(DarkWhite)
    private val _passwordLabelColorState = MutableStateFlow(DarkWhite)

    val showPasswordState = _showPasswordState.asStateFlow()
    val nameLabelState = _nameLabelState.asStateFlow()
    val emailLabelState = _emailLabelState.asStateFlow()
    val passwordLabelState = _passwordLabelState.asStateFlow()
    val nameLabelColorState = _nameLabelColorState.asStateFlow()
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
                    _emailLabelState.emit("Enter valid email")
                    _emailLabelColorState.emit(ErrorColor)
                }
                _emailState.emit(newEmail)
            }
        }
    }

    fun onNameChange(newName: String) {
        viewModelScope.launch {
            validateFullName(newName).collectLatest { valid ->
                if (valid) {
                    _nameLabelState.emit("Full Name")
                    _nameLabelColorState.emit(DarkWhite)
                }
                else {
                    _nameLabelState.emit("Enter your full name with English characters only")
                    _emailLabelColorState.emit(ErrorColor)
                }

                _fullNameState.emit(newName)
            }
        }
    }

    fun onPasswordChange(newPassword: String) {
        viewModelScope.launch {
            validatePassword(newPassword).collectLatest { validationResult ->

                when(validationResult) {
                    PasswordValidationResult.Valid -> {
                        _passwordLabelState.emit("Password")
                        _passwordLabelColorState.emit(DarkWhite)
                    }

                    PasswordValidationResult.PasswordChars -> {
                        _passwordLabelState.emit(validationResult.message)
                        _passwordLabelColorState.emit(DarkWhite)
                    }

                    PasswordValidationResult.PasswordLengthMin -> {
                        _passwordLabelState.emit(validationResult.message)
                        _passwordLabelColorState.emit(DarkWhite)
                    }

                    PasswordValidationResult.PasswordLengthMax -> {
                        _passwordLabelState.emit(validationResult.message)
                        _passwordLabelColorState.emit(DarkWhite)
                    }
                }

                _passwordState.emit(newPassword)
            }
        }
    }

    fun onForgotPasswordClicked() {
        //  TODO NAVIGATE
    }

    fun onCreateAccountClicked() {
        if (allowRegisterButton) {
            viewModelScope.launch {
                registerNewUser(
                    name = fullNameState.value,
                    email = emailState.value,
                    password = passwordState.value
                ).collectLatest {
                    //  TODO HANDLE THE RESPONSE
                }
            }
        }
        else {
            //  TODO IF INFORMATION IS NOT READY PROMPT USER
        }
    }

    fun onSignWithGoogleClicked() {
        viewModelScope.launch {
            registerWithGoogle().collectLatest {
                //  TODO HANDLE THE RESULT
            }
        }
    }
    fun onLogInClicked() {
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
    private fun validateFullName(name: String): StateFlow<Boolean> {
        return MutableStateFlow(true)

    }
    private fun validatePassword(password: String): StateFlow<PasswordValidationResult> {
        return MutableStateFlow(PasswordValidationResult.Valid)
    }
    private fun registerNewUser(
        name: String,
        email: String,
        password: String
    ): StateFlow<Success<Unit>> {
        return MutableStateFlow(Success(Unit))

    }

    private fun registerWithGoogle(): StateFlow<Success<Unit>> {
        return MutableStateFlow(Success(Unit))

    }
}
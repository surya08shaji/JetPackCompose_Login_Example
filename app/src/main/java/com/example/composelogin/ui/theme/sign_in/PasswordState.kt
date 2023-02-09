package com.example.composelogin.ui.theme.sign_in


//private const val PASSWORD_VALIDATION_REGEX =
//    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$"


class PasswordState : TextFieldState(
    validator = ::isPasswordValid,
    errorMessage = { passwordErrorMessage() }
)

private fun isPasswordValid(password: String) = password.length >= 4

private fun passwordErrorMessage() = "Password is invalid"





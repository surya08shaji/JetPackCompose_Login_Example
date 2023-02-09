package com.example.composelogin.ui.theme.sign_in

import java.util.regex.Pattern


private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class EmailState : TextFieldState(
    validator = ::isEmailValid,
    errorMessage = ::emailErrorMessage
)

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}

private fun emailErrorMessage(email: String) = "Email $email is invalid."
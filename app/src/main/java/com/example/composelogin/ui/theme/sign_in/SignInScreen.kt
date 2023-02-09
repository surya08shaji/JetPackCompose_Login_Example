package com.example.composelogin.ui.theme.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.R
import com.example.composelogin.ui.theme.darkBlue

@Preview(showSystemUi = true)
@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {

//email error handling
        val emailState = remember { EmailState() }
        Email(emailState.text.trim(), emailState.error) {
            emailState.text = it
            emailState.validate()
        }
//password error handling
        val passwordState = remember { PasswordState() }
        Password(passwordState.text.trim(), passwordState.error) {
            passwordState.text = it
            passwordState.validate()
        }
        SignInButton(enable = emailState.isValid() && passwordState.isValid())
    }
}


@Composable
fun Email(email: String, error: String?, onEmailChanged: (String) -> Unit) {

    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { onEmailChanged(it) },
            label = { Text(text = "Mobile Number") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(9.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = error != null,
        )
    }
    error?.let { ErrorField(it) }
}

//email error msg
@Composable
fun ErrorField(error: String) {
    Text(
        text = error,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(color = MaterialTheme.colors.error)
    )
}

@Composable
fun Password(password: String, error: String?, onPasswordChanged: (String) -> Unit) {
    val showPassword = remember {
        mutableStateOf(false)
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = password, onValueChange = { onPasswordChanged(it) },
        label = { Text(text = "Password") },
//        label = { Text(text = stringResource(id = R.string.password_hint)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(9.dp),
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = stringResource(R.string.hide_password),
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(R.string.show_password)
                    )
                }
            }
        },
        isError = error != null
    )
    error?.let { ErrorField(it) }
}

@Composable
fun SignInButton(enable: Boolean) {
    Button(
        onClick = {
        },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = darkBlue,
            contentColor = Color.White
        ),
        enabled = enable,
        shape = RoundedCornerShape(9.dp)
    ) {
        Text(text = stringResource(id = R.string.sign_in_txt))
    }

}
package com.codelabs.foodapp.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.codelabs.foodapp.R
import com.codelabs.foodapp.core.presentation.UiEvent
import com.codelabs.foodapp.feature_auth.domain.usecases.LoginUseCase
import com.codelabs.foodapp.navigation.NavigationItem
import com.codelabs.foodapp.ui.theme.FoodAppTheme
import com.codelabs.foodapp.util.SpaceLarge
import com.codelabs.foodapp.util.SpaceMedium
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun LoginScreenPreview(){
    FoodAppTheme {
        LoginScreen(null,LoginViewModel(LoginUseCase()))
    }
}
@Composable
fun LoginScreen(
    navController: NavHostController?,
    viewModel:LoginViewModel
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest {event->
            when(event){
                is UiEvent.ShowSnackBar->{

                }
                is UiEvent.OnLogin->{
                    navController?.navigate(NavigationItem.Menu.route)
                }

                else -> {}
            }

        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = 50.dp
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(

                text = stringResource(id = R.string.login_label),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
//            EmailField(viewModel)
            OutlinedTextField(
                value = emailState.text,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                },
                label={Text("Enter Email")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
//            PasswordField(viewModel)
            OutlinedTextField(
                value = passwordState.text,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                label = {Text("Enter Password")},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(onClick = {
                viewModel.onEvent(LoginEvent.Login)
            }) {
                Text(text = "Submit")

            }
        }

    }

}
@Composable
fun PasswordField(viewModel: LoginViewModel){
    var password by remember{ mutableStateOf("") }
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
        },
        label = {Text("Enter Password")},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
@Composable
fun EmailField(viewModel: LoginViewModel){
    var email by remember{ mutableStateOf("") }

    OutlinedTextField(
        value = email,
        onValueChange = {
            email = it
        },
        label={Text("Enter Email")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}
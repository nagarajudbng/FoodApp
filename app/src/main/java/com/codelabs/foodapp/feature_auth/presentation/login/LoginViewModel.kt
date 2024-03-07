package com.codelabs.foodapp.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.foodapp.core.domain.states.PaswordFieldState
import com.codelabs.foodapp.core.domain.states.StandardTextFieldState
import com.codelabs.foodapp.core.presentation.UiEvent
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.core.util.UiText
import com.codelabs.foodapp.feature_auth.domain.model.LoginResult
import com.codelabs.foodapp.feature_auth.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class LoginViewModel constructor(
    private val loginUseCase: LoginUseCase
) :ViewModel(){

    private val _emailState = mutableStateOf(StandardTextFieldState("vamsi@gmail.com"))
    val emailState: State<StandardTextFieldState> = _emailState

    private val _passwordState = mutableStateOf(PaswordFieldState("Vamsi@1289"))
    val passwordState:State<PaswordFieldState> = _passwordState

    private val _loginState = mutableStateOf(LoginState())
    val loginState:State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event:LoginEvent){
        when(event){
            is LoginEvent.EnteredEmail->{
                _emailState.value = emailState.value.copy(
                    text = event.email
                )
            }
            is LoginEvent.EnteredPassword->{
                _passwordState.value = passwordState.value.copy(
                    text = event.password
                )
            }
            is LoginEvent.TogglePasswordVisibility->{
                _loginState.value = loginState.value.copy(
                    isPasswordVisible = !loginState.value.isPasswordVisible
                )
            }
            is LoginEvent.Login->{
                viewModelScope.launch {
                    _loginState.value = loginState.value.copy(isLoading = true)
                    val loginResult = loginUseCase(
                        email = emailState.value.text,
                        password = _passwordState.value.text
                    )
                    _loginState.value = loginState.value.copy(isLoading = false)
                    if(loginResult.emailError!=null){
                        _emailState.value = emailState.value.copy(
                            error= loginResult.emailError
                        )
                    }
                    if(loginResult.passwordError!=null){
                        _passwordState.value = passwordState.value.copy(
                            error= loginResult.passwordError
                        )
                    }
                    when(loginResult.result){
                        is Resource.Success->{
                           _eventFlow.emit(UiEvent.OnLogin)
                        }
                        is Resource.Error->{
                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(
                                    loginResult.result.uiText?: UiText.unknwonError()
                                )
                            )
                        }

                        else -> {}
                    }
                }

            }
        }
    }
}
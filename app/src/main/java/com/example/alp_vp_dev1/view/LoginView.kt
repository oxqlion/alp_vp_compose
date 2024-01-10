package com.example.alp_vp_dev1.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp_dev1.R
import com.example.alp_vp_dev1.data.DataStoreManager
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.ui.theme.DarkGrey
import com.example.alp_vp_dev1.ui.theme.IjoButton
import com.example.alp_vp_dev1.viewmodel.LoginViewModel
import kotlin.math.round


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    loginViewModel: LoginViewModel,
    navController: NavController,
    dataStore: DataStoreManager
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordValid by rememberSaveable { mutableStateOf(true) }


    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(text = "Email")
                    },
                    placeholder = {
                        Text(text = "Email")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color(0xFFD0FF00),
                        focusedBorderColor = Color(0xFFD0FF00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                CustomPasswordField(
                    value = password,
                    onValueChanged = { password = it },
                    text = "Password",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    isPasswordValid = isPasswordValid
                )

                Button(
                    onClick = {
                        val user = User(
                            email = email,
                            password = password
                        )
                        loginViewModel.Login(user, navController, dataStore)
                        Log.d("haloo", "mas")
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Login",
                        color = Color.Black
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                    text = "Atau login dengan",
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Image(
                    painter = painterResource(id = R.drawable.screenshot_2024_01_10_082230),
                    contentDescription = "more login option",
                    Modifier.width(300.dp),
                    contentScale = ContentScale.FillWidth
                )

                Row{
                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "Belum memiliki akun?",
                        fontSize = 13.sp
                    )

                    TextButton(onClick = {
                        navController.navigate(ListScreen.Register.name)
                    }) {
                        Text(
                            modifier = Modifier.padding(top = 18.dp),
                            text = "Register",
                            color = Color(0xFFD0FF00)
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isPasswordValid: Boolean
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text) },
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFD0FF00),
            unfocusedBorderColor = Color(0xFFD0FF00)
        ),
        modifier = modifier,
        isError = !isPasswordValid,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        },
    )

    if (!isPasswordValid) {
        Text(
            text = "Password must be 8 characters long and contain uppercase, lowercase, number, and special character",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
            Color.Red
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginPreivew() {
//    LoginView()
}
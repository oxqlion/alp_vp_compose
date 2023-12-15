package com.example.alp_vp_dev1.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp_dev1.R
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView() {

    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var confirmPassword by remember { mutableStateOf("") }
    var isEmailValid by rememberSaveable { mutableStateOf(true) }
    var isPasswordValid by rememberSaveable { mutableStateOf(true) }
    var showPasswordMismatchError by remember { mutableStateOf(false) }

    val validatePasswords = {
        showPasswordMismatchError = password.isNotEmpty() && confirmPassword.isNotEmpty() && password != confirmPassword
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Register",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(text = "Full Name")
                    },
                    placeholder = {
                        Text(text = "Full Name")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color(0xFF333138),
                        focusedBorderColor = Color(0xFFD0FF00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = phone,
                    onValueChange = { phone = it },
                    label = {
                        Text(text = "Phone")
                    },
                    placeholder = {
                        Text(text = "Phone")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color(0xFF333138),
                        focusedBorderColor = Color(0xFFD0FF00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                CustomEmailField(
                    value = email,
                    onValueChanged = { email = it },
                    text = "email",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    isEmailValid = isEmailValid
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

                CustomPasswordField(
                    value = confirmPassword,
                    onValueChanged = {
                        confirmPassword = it
                        validatePasswords()
                                     },
                    text = "Confirm Password",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    isPasswordValid = !showPasswordMismatchError
                )
                if (showPasswordMismatchError) {
                    Text(
                        text = "Passwords do not match",
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                    )
                }

                Button(
                    onClick = {
                        isEmailValid = isValidEmail(email)
                        isPasswordValid = isValidPassword(password)

                        if (isEmailValid && isPasswordValid) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Data $name saved")
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .height(50.dp)
                        .shadow(18.dp, shape = RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(4.dp),
                    enabled = name.isNotBlank() && phone.isNotBlank() && email.isNotBlank() && password.isNotBlank(),
                ) {
                    Text(
                        text = "Register",
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .absolutePadding(left = 20.dp)
                            .width(100.dp)
                    )
                    Text(
                        text = "Atau register dengan",
                        color = Color.LightGray
                    )
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .absolutePadding(right = 20.dp)
                            .width(100.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .width(200.dp)
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .height(50.dp)
                            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(5.dp)),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "Google"
                        )
                        Text(
                            text = "Google",
                            color = Color.Black
                        )
                    }
                    Button(
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .width(200.dp)
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .height(50.dp)
                            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(5.dp)),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.facebook_icon),
                            contentDescription = "Facebook",
                            modifier = Modifier
                                .clip(shape = CircleShape)
                        )
                        Text(
                            text = "Facebook",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterPreivew() {
    RegisterView()
}
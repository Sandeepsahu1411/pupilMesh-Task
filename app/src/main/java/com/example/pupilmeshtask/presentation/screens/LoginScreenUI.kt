package com.example.pupilmeshtask.presentation.screens


import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pupilmeshtask.R
import com.example.pupilmeshtask.navigation.HomeScreenRoute
import com.example.pupilmeshtask.navigation.LoginScreenRoute
import com.example.pupilmeshtask.presentation.viewmodel.AppViewModel


@Composable
fun LoginScreenUI(navController: NavController, viewModel: AppViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val loginResult by viewModel.loginResult.collectAsState()
    Row {
        val activity = LocalContext.current as? Activity
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.clickable { activity?.finish() }
        )
        Spacer(Modifier.width(15.dp))
        Text(text = "Sign In", color = Color.LightGray)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.DarkGray,
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Zenithra",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Welcome Back",
                        fontSize = 28.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Please enter your detail to sign in",
                        fontSize = 12.sp,
                        color = Color.LightGray,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.LightGray, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.google_icon),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Spacer(Modifier.width(15.dp))
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.LightGray, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.apple_icon),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
                OrDivider()
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Your Email Address", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.LightGray),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                    )

                )
                var passwordVisibility by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Password", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.LightGray),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),

                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = icon,
                                contentDescription = if (passwordVisibility) "Hide password" else "Show password",
                                tint = Color.LightGray
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                    )
                )
                Text(
                    text = "Forgot password?",
                    color = Color.Cyan,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline
                )
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Gray),
                    onClick = {
                        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,3}\$")

                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(
                                context,
                                "All fields required",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }
                        if (!emailPattern.matches(email)) {
                            Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        viewModel.login(email, password)

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sign In")
                }
                Text(
                    buildAnnotatedString
                    {
                        append("Don't have an account?")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" Sign up")
                        }
                    },
                    color = Color.LightGray,
                    fontSize = 14.sp,

                    )
            }
        }

    }

    loginResult?.let {
        if (it) {
            LaunchedEffect(Unit) {
                navController.navigate(HomeScreenRoute) {
                    popUpTo(LoginScreenRoute) { inclusive = true }
                }
            }
        } else {
            Toast.makeText(LocalContext.current, "Invalid password", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun OrDivider(
    text: String = "OR",
    color: Color = Color.LightGray
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = color,
            thickness = 1.dp
        )
        Text(
            text = text.uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = color,
            thickness = 1.dp
        )
    }
}

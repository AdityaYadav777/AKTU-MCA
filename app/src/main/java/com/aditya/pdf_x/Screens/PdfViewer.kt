package com.aditya.pdf_x.Screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aditya.pdf_x.Utils
import com.aditya.pdf_x.ViewModels.HomeViewModel
import com.rajat.pdfviewer.compose.PdfRendererViewCompose
import kotlinx.coroutines.launch


@Composable
fun PdfViewer(HomeViewModel: HomeViewModel) {


    val url = Utils.url ?: ""
    myPdf2(url, HomeViewModel)

}


@Composable
fun myPdf2(url: String, HomeViewModel: HomeViewModel) {

    var state = remember {
        mutableStateOf(false)
    }


    if (state.value) {
        MyBottomSheet(

            onClick = {
                state.value = it
            },
            HomeViewModel

        )
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(60.dp),
                onClick = {
                    state.value = !state.value
                },
                containerColor = Color.Black,
                shape = RoundedCornerShape(100),
            ) {

                Text("A.I.", color = Color.White, fontSize = 22.sp)
            }
        }


    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()

            PdfRendererViewCompose(
                url = url,
            )

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(onClick: (Boolean) -> Unit, homeViewModel: HomeViewModel) {

    var message by remember { mutableStateOf("") }
    val isLoading by homeViewModel.isLoading.collectAsStateWithLifecycle()
    val response by homeViewModel.aiResponse.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onClick(false) },
        containerColor = Color(0xFF121212), // Dark mode aesthetic
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp) // Rounded top
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .background(
                    Color(0xFF1E1E1E),
                    shape = RoundedCornerShape(13.dp)
                ), // Rounded container
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ✨ Animated Text Input & Button Row
            AnimatedVisibility(visible = true, enter = slideInVertically() + fadeIn()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(6.dp)
                            .weight(10f)
                            .clip(RoundedCornerShape(6.dp))
                            // Rounded input field
                            .shadow(4.dp, RoundedCornerShape(2.dp)), // Soft shadow
                        value = message,
                        onValueChange = { message = it },
                        textStyle = TextStyle(color = Color.White),
                        label = { Text("Type here...", color = Color.Gray) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Cyan,
                            unfocusedBorderColor = Color.Gray
                        )
                    )

                    Spacer(Modifier.width(10.dp))

                    // ✨ Animated Send Button
                    AnimatedVisibility(visible = message.isNotEmpty(), enter = scaleIn()) {
                        Button(
                            modifier = Modifier
                                .weight(2.4f)
                                .clip(RoundedCornerShape(16.dp)) // Rounded button
                                .shadow(4.dp, RoundedCornerShape(16.dp)), // Soft shadow
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                            onClick = {
                                if (message.isNotEmpty()) {
                                    scope.launch {
                                        homeViewModel.getAiResponse(message)
                                        message = ""
                                    }
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Send,
                                contentDescription = "Send",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // 🔄 Loading Indicator with Animation
            AnimatedVisibility(visible = isLoading, enter = fadeIn() + scaleIn()) {
                CircularProgressIndicator(color = Color.Cyan)
            }


            if (response.isEmpty()) {
                Text(
                    "👋 Hi! How can I help you?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {

                LazyColumn(
//                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    items(response) { text ->
                        AnimatedMessageItem(text) // 🆕 Animated AI response
                    }
                }

                Log.d("LOLO", response.toString())

                // 📜 Auto Scroll to Latest Message
                LaunchedEffect(response) {
                    listState.animateScrollToItem(response.size - 1)
                }
            }
        }
    }
}


@Composable
fun AnimatedMessageItem(text: String) {
    val scaleAnim by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "scaleAnimation"
    )

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp)) // Rounded message bubbles
            .background(Color(0xFF262626)) // Dark gray background
            .scale(scaleAnim)
            .padding(12.dp)
    ) {

        val annotatedText = buildAnnotatedString {
            val lines = text.split("\n") // Split lines for processing

            lines.forEachIndexed { index, line ->
                when {
                    line.startsWith("##") -> { // Heading (##)
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        ) {
                            append(line.replace("##", "").trim() + "\n\n")
                        }
                    }

                    line.startsWith("*") -> { // Bullet Point (*)
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                            append("• " + line.replace("*", "").trim() + "\n")
                        }
                    }

                    line.startsWith("**") -> { // Bold Text (**)
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(line.replace("**", "").trim() + "\n")
                        }
                    }

                    else -> { // Normal Text
                        append(line + "\n")
                    }
                }

                // Add spacing after each line
                if (index < lines.size - 1) append("\n")
            }
        }

        Text(
            text = annotatedText,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}





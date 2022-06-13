package com.mihir.imagesbymihir


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Animatable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import kotlinx.coroutines.delay
import java.net.URLEncoder


class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : ImagesViewModel by viewModels()

        val list = viewModel.images



        setContent{

            var dot1 by remember {
                mutableStateOf(false)
            }
            var dot2 by remember {
                mutableStateOf(false)
            }
            var dot3 by remember {
                mutableStateOf(false)
            }

            val keyboardController = LocalSoftwareKeyboardController.current

            LaunchedEffect(Unit) {
                while(true) {
                    dot1=true
                    delay(300)
                    dot2=true
                    delay(300)
                    dot3=true
                    delay(300)
                    dot1 =  false
                    dot2 = false
                    dot3 = false
                    delay(300)
                }
            }


            Theme{
                var searchQuery by remember {
                    mutableStateOf("")
                }

                Column() {

                    Row{
                        Text(
                            text = "Images",
                            color = primary,
                            fontWeight = MaterialTheme.typography.h2.fontWeight,
                            fontSize = MaterialTheme.typography.h2.fontSize,
                            fontFamily = FontFamily.Monospace
                        )
                        if(dot1)
                        {
                            Text(
                                text = ".",
                                color = primary,
                                fontWeight = MaterialTheme.typography.h2.fontWeight,
                                fontSize = MaterialTheme.typography.h2.fontSize,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                        if(dot2)
                        {
                            Text(
                                text = ".",
                                color = primary,
                                fontWeight = MaterialTheme.typography.h2.fontWeight,
                                fontSize = MaterialTheme.typography.h2.fontSize,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                        if(dot3)
                        {
                            Text(
                                text = ".",
                                color = primary,
                                fontWeight = MaterialTheme.typography.h2.fontWeight,
                                fontSize = MaterialTheme.typography.h2.fontSize,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                    }
                    Text(
                        "By Mihir", color = primary,
                        fontWeight = MaterialTheme.typography.body1.fontWeight,
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        fontFamily = FontFamily.Monospace
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(4f),
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primary,
                                backgroundColor = background
                            )
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(2f),
                            onClick = {
                                keyboardController?.hide()
                                viewModel.add(searchQuery)
                            }
                        ) {
                            Text(
                                text = "Search ",
                                color = background,
                                fontWeight = MaterialTheme.typography.h6.fontWeight,
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){

                        item{
                            list.forEach{
                                Box(modifier = Modifier
                                    .border(BorderStroke(2.dp, primary))
                                    .fillMaxWidth(0.98f)
                                    .fillParentMaxHeight(0.5f)
                                    .background(primary)
                                    ,
                                    contentAlignment = Center
                                )
                                {
                                    AsyncImage(
                                        modifier = Modifier.fillMaxSize(),
                                        model = it,
                                        contentDescription ="",
                                        contentScale = ContentScale.FillHeight
                                    )
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    }


                }
            }
        }


    }

}

package com.example.ktorandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktorandroid.data.dto.PostResponse
import com.example.ktorandroid.data.remote.PostApi
import com.example.ktorandroid.ui.theme.KtorAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //should be inside viewModel with dependency injection
        val service = PostApi.create()

        super.onCreate(savedInstanceState)
        setContent {

            val post = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPost()
                })

            KtorAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(post.value)
                }
            }
        }
    }
}

@Composable
fun Greeting(posts: List<PostResponse>) {
    LazyColumn {
        items(posts.size) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(modifier = Modifier.fillMaxWidth(), text = posts[it].title, fontSize = 15.sp)
                Spacer(Modifier.height(4.dp))
                Text(modifier = Modifier.fillMaxWidth(), text = posts[it].body, fontSize = 13.sp)
            }
        }
    }
}

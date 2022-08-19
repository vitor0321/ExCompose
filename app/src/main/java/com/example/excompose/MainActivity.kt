package com.example.excompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excompose.ui.theme.ComposeBasisTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasisTheme {
                FeedItem()
            }
        }
    }
}


@Composable
fun FeedItem() {
    Column {
        Box {
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = "Marvel logo",
                modifier = Modifier
                    .clip(RectangleShape)
            )
            Text(
                text = "26:15",
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp, 2.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Chanal image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Column {
                DetailText("Jetpack Compose - Ui Android",16)
                Spacer(modifier = Modifier.size(8.dp))
                Row {
                    DetailText("Douglas Motta",12)
                    DetailText(" | 247 views ", 12)
                    DetailText(" | 2 weeks ago",12)
                }
            }
        }
    }
}

@Composable
fun DetailText(text: String, fontSize: Int) {
    Text(
        text = text,
        color = Color.White,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Light
    )
}

@Preview
@Composable
fun DefaultPreview() {
    ComposeBasisTheme {
        FeedItem()
    }
}

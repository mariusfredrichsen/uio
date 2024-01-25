package no.uio.ifi.in2000.mafredri.truefalsequiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import no.uio.ifi.in2000.mafredri.truefalsequiz.ui.theme.TrueFalseQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrueFalseQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizScreen()
                }
            }
        }
    }
}

@Composable
fun QuizScreen() {
    val quizes: MutableList<Quiz> = listOf<Quiz>().toMutableList()
    quizes.add(
        Quiz(
            imageURL = "https://upload.wikimedia.org/wikipedia/en/9/96/Pink_Panther.png",
            quizQuestion = "Er denne karakteren blå?",
            questionAnswer = false
        )
    )
    quizes.add(
        Quiz(
            imageURL = "https://upload.wikimedia.org/wikipedia/en/thumb/3/3d/Perry_Platypus.png/220px-Perry_Platypus.png",
            quizQuestion = "Er dette et nebbdyr?",
            questionAnswer = true
        )
    )
    quizes.add(
        Quiz(
            imageURL = "https://upload.wikimedia.org/wikipedia/en/a/a9/MarioNSMBUDeluxe.png",
            quizQuestion = "Er dette Luigi",
            questionAnswer = false
        )
    )
    var quizProgress by remember { mutableIntStateOf(0) }
    var quizScore by remember { mutableIntStateOf(0) }
    var currentQuiz by remember { mutableStateOf(quizes[quizProgress]) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier.size(300.dp),
            model = currentQuiz.imageURL,
            contentDescription = null
        )
        Text(text = currentQuiz.quizQuestion, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        Row {
            Button(onClick = {
                if (currentQuiz.questionAnswer) {
                    quizScore++
                }
                currentQuiz = quizes[++quizProgress]
            }) {
                Text(text = "Sant", modifier = Modifier.padding(8.dp))
            }
            Button(onClick = {
                if (!currentQuiz.questionAnswer) {
                    quizScore++
                }
                currentQuiz = quizes[++quizProgress]
            } ) {
                Text(text = "Usant", modifier = Modifier.padding(8.dp))
            }
        }
        Row {
            Text(text = "Quiz score: $quizScore", modifier = Modifier.padding(8.dp))
            Text(text = "Quiz number: $quizProgress", modifier = Modifier.padding(8.dp))
        }
    }
}

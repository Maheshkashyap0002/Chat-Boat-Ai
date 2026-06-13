package premium.mahesh.chatgpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import premium.mahesh.chatgpt.ui.theme.ChatGptTheme

class MainActivity : ComponentActivity() {

    private val chatViewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatGptTheme {
                ChatPageScreen(viewModel = chatViewModel)
            }
        }
    }
}

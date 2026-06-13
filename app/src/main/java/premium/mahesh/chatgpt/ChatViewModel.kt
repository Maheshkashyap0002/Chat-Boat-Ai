package premium.mahesh.chatgpt

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val messageList = mutableStateListOf<MessageModel>()

    private val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-3.5-flash" ,
        apiKey = Constant.apikey
    )

    fun sendMessage(question : String){
        if (question.isBlank()) return
        
        viewModelScope.launch {
            try {
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role) { text(it.message) }
                    }
                )

                messageList.add(MessageModel(question, "user"))

                val response = chat.sendMessage(question)
                response.text?.let {
                    messageList.add(MessageModel(it, "model"))
                }
            } catch (e: Exception) {
                messageList.add(MessageModel("Error: " + e.message, "model"))
                Log.e("ChatViewModel", "Error sending message", e)
            }
        }
    }
}

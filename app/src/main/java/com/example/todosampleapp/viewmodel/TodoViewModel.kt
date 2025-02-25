
import com.example.todosampleapp.model.Todo


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todosampleapp.model.TodosApi
import kotlinx.coroutines.launch



sealed interface TodoUiState {
    data class Success(val todos: List<Todo>) : TodoUiState
    object Error : TodoUiState
    object Loading : TodoUiState
}
class TodoViewModel : ViewModel() {
    var todoUiState: TodoUiState by mutableStateOf(TodoUiState.Loading)
        private set

    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            try {
                val todosApi = TodosApi.getInstance()
                val todos = todosApi.getTodos()
                todoUiState = TodoUiState.Success(todos)
            } catch (e: Exception) {
                todoUiState = TodoUiState.Error
            }
        }
    }
}
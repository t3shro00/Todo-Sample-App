package com.example.todosampleapp.ui

import TodoList
import TodoUiState
import TodoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todosampleapp.ui.theme.TodoSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoSampleAppTheme {
                // Use Scaffold for a basic Material Design layout
                    // Pass innerPadding to TodoScreen
                    TodoApp()

            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(todoViewModel: TodoViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todos") }
            )
        }
    ) { innerPadding ->
        TodoScreen(Modifier.padding(innerPadding), todoViewModel.todoUiState)
    }
}

@Composable
fun TodoScreen(modifier: Modifier, uiState: TodoUiState) {
    when (uiState) {
        is TodoUiState.Loading -> LoadingScreen()
        is TodoUiState.Success -> TodoList(modifier, uiState.todos)
        is TodoUiState.Error -> ErrorScreen()

        else -> {
            Text("Unknown state")
        }
    }
}

@Composable
fun LoadingScreen() {
    Text("Loading...")
}
@Composable
fun ErrorScreen() {
    Text("Error retrieving data from API.")
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoSampleAppTheme {
        // Preview TodoScreen with sample data
        TodoApp()
    }
}
package com.example.search_auto_complete_feature.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAutoComplete(
    viewModel: SearchViewModel = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }

    val uiState by viewModel.state.collectAsState()
    val query by viewModel.query.collectAsState()

    LaunchedEffect(query, uiState.suggestions, uiState.isLoading) {
        expanded = query.isNotBlank()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        ExposedDropdownMenuBox(
            expanded = expanded, onExpandedChange = { expanded = it }) {

            OutlinedTextField(
                value = query,
                onValueChange = {
                    viewModel.sendIntent(SearchIntent.OnQueryChanged(it))
                },
                label = { Text("Enter city name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryEditable,
                        enabled = true
                    ),
                singleLine = true,
                trailingIcon = {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp)
                        )
                    }
                })
            if (query.isNotBlank())
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    if (query.isNotBlank() && uiState.suggestions.isEmpty() && !uiState.isLoading) DropdownMenuItem(
                        text = { Text("Search \"$query\"") },
                        onClick = { },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    ) else if (uiState.isLoading) DropdownMenuItem(
                        text = { Text("Loading...") },
                        onClick = { },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    ) else uiState.suggestions.forEach { selection ->
                        DropdownMenuItem(
                            text = { Text(selection) },
                            onClick = { expanded = false },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
        }
    }
}

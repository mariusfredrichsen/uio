package no.uio.ifi.in2000.mafredri.oblig1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TextUIState(
    val text: String = ""
)

class PalindromeViewModel: ViewModel() {
    private val _textUIState = MutableStateFlow(TextUIState())
    val textUIState: StateFlow<TextUIState> = _textUIState.asStateFlow()

    fun update(newText: String) {
        viewModelScope.launch {
            _textUIState.update {
                it.copy(
                    text = newText
                )
            }
        }
    }
}

class UnitConverterViewModel: ViewModel() {
    private val _textUIState = MutableStateFlow(TextUIState())
    val textUIState: StateFlow<TextUIState> = _textUIState.asStateFlow()

    fun update(newtext: String) {
        viewModelScope.launch {
            _textUIState.update {
                it.copy(
                    text = newtext
                )
            }
        }
    }
}
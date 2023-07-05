package uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.StartViewModel

class StartViewModelImpl  : StartViewModel, ViewModel() {

    override val openDiabetFlow = MutableSharedFlow<Unit>()
    override val openFindRiscFlow = MutableSharedFlow<Unit>()
    override val openDRFlow = MutableSharedFlow<Unit>()

    override fun openDiabetScreen() {
        viewModelScope.launch {
            openDiabetFlow.emit(Unit)
        }
    }

    override fun openFindRiscScreen() {
        viewModelScope.launch {
            openFindRiscFlow.emit(Unit)
        }
    }

    override fun openDRScreen() {
        viewModelScope.launch {
            openDRFlow.emit(Unit)
        }
    }
}
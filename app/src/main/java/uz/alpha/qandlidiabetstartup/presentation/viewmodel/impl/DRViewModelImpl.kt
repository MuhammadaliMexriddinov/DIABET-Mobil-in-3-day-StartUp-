package uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.alpha.diabetaithreecategory.utils.hasConnection
import uz.alpha.qandlidiabetstartup.domain.repository.impl.DRRepositoryImpl
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.DRViewModel
import java.io.File
import javax.inject.Inject


class DRViewModelImpl  : DRViewModel,
    ViewModel() {

    private  val repository=DRRepositoryImpl()

    override val openImageFlow = MutableSharedFlow<Unit>()

    override val progressFlow = MutableSharedFlow<Boolean>()

    override val getImageFlow = MutableSharedFlow<String>()

    override val showImageFlow = MutableSharedFlow<String>()

    override suspend fun changeImage() {
        openImageFlow.emit(Unit)
    }

    override suspend fun sendImage(file: File) {
        progressFlow.emit(true)

        if (!hasConnection()) {
            progressFlow.emit(false)
            return
        }

        viewModelScope.launch {
            repository.sendData(file).onEach {
                it.onSuccess {
                    showImageFlow.emit(it)
                }
                it.onFailure {
                showImageFlow.emit(it.message.toString())
                }
            }.launchIn(viewModelScope)
        }
    }

    override suspend fun setImage(str: String) {
        getImageFlow.emit(str)
    }

}
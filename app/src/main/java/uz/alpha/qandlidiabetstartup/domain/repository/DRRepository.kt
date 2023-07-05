package uz.alpha.qandlidiabetstartup.domain.repository

import kotlinx.coroutines.flow.Flow
import java.io.File

interface DRRepository {

    fun sendData(
      file:File
    ): Flow<Result<String>>

}
package uz.alpha.qandlidiabetstartup.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface DiseaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(data: DiseaseEntity)

    @Update
     fun update(data: DiseaseEntity)

    @Delete
     fun delete(data: DiseaseEntity)

    @Query("SELECT*FROM DiseaseEntity")
    fun getAll(): LiveData<List<DiseaseEntity>>
}
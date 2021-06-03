package fr.mastergime.issakzei.mvvmapp.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.mastergime.issakzei.mvvmapp.models.entity.Employee

@Dao
interface EmployeeDao {

    //ajout d'un employee
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployee(employee:Employee):Long

    //update emplyee
    @Update
    suspend fun updateEmployee(employee: Employee): Int

    //delete emplyee
    @Delete
    suspend fun deleteEmployee(employee: Employee): Int

    //query of delete all  emplyee from db
    @Query("DELETE FROM Employee ")
    suspend fun deleteAllEmployee(): Int

    //query of select all  emplyee from db
    @Query("SELECT * FROM Employee ")
    fun getAllEmployee(): LiveData<List<Employee>>
}
package fr.mastergime.issakzei.mvvmapp.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Employee")
data class Employee (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idEmp")
    val  id:Int,

    @ColumnInfo(name = "nameEmp")
    val name:String ,

    @ColumnInfo(name = "addressEmp")
    val address:String


    )


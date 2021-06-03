package fr.mastergime.issakzei.mvvmapp.models

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import fr.mastergime.issakzei.mvvmapp.models.dao.EmployeeDao
import fr.mastergime.issakzei.mvvmapp.models.entity.Employee

@Database(
    entities=[Employee::class],
    version=1
)
 abstract class RoomDB: RoomDatabase() {

     abstract  val employeeDao:EmployeeDao
     //creation d'une seule instance de la DB=> utilisation de companionOBject

     companion object{
         @Volatile
         private var INSTANCE: RoomDB?= null

         fun getInstance(context:Context): RoomDB{
             synchronized(this){
                 var instance= INSTANCE
                 if(instance==null){
                     instance= androidx.room.Room.databaseBuilder(
                         context, RoomDB::class.java, "EmployeeDB"
                     ).build()
                 }
                 return instance
             }
         }
     }
}
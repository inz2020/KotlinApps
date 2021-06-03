package fr.mastergime.issakzei.mvvmapp.models.repository

import fr.mastergime.issakzei.mvvmapp.models.dao.EmployeeDao
import fr.mastergime.issakzei.mvvmapp.models.entity.Employee

class EmployeeRepository(private val dao:EmployeeDao) {


    //ajout d'un employee
    suspend fun addEmployee(employee: Employee)=dao.addEmployee(employee)

    //update emplyee
    suspend fun updateEmployee(employee: Employee)= dao.updateEmployee(employee)

    //delete emplyee
    suspend fun deleteEmployee(employee: Employee)= dao.deleteEmployee(employee)

    //query of delete all  emplyee from db
    suspend fun deleteAllEmployee()= dao.deleteAllEmployee()

    //query of select all  emplyee from db

   val  getAllEmployee = dao.getAllEmployee()
}
package fr.mastergime.issakzei.mvvmapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mastergime.issakzei.mvvmapp.models.entity.Employee
import fr.mastergime.issakzei.mvvmapp.models.repository.EmployeeRepository
import kotlinx.coroutines.launch

class EmployeeViewModel(private val employeeRepository: EmployeeRepository): ViewModel() {

    //creation d'une constante où on va affecter la liste des employees

    val employees= employeeRepository.getAllEmployee

   fun addEmployee(employee: Employee) {
       viewModelScope.launch {
           val newIdEmployee = employeeRepository.addEmployee(employee)}

       }

       fun updateEmployee(employee: Employee) =
           viewModelScope.launch {
               val rows = employeeRepository.updateEmployee(employee)
           }

       fun deleteEmployee(employee: Employee){
        viewModelScope.launch {
            val rows= employeeRepository.deleteEmployee(employee) }
    }

    fun deleteAllEmployee(){
        viewModelScope.launch {
            val rows= employeeRepository.deleteAllEmployee() }
    }




}
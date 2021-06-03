package fr.mastergime.issakzei.mvvmapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.mastergime.issakzei.mvvmapp.models.repository.EmployeeRepository
import java.lang.IllegalArgumentException

class EmployeeViewModelFactory(private val employeeRepository: EmployeeRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmployeeViewModel::class.java))
            return EmployeeViewModel(employeeRepository) as T

        throw  IllegalArgumentException("Unknown viewModel")
    }
}
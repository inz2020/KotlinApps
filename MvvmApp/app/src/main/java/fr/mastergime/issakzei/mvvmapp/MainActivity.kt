package fr.mastergime.issakzei.mvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import fr.mastergime.issakzei.mvvmapp.databinding.ActivityMainBinding
import fr.mastergime.issakzei.mvvmapp.databinding.ItemLayoutBinding
import fr.mastergime.issakzei.mvvmapp.models.RoomDB
import fr.mastergime.issakzei.mvvmapp.models.dao.EmployeeDao
import fr.mastergime.issakzei.mvvmapp.models.entity.Employee
import fr.mastergime.issakzei.mvvmapp.models.repository.EmployeeRepository
import fr.mastergime.issakzei.mvvmapp.view.EmployeeAdapter
import fr.mastergime.issakzei.mvvmapp.view.OnElementClickListener
import fr.mastergime.issakzei.mvvmapp.viewModels.EmployeeViewModel
import fr.mastergime.issakzei.mvvmapp.viewModels.EmployeeViewModelFactory

class MainActivity : AppCompatActivity() , OnElementClickListener{
    //utilisation viewBinding
    lateinit var  binding: ActivityMainBinding
   lateinit var viewModel: EmployeeViewModel
   private val TAG:String?="MYTAG"

    lateinit var adapter: EmployeeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao:EmployeeDao= RoomDB.getInstance(application).employeeDao
        val repository=EmployeeRepository(dao)
        val factory= EmployeeViewModelFactory(repository)
        viewModel= ViewModelProvider(this, factory).get(EmployeeViewModel::class.java)


        binding.floatingActionButton.setOnClickListener {
            //Action ouvrir boite de dialogue pour ajouter
            showDialog(null)
        }
        showAllEmployee()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deleteAll_menu)
        {
            viewModel.deleteAllEmployee()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog(employee: Employee?){
        val alertDialog= AlertDialog.Builder(this)
        val dialogBinding= ItemLayoutBinding.inflate(layoutInflater)
        alertDialog.setView(dialogBinding.root)

        val dialog= alertDialog.create()

        if(employee==null) {
            dialogBinding.login.setText("Enregistrer")
            dialogBinding.login.setOnClickListener {
                //verifier que les champs address et name ne sont pas vides

                if (!TextUtils.isEmpty(dialogBinding.nameEmployee.text.toString())
                        && !TextUtils.isEmpty(dialogBinding.addressEmployee.text.toString())) {

                    //Action enregistrer
                    val newEmployee=Employee(
                           0,
                            dialogBinding.nameEmployee.text.toString(),
                            dialogBinding.addressEmployee.text.toString(),
                    )
                    viewModel.addEmployee(newEmployee)

                } else {
                    Toast.makeText(this, "Tous les champs sont obligatoirs!!", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
        }

            else {
            dialogBinding.login.setText("Modifier")
            dialogBinding.nameEmployee.setText(employee.name)
            dialogBinding.addressEmployee.setText(employee.address)

            dialogBinding.login.setOnClickListener {
                //verifier que les champs address et name ne sont pas vides
                if (!TextUtils.isEmpty(dialogBinding.nameEmployee.text.toString())
                        && !TextUtils.isEmpty(dialogBinding.addressEmployee.text.toString())) {

                    //Action modifier
                    viewModel.updateEmployee(Employee(
                            employee.id,
                            dialogBinding.nameEmployee.text.toString(),
                            dialogBinding.addressEmployee.text.toString()
                    ))

                } else {
                    Toast.makeText(this, "Tous les champs sont obligatoirs!!", Toast.LENGTH_SHORT)
                }

                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun showAllEmployee(){
        viewModel.employees.observe(this, {
            //Log.i(TAG, it.toString())
            adapter= EmployeeAdapter((it), this)
            binding.idRecycle.adapter= adapter
        })
    }

    override fun setOnElementClickListener(employee: Employee, toUpdate: Boolean) {
        if(toUpdate){
            showDialog(employee)
        }
        else{
            viewModel.deleteEmployee(employee)
        }
    }
}
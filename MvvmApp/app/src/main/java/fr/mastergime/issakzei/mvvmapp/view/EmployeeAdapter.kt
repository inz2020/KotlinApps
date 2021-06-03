package fr.mastergime.issakzei.mvvmapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import fr.mastergime.issakzei.mvvmapp.databinding.ElementEmployeeLayoutBinding
import fr.mastergime.issakzei.mvvmapp.models.entity.Employee

class EmployeeAdapter(private val employees: List<Employee>, private val listener: OnElementClickListener):
        RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {

        val inflater= LayoutInflater.from(parent.context)

        val binding= ElementEmployeeLayoutBinding.inflate(inflater, parent, false)
        return EmployeeViewHolder(parent.context,binding, listener)

    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
       holder.adapterView(employees[position])
    }

    override fun getItemCount(): Int {
        return employees.size
    }


    class EmployeeViewHolder(private val context:Context,
                             private val binding:ElementEmployeeLayoutBinding,
                             private val listener:OnElementClickListener):RecyclerView.ViewHolder(binding.root){
        fun adapterView(employee: Employee){
            binding.idFirstLetter.text= employee.name[0].toString()
            binding.idCName.text= employee.name

            binding.idImageView.setOnClickListener {
                //cest ce popup qui va presenter les items de 'ajouter' , 'modifier', 'supprimer'
                val popMenu=PopupMenu(context, it)

                popMenu.menu.add("Modifier").setOnMenuItemClickListener {
                    listener.setOnElementClickListener(employee, true)
                    true
                }

                popMenu.menu.add("Supprimer").setOnMenuItemClickListener {
                    listener.setOnElementClickListener(employee, false)
                    true
                }
                popMenu.show()
            }

        }
    }
}

interface OnElementClickListener{
    fun setOnElementClickListener(employee: Employee, toUpdate:Boolean)
}
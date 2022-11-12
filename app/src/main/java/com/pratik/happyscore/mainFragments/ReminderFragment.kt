package com.pratik.happyscore.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pratik.happyscore.R
import com.pratik.happyscore.adapter.MedicineAdapter
import com.pratik.happyscore.databinding.FragmentReminderBinding
import com.pratik.happyscore.models.MedicineModel

class ReminderFragment : Fragment() {
    private var _binding: FragmentReminderBinding? = null
    private val binding get() = _binding!!

    lateinit var auth: FirebaseAuth
    lateinit var adapter:MedicineAdapter
    lateinit var database:FirebaseDatabase
    lateinit var medicineArrayList: ArrayList<MedicineModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReminderBinding.inflate(inflater,container,false)



        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        medicineArrayList = ArrayList<MedicineModel>()

        val databaseReference = database.reference.child("Users").child(auth.uid.toString()).child("medicines")


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                medicineArrayList.clear()
                for (datasnapshot: DataSnapshot in snapshot.children){

                    val med:MedicineModel = MedicineModel(
                        datasnapshot.child("date").value.toString(),
                        datasnapshot.child("time").value.toString(),
                        datasnapshot.child("title").value.toString(),
                        datasnapshot.child("discription").value.toString(),
                        datasnapshot.child("status").value as Boolean?
                    )
                    medicineArrayList.add(med)
                }
               // Toast.makeText(requireActivity(),medicineArrayList.size.toString(),Toast.LENGTH_SHORT).show()
                binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
                adapter = MedicineAdapter(requireActivity(), medicineArrayList)
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })




        // Inflate the layout for this fragment
        return binding.root
    }

}
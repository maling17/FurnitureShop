package com.example.furnitureshop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.furnitureshop.adapter.FurnitureAdapter
import com.example.furnitureshop.databinding.FragmentHomeBinding
import com.example.furnitureshop.db.FurnitureTable
import com.example.furnitureshop.viewmodel.FurnitureViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var adapter: FurnitureAdapter
    private lateinit var furnitureViewModel: FurnitureViewModel
    private lateinit var furnitureList: List<FurnitureTable>

    var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView(_binding)

        return binding.root
    }

    private fun initData() {
        furnitureViewModel = ViewModelProvider(this).get(FurnitureViewModel::class.java)
        furnitureViewModel.furnitureList.observe(this,
            Observer { furniture: List<FurnitureTable> ->
                furnitureList = furniture
                adapter.setMovieList(furniture)
            }
        )
    }

    private fun initView(view: FragmentHomeBinding?) {
        //menampilkan data dari
        adapter = FurnitureAdapter(this)
        binding.rvFurniture.adapter = adapter
        binding.rvFurniture.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        binding.rvFurniture.layoutManager = LinearLayoutManager(requireContext())
    }
}
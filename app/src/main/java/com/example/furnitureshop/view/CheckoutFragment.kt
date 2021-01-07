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
import com.example.furnitureshop.adapter.TransaksiAdapter
import com.example.furnitureshop.databinding.FragmentCheckoutBinding
import com.example.furnitureshop.db.TransactionTable
import com.example.furnitureshop.viewmodel.TransaksiViewModel

class CheckoutFragment : Fragment() {
    private lateinit var adapter: TransaksiAdapter
    private lateinit var transaksiViewModel: TransaksiViewModel
    private lateinit var transaksiList: List<TransactionTable>

    var _binding: FragmentCheckoutBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        initView(_binding)

        return binding.root
    }

    private fun initData() {
        transaksiViewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
        transaksiViewModel.transaksiList.observe(this,
            Observer { transaksi: List<TransactionTable> ->
                transaksiList = transaksi
                adapter.setMovieList(transaksi)
            }
        )
    }

    private fun initView(view: FragmentCheckoutBinding?) {
        adapter = TransaksiAdapter(this)
        binding.rvCheckout.adapter = adapter
        binding.rvCheckout.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvCheckout.layoutManager = LinearLayoutManager(requireContext())
    }
}
package com.example.furnitureshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.furnitureshop.db.*

class TransaksiViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionDao: TransactionDao =
        FurnitureDatabase.getDatabase(application).transactionDao()
    val transaksiList: LiveData<List<TransactionTable>>

    init {
        transaksiList = transactionDao.allTransaction
    }
    suspend fun insert(vararg transaction:TransactionTable) {
        transactionDao.insert(*transaction)
    }

}
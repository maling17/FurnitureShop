package com.example.furnitureshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.furnitureshop.db.FurnitureDao
import com.example.furnitureshop.db.FurnitureDatabase
import com.example.furnitureshop.db.FurnitureTable

class FurnitureViewModel(application: Application) : AndroidViewModel(application) {

    private val furnitureDao: FurnitureDao =
        FurnitureDatabase.getDatabase(application).furnitureDao()

    val furnitureList: LiveData<List<FurnitureTable>>

    init {
        furnitureList = furnitureDao.allFurniture
    }
    suspend fun byKategori(kategori:String){
        furnitureDao.viewProductByCategory(kategori)
    }
}
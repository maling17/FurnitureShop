package com.example.furnitureshop.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FurnitureDao {

    @Query("SELECT * FROM furniture where kategori=:kategori")
    suspend fun viewProductByCategory(kategori: String): FurnitureTable?

    @Query("SELECT * FROM furniture WHERE id_produk = :id LIMIT 1")
    suspend fun findFurnitureById(id: Long): FurnitureTable?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg furniture: FurnitureTable)

    @get:Query("SELECT * FROM furniture ORDER BY id_produk ASC")
    val allFurniture: LiveData<List<FurnitureTable>>

}
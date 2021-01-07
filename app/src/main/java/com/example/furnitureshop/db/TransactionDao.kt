package com.example.furnitureshop.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg transaction: TransactionTable)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(transaction: TransactionTable)

    /*  @Query("DELETE FROM transaksi where id_transaksi =:id_transaksi")
      suspend fun deleteAll(id_transaksi: Long): TransactionTable?
  */
    @get:Query("SELECT * FROM transaksi ORDER BY id_transaksi ASC")
    val allTransaction: LiveData<List<TransactionTable>>
}
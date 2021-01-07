package com.example.furnitureshop.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = TransactionTable.TABLE_NAME,
    indices = [Index(value = [TransactionTable.ID_TRANSACTION], unique = true)]
)
data class TransactionTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_TRANSACTION)
    var idTransaksi: Long = 0,
    @ColumnInfo(name = "id_produk")
    var idProduk: Long = 0,
    @ColumnInfo(name = "nama_produk")
    var namaProduk: String,
    @ColumnInfo(name = "deskripsi")
    var deskripsi: String,
    @ColumnInfo(name = "harga")
    var harga: Int,
    @ColumnInfo(name = "made")
    var made: String,
    @ColumnInfo(name = "qty")
    var qty: Int = 0,
    @ColumnInfo(name = "image")
    var image: String,
) {
    constructor() : this(0L, 0L,"","",0, "",0,"")

    companion object {
        const val TABLE_NAME = "transaksi"
        const val ID_TRANSACTION = "id_transaksi"
    }
}
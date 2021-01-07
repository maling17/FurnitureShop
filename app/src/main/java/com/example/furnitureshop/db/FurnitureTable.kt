package com.example.furnitureshop.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.furnitureshop.db.FurnitureTable.Companion.PRODUCT_NAME
import com.example.furnitureshop.db.FurnitureTable.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [Index(value = [PRODUCT_NAME], unique = true)]
)
data class FurnitureTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_produk")
    var idProduk: Long = 0,
    @ColumnInfo(name = PRODUCT_NAME)
    var namaProduk: String,
    @ColumnInfo(name = "deskripsi")
    var deskripsi: String,
    @ColumnInfo(name = "harga")
    var harga: Int,
    @ColumnInfo(name = "made")
    var made: String,
    @ColumnInfo(name = "kategori")
    var kategori: String,
    @ColumnInfo(name = "image")
    var image: String,
) {

    constructor() : this(0L, "", "", 0, "", "", "")


    companion object {
        const val TABLE_NAME = "furniture"
        const val PRODUCT_NAME = "nama_produk"
    }
}
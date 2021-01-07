package com.example.furnitureshop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [FurnitureTable::class, TransactionTable::class], version = 2)
abstract class FurnitureDatabase : RoomDatabase() {

    abstract fun furnitureDao(): FurnitureDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        private var INSTANCE: FurnitureDatabase? = null
        private const val DB_NAME = "furniture2.db"

        fun getDatabase(context: Context): FurnitureDatabase {

               val MIGRATION_2_3 = object : Migration(1, 2) {
                   override fun migrate(database: SupportSQLiteDatabase) {

                       database.execSQL("DROP TABLE transaksi;")
                       database.execSQL(
                           "CREATE TABLE transaksi(id_transaksi INTEGER PRIMARY KEY NOT NULL,id_produk INTEGER  NOT NULL,nama_produk TEXT NOT NULL,deskripsi TEXT NOT NULL,harga INTEGER NOT NULL,made TEXT NOT NULL,qty INTEGER NOT NULL,image TEXT NOT NULL ); "
                       )
                       database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_furniture_id_transaksi ON transaksi(id_transaksi);")
                   }
               }
            if (INSTANCE == null) {
                synchronized(FurnitureDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext, FurnitureDatabase::class.java,
                                DB_NAME
                        )
                                .addCallback(object : Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                        super.onCreate(db)
                                        GlobalScope.launch { rePopulateDb(INSTANCE) }
                                    }
                                }).addMigrations(MIGRATION_2_3)
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }


}
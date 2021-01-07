package com.example.furnitureshop.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.furnitureshop.databinding.ActivityDetailFunitureBinding
import com.example.furnitureshop.db.FurnitureDatabase
import com.example.furnitureshop.db.TransactionDao
import com.example.furnitureshop.db.TransactionTable
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class DetailFunitureActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailFunitureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFunitureBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ambil data
        getData()

        binding.btnBeliSekarang.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                insert()
                finish()
            }
        }
        binding.btnCheckout.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                insert()
                finish()
            }
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    fun getData() {
        val namaProduct = intent.getStringExtra("nama_produk")
        val image = intent.getStringExtra("image")
        val harga = intent.getStringExtra("harga")
        val deskripsi = intent.getStringExtra("deskripsi")
        val made = intent.getStringExtra("made")

        binding.tvNamaProduk.text = namaProduct
        binding.tvHarga.text = "IDR$harga"
        binding.tvDeskripsi.text = deskripsi
        binding.tvDistributor.text = made
        Picasso.get().load(image).into(binding.ivProduk)
    }

    suspend fun insert() {
        val namaProduct = intent.getStringExtra("nama_produk")
        val idProduct = intent.getStringExtra("id_produk")
        val image = intent.getStringExtra("image")
        val harga = intent.getStringExtra("harga")
        val deskripsi = intent.getStringExtra("deskripsi")
        val made = intent.getStringExtra("made")

        val transactionDao: TransactionDao =
            FurnitureDatabase.getDatabase(application).transactionDao()

        transactionDao.insert(
            TransactionTable(
                idProduk = idProduct.toLong(),
                namaProduk = namaProduct,
                deskripsi = deskripsi,
                harga = harga.toInt(),
                made = made,
                qty = 1,
                image = image
            )
        )
    }
}
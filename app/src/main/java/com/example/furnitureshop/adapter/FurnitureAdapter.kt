package com.example.furnitureshop.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.furnitureshop.R
import com.example.furnitureshop.db.FurnitureTable
import com.example.furnitureshop.view.DetailFunitureActivity
import com.squareup.picasso.Picasso

class FurnitureAdapter(private val parent: Fragment) :
    RecyclerView.Adapter<FurnitureAdapter.FurnitureViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(parent.requireContext())
    private var furnitureList: List<FurnitureTable>? = null

    fun setMovieList(furnitureList: List<FurnitureTable>?) {
        this.furnitureList = furnitureList
        notifyDataSetChanged()
    }

    class FurnitureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProduk: ImageView = itemView.findViewById(R.id.iv_list_produk)
        val namaProduk: TextView = itemView.findViewById(R.id.tv_list_nama_produk)
        val deskripsi: TextView = itemView.findViewById(R.id.tv_list_desc_produk)
        val harga: TextView = itemView.findViewById(R.id.tv_harga_list_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_furniture, parent, false)
        return FurnitureViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FurnitureViewHolder, position: Int) {
        furnitureList.let { list ->
            val furniture = list!![position]
            holder.namaProduk.text = furniture.namaProduk
            holder.deskripsi.text = furniture.deskripsi
            holder.harga.text = "IDR${furniture.harga}"
            Picasso.get().load(furniture.image.toString()).into(holder.ivProduk)

            holder.itemView.setOnClickListener {
                val intent = Intent(parent.requireContext(), DetailFunitureActivity::class.java)
                intent.putExtra("id_produk", furniture.idProduk.toString())
                intent.putExtra("nama_produk", furniture.namaProduk)
                intent.putExtra("image", furniture.image)
                intent.putExtra("harga", furniture.harga.toString())
                intent.putExtra("deskripsi", furniture.deskripsi)
                intent.putExtra("made", furniture.made)
                intent.putExtra("kategori", furniture.kategori)

                parent.requireContext().startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (furnitureList == null) {
            0
        } else {
            furnitureList!!.size
        }
    }
}
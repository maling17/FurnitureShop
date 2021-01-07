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
import com.example.furnitureshop.db.TransactionTable
import com.example.furnitureshop.view.DetailFunitureActivity
import com.squareup.picasso.Picasso

class TransaksiAdapter(private val parent: Fragment) :
    RecyclerView.Adapter<TransaksiAdapter.FurnitureViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(parent.requireContext())
    private var transaksiList: List<TransactionTable>? = null
    var status = "0"
    fun setMovieList(transaksiList: List<TransactionTable>?) {
        this.transaksiList = transaksiList
        notifyDataSetChanged()
    }

    class FurnitureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProduk: ImageView = itemView.findViewById(R.id.iv_list_produk_checkout)
        val ivCheck: ImageView = itemView.findViewById(R.id.iv_check_produk)
        val ivPlus: ImageView = itemView.findViewById(R.id.btn_plus)
        val ivMinus: ImageView = itemView.findViewById(R.id.btn_min)
        val namaProduk: TextView = itemView.findViewById(R.id.tv_list_nama_produk_checkout)
        val deskripsi: TextView = itemView.findViewById(R.id.tv_list_desc_checkout)
        val qty: TextView = itemView.findViewById(R.id.tv_qty_list_produk)
        val harga: TextView = itemView.findViewById(R.id.tv_harga_list_produk_checkout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_checkout, parent, false)
        return FurnitureViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FurnitureViewHolder, position: Int) {
        transaksiList.let { list ->
            val furniture = list!![position]
            var kuantitas = furniture.qty.toInt()
            var harga = furniture.harga
            val total = harga * kuantitas
            holder.namaProduk.text = furniture.namaProduk
            holder.deskripsi.text = furniture.deskripsi
            holder.harga.text = "IDR${total}"
            Picasso.get().load(furniture.image.toString()).into(holder.ivProduk)
            holder.qty.text = kuantitas.toString()

            holder.ivCheck.setOnClickListener {

                status = if (status == "0") {
                    holder.ivCheck.setBackgroundResource(R.drawable.ic_check_circle)
                    "0"
                } else {
                    holder.ivCheck.setBackgroundResource(R.drawable.ic_unchecked)
                    "1"
                }
            }

            holder.ivPlus.setOnClickListener {
                kuantitas += 1
                val total = harga * kuantitas
                holder.harga.text = "IDR${total}"
                holder.qty.text = kuantitas.toString()
            }

            holder.ivMinus.setOnClickListener {
                kuantitas -= 1
                val total = harga * kuantitas
                holder.harga.text = "IDR${total}"
                holder.qty.text = kuantitas.toString()
            }


            holder.itemView.setOnClickListener {
                val intent = Intent(parent.requireContext(), DetailFunitureActivity::class.java)
                intent.putExtra("id_produk", furniture.idProduk.toString())
                intent.putExtra("nama_produk", furniture.namaProduk)
                intent.putExtra("image", furniture.image)
                intent.putExtra("harga", furniture.harga.toString())
                intent.putExtra("deskripsi", furniture.deskripsi)
                intent.putExtra("made", furniture.made)


                parent.requireContext().startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (transaksiList == null) {
            0
        } else {
            transaksiList!!.size
        }
    }
}
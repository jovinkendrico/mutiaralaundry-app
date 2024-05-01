package com.example.mutiaralaundry.data.ui.history

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mutiaralaundry.data.response.DataItem
import com.example.mutiaralaundry.databinding.HistoryLayoutBinding

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ListViewHolder>()  {
    private val listDetailPesanan = ArrayList<DataItem>()

    fun setData(data: List<DataItem>){
        listDetailPesanan.clear()
        listDetailPesanan.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.ListViewHolder {
        val binding = HistoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ListViewHolder, position: Int) {
        val detail = listDetailPesanan[position]
        holder.bind(detail)
//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView.context,DetailPanenActivity::class.java)
//            intent.putExtra("id",detail.id)
//            holder.itemView.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return listDetailPesanan.size
    }

    inner class ListViewHolder(private val binding: HistoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detail: DataItem) {
            binding.tvCab.text = detail.cabang.nama
            binding.tvPak.text = detail.paket.nama
            binding.tvQty.text = detail.qty.toString() + " paket"
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
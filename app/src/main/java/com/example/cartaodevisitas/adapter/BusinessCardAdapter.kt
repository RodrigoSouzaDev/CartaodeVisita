package com.example.cartaodevisitas.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cartaodevisitas.databinding.CartaoRecycleritemBinding
import com.example.cartaodevisitas.model.BusinessCard

class BusinessCardAdapter(
    private val deleteClicked: (BusinessCard) -> Unit
) :
    RecyclerView.Adapter<BusinessCardAdapter.BusinessCardViewHolder>() {

    private var cards: ArrayList<BusinessCard> = ArrayList()
    var onClickShare: (View) -> Unit = {}

    class BusinessCardViewHolder(val binding: CartaoRecycleritemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessCardViewHolder {
        return BusinessCardViewHolder(
            CartaoRecycleritemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusinessCardViewHolder, position: Int) {
        val businessCard = cards[position]
        holder.binding.tvNome.text = businessCard.nome
        holder.binding.tvTelefone.text = businessCard.telefone
        holder.binding.tvEmail.text = businessCard.email
        holder.binding.tvEmpresa.text = businessCard.empresa
        holder.binding.cardView.setCardBackgroundColor(businessCard.corFundo)
        holder.binding.imgbtDelete.setOnClickListener{
            deleteClicked(businessCard)
        }
        holder.binding.cardView.setOnClickListener {
            onClickShare(it)
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(array: ArrayList<BusinessCard>) {
        cards = array
        notifyDataSetChanged()
    }
}


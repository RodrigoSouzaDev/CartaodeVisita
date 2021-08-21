package com.example.cartaodevisitas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartaodevisitas.model.BusinessCard
import com.example.cartaodevisitas.repository.BusinessCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBusinessCardViewModel (private val repository: BusinessCardRepository): ViewModel()
{
    private var color : Int = 0

    fun insert (businessCard: BusinessCard){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(businessCard)
        }
    }

    fun setColor(colorSelected: Int)
    {
        color = colorSelected
    }

    fun getColor (): Int
    {
        return color
    }
}
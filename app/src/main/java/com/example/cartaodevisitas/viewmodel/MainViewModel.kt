package com.example.cartaodevisitas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cartaodevisitas.repository.BusinessCardRepository
import com.example.cartaodevisitas.model.BusinessCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: BusinessCardRepository): ViewModel() {
    val allBusinessCards : LiveData<List<BusinessCard>> = repository.getAll().asLiveData()

    fun insert (businessCard: BusinessCard){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(businessCard)
        }
    }

    fun delete (businessCard: BusinessCard){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(businessCard)
        }
    }
}
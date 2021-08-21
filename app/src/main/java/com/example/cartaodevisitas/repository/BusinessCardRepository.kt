package com.example.cartaodevisitas.repository

import com.example.cartaodevisitas.model.BusinessCard
import kotlinx.coroutines.flow.Flow

interface BusinessCardRepository {

    fun getAll (): Flow<List<BusinessCard>>

    suspend fun insert(businessCard: BusinessCard)

    suspend fun delete(businessCard: BusinessCard)

}
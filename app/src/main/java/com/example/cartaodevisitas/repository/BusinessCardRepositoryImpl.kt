package com.example.cartaodevisitas.repository

import com.example.cartaodevisitas.database.BusinessCardDatabase
import com.example.cartaodevisitas.model.BusinessCard
import kotlinx.coroutines.flow.Flow

class BusinessCardRepositoryImpl (private val database: BusinessCardDatabase): BusinessCardRepository {

    override fun getAll(): Flow<List<BusinessCard>> {
        return database.businessCardDao().getAll()
    }

    override suspend fun insert(businessCard: BusinessCard) {
        database.businessCardDao().insert(businessCard)
    }

    override suspend fun delete(businessCard: BusinessCard) {
        database.businessCardDao().delete(businessCard)
    }
}
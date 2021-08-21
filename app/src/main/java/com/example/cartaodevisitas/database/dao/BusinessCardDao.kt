package com.example.cartaodevisitas.database.dao

import androidx.room.*
import com.example.cartaodevisitas.model.BusinessCard
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM BCard_table")
    fun getAll(): Flow<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCard: BusinessCard)

    @Delete
    suspend fun delete(businessCard: BusinessCard)
}
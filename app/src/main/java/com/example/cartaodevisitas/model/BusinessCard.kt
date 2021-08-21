package com.example.cartaodevisitas.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "BCard_table")
data class BusinessCard (
    val nome: String,
    val empresa: String,
    val telefone: String,
    val email: String,
    val corFundo: Int
        ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
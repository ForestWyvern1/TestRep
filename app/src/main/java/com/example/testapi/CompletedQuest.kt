package com.example.testapi

data class CompletedQuest(
    val authorName: String,
    val category: Category,
    val id: Int,
    val mainPhoto: String,
    val name: String,
    val rating: Int
)
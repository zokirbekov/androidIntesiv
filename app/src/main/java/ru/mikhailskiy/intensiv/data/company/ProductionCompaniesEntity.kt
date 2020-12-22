package ru.mikhailskiy.intensiv.data.company

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductionCompaniesEntity(
    @PrimaryKey(autoGenerate = true)
    val proCompanyId: Long,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
)
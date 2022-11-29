package io.github.grishaninvyacheslav.lh.network.data.entity

data class CompanyDetailsEntity(
    val id: String,
    val name: String,
    val img: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val www: String,
    val phone: String
)
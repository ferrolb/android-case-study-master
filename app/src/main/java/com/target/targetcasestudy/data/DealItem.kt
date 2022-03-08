package com.target.targetcasestudy.data

import com.squareup.moshi.Json

data class DealItem(
    var id: Int,
    var title: String,
    var description: String,
    var price: String,
    var aisle: String
)

data class Products(
    @field:Json(name = "products")
    var products: List<Deal>?
)

data class Deal(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "aisle") val aisle: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "image_url") val image_url: String?,
    @field:Json(name = "regular-price") val regularPrice: RegularPrice?,
    @field:Json(name = "sale-price") val salePrice: SalePrice?
)

data class RegularPrice(
    @field:Json(name = "amount_in_cents") val id: Int?,
    @field:Json(name = "currency_symbol") val currency_symbol: String?,
    @field:Json(name = "display_string") val aisle: String?
)

data class SalePrice(
    @field:Json(name = "amount_in_cents") val id: Int?,
    @field:Json(name = "currency_symbol") val currency_symbol: String?,
    @field:Json(name = "display_string") val aisle: String?
)
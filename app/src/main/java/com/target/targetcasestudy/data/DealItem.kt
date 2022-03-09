package com.target.targetcasestudy.data

import com.squareup.moshi.Json
import java.io.Serializable

data class Products(
    @field:Json(name = "products")
    var products: List<DealItem>?
)

data class DealItem(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "aisle") val aisle: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "image_url") val imageUrl: String?,
    @field:Json(name = "regular_price") val regularPrice: RegularPrice?,
    @field:Json(name = "sale_price") val salePrice: SalePrice?
) : Serializable

data class RegularPrice(
    @field:Json(name = "amount_in_cents") val amountInCents: Int?,
    @field:Json(name = "currency_symbol") val currencySymbol: String?,
    @field:Json(name = "display_string") val displayString: String?
) : Serializable

data class SalePrice(
    @field:Json(name = "amount_in_cents") val amountInCents: Int?,
    @field:Json(name = "currency_symbol") val currencySymbol: String?,
    @field:Json(name = "display_string") val displayString: String?
) : Serializable
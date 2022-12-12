package com.peacetechsolution.myshopbarkat.data.model

data class ProductResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Product>,
    val prev_page: String,
    val total_results: Int
)

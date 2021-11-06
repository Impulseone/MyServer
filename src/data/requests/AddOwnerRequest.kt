package com.mycorp.data.requests

data class AddOwnerRequest(
    val noteID: String,
    val owner: String
)
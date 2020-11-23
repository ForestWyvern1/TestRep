package com.example.testapi

data class Users(
    var email: String,
    var nickName: String,
    var password: String,
    var phone: String
){
    var token: String? = null
}
package com.example.mynewsapp.domain.broadcast

interface NetworkChangeListener {
    fun onNetworkChange(isConnected: Boolean)
}
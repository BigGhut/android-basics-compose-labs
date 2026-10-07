package com.example.kotlinpractice.practice

class Bid(val amount: Int, val bidder: String)

fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    return bid?.amount ?: minimumPrice
}

fun runSpecialAuction() {
    val winningBid = Bid(5000, "Private Collector")

    DeviceConsole.log("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    DeviceConsole.log("Item B is sold at ${auctionPrice(null, 3000)}.")
}

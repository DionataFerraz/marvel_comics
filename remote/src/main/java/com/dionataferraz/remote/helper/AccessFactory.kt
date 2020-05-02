package com.dionataferraz.remote.helper

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

object AccessFactory {
    private const val ALGORITHM = "MD5"
    private const val DATE_FORMAT = "yyyy.MM.dd.HH.mm.ss"

    private var access: Pair<String, String>? = null

    fun getAccess(key: String): Pair<String, String> = access ?: createHash(key)

    private fun createHash(key: String): Pair<String, String> {
        val ts = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
        val toHash = ts + key

        val m = MessageDigest.getInstance(ALGORITHM)
        m.update(toHash.toByteArray(), 0, toHash.length)

        return Pair(ts, BigInteger(1, m.digest()).toString(16))
    }
}

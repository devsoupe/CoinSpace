package com.perelandrax.coincraft.data.repository.remote

import com.perelandrax.coincraft.data.repository.remote.model.CoinModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CoinApi {

  companion object {

    fun create(baseUrl: String, okHttpClient: OkHttpClient): CoinApi {
      val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
      return retrofit.create(CoinApi::class.java)
    }
  }

  @GET("/v1/ticker/?limit=0")
  suspend fun getCoinListCmc(): List<CoinModel>
}
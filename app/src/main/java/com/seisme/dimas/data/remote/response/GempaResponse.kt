package com.seisme.dimas.data.remote.response

import com.google.gson.annotations.SerializedName

data class GempaResponse(

	@field:SerializedName("Infogempa")
	val infogempa: Infogempa
)

data class GempaItem(

	@field:SerializedName("Dirasakan")
	val dirasakan: String,

	@field:SerializedName("Wilayah")
	val wilayah: String,

	@field:SerializedName("Kedalaman")
	val kedalaman: String,

	@field:SerializedName("Jam")
	val jam: String,

	@field:SerializedName("Coordinates")
	val coordinates: String,

	@field:SerializedName("Tanggal")
	val tanggal: String,

	@field:SerializedName("Bujur")
	val bujur: String,

	@field:SerializedName("Magnitude")
	val magnitude: String,

	@field:SerializedName("Lintang")
	val lintang: String,

	@field:SerializedName("DateTime")
	val dateTime: String
)

data class Infogempa(

	@field:SerializedName("gempa")
	val gempa: List<GempaItem>
)

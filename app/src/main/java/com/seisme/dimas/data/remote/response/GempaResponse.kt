package com.seisme.dimas.data.remote.response

import com.google.gson.annotations.SerializedName

data class Infogempa(
	@field:SerializedName("gempa")
	val gempa: List<GempaItem>
)

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
) {
	val formattedTime: String
		get() = formatTime(jam)

	val formattedLocation: String
		get() = formatLocation(wilayah)
}

fun formatTime(time: String): String {
	val regex = Regex("(\\d{2}):(\\d{2}):\\d{2} WIB")
	return regex.replace(time) { matchResult ->
		"${matchResult.groupValues[1]}.${matchResult.groupValues[2]}"
	}
}

fun formatLocation(location: String): String {
	val regex = Regex(
		"(\\b(?:utara|selatan|timur|barat|tenggara|baratlaut|timurlaut|baratdaya)\\b)\\s+(.+)",
		RegexOption.IGNORE_CASE
	)
	val matchResult = regex.find(location)

	return matchResult?.let {
		val direction =
			it.groupValues[1].replaceFirstChar { char -> char.uppercase() }
		val region = it.groupValues[2].split(" ").joinToString(" ") { word ->
			word.lowercase().replaceFirstChar { char -> char.uppercase() }
		}
		"$direction $region"
	} ?: location
}
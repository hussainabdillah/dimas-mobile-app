package com.seisme.dimas.data.remote.response

import com.google.gson.annotations.SerializedName

data class EarthquakeInfo(
	@field:SerializedName("gempa")
	val earthquakes: List<EarthquakeItem>
)

data class EarthquakeResponse(
	@field:SerializedName("Infogempa")
	val earthquakeInfo: EarthquakeInfo
)

data class EarthquakeItem(
	@field:SerializedName("Dirasakan")
	val felt: String,

	@field:SerializedName("Wilayah")
	val location: String,

	@field:SerializedName("Kedalaman")
	val depth: String,

	@field:SerializedName("Jam")
	val time: String,

	@field:SerializedName("Coordinates")
	val coordinates: String,

	@field:SerializedName("Tanggal")
	val date: String,

	@field:SerializedName("Bujur")
	val longitude: String,

	@field:SerializedName("Magnitude")
	val magnitude: String,

	@field:SerializedName("Lintang")
	val latitude: String,

	@field:SerializedName("DateTime")
	val dateTime: String
) {
	val formattedTime: String
		get() = formatTime(time)

	val formattedLocation: String
		get() = formatLocation(location)
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

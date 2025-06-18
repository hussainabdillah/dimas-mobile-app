package com.seisme.dimas.data.repository

import com.seisme.dimas.R

data class MitigationRepository(
    val itemTitle: String,
    val itemImage: Int,
    val itemDetails: List<MitigationDetail>
) {
    data class MitigationDetail(
        val detailTitle: String,
        val detailInfo: String,
        val detailDesc: List<AnnotatedInfo>,
        val detailImg: Int
    ) {
        data class AnnotatedInfo(
            val text: String,
            val isBold: Boolean = false,
            val isBullet: Boolean = false
        )
    }
}

val earthquakeMitigationItem = listOf(
    MitigationRepository(
        itemTitle = "Di Dalam Ruangan",
        itemImage = R.drawable.img_dalam_ruangan,
        itemDetails = listOf(
            MitigationRepository.MitigationDetail(
                detailTitle = "Rumah",
                detailInfo = "Saat gempa terjadi di rumah, penting untuk tetap tenang dan segera berlindung di tempat yang aman. Melindungi diri dari benda jatuh dan meminimalkan risiko cedera adalah prioritas utama. Berikut langkah-langkah yang perlu dilakukan:",
                detailDesc = listOf(
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Find shelter under a sturdy table",
                        isBullet = true
                    ),
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Stay away from windows and heavy objects",
                        isBullet = true
                    ),
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Do not use elevators",
                        isBold = true,
                        isBullet = true
                    )
                ),
                detailImg = R.drawable.img_gempa_dirumah
            ),
            MitigationRepository.MitigationDetail(
                detailTitle = "Kamar Mandi",
                detailInfo = "Saat gempa terjadi di kamar mandi, penting untuk melindungi diri dari potensi bahaya. Meskipun ruangnya kecil, ada langkah-langkah yang bisa diambil untuk menjaga keselamatan.",
                detailDesc = listOf(
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Move to an open area away from buildings",
                        isBullet = true
                    ),
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Watch for falling debris",
                        isBold = true,
                        isBullet = true
                    ),
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Stay clear of power lines",
                        isBullet = true
                    )
                ),
                detailImg = R.drawable.img_gempa_dikamar_mandi
            ),
            MitigationRepository.MitigationDetail(
                detailTitle = "Elevator",
                detailInfo = "Jika Anda terjebak di dalam elevator saat gempa, penting untuk tetap tenang dan segera mencoba keluar dengan aman. Namun, jika keluar tidak memungkinkan, Anda harus menghubungi bantuan menggunakan tombol darurat di elevator. Berikut langkah-langkah yang perlu dilakukan:",
                detailDesc = listOf(
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Move to an open area away from buildings",
                        isBullet = true
                    ),
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Watch for falling debris",
                        isBold = true,
                        isBullet = true
                    ),
                    MitigationRepository.MitigationDetail.AnnotatedInfo(
                        "Stay clear of power lines",
                        isBullet = true
                    )
                ),
                detailImg = R.drawable.img_gempa_dielevator
            )
        )
    ),
    MitigationRepository(
        itemTitle = "Di Luar Ruangan",
        itemImage = R.drawable.img_luar_ruangan,
        itemDetails = listOf(
            MitigationRepository.MitigationDetail(
                detailTitle = "Tempat Ramai",
                detailInfo = "Jika gempa terjadi di tempat ramai, penting untuk segera mencari perlindungan dari bahaya seperti benda jatuh. Tetap waspada dan tunggu lampu darurat menyala jika listrik padam, untuk menghindari cedera saat bergerak. Berikut langkah-langkah yang perlu dilakukan:",
                detailDesc = listOf(
                    MitigationRepository.MitigationDetail.AnnotatedInfo("Find shelter under a sturdy table", isBullet = true),
                    MitigationRepository.MitigationDetail.AnnotatedInfo("Stay away from windows and heavy objects", isBullet = true),
                    MitigationRepository.MitigationDetail.AnnotatedInfo("Do not use elevators", isBold = true, isBullet = true)
                ),
                detailImg = R.drawable.img_tempat_ramai
            )
        ),
    )
)
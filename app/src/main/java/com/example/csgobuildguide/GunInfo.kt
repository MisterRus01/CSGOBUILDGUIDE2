package com.example.csgobuildguide

import android.widget.ImageView
import java.io.Serializable

data class GunInfo(
    var photoPath: String,
    var gunType: String
): Serializable

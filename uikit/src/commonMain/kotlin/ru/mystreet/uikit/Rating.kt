package ru.mystreet.uikit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStar
import ru.mystreet.uikit.iconpack.uikiticonpack.RatingStarOutline

@Composable
fun RatingStars(
    rating: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(5) {
            RatingStar(
                isSelected = it + 1 <= rating,
                modifier = Modifier.size(15.dp),
            )
        }
    }
}

@Composable
fun RatingStar(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    Icon(
        ratingIcon(isSelected),
        contentDescription = null,
        tint = ratingColor(isSelected),
        modifier = modifier,
    )
}

@Composable
fun ratingColor(isSelected: Boolean): Color {
    return if (isSelected) Color(0xFFEEA63A) else MaterialTheme.colorScheme.outline
}

fun ratingIcon(isSelected: Boolean): ImageVector {
    return if (isSelected) UIKitIconPack.RatingStar else UIKitIconPack.RatingStarOutline
}

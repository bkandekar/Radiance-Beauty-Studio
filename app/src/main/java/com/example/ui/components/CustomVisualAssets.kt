package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush as DrawBrush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Schedule
import com.example.data.DesignLookItem
import com.example.data.GalleryItem
import com.example.data.ServiceCategory
import com.example.ui.theme.AccentGold
import com.example.ui.theme.DarkPlumCard
import com.example.ui.theme.DarkPlumText
import com.example.ui.theme.DeepPlum
import com.example.ui.theme.LightIvory
import com.example.ui.theme.LightRoseContainer
import com.example.ui.theme.MutedRoseBorder
import com.example.ui.theme.RoseGold
import com.example.ui.theme.SoftGold
import com.example.ui.theme.SoftGrayText

/**
 * RULE 4 — CONSOLIDATED PLACEHOLDER & VISUAL SYSTEM
 * Unified styling for hero, gallery, service cards, and avatars.
 */

@Composable
fun HeroIllustration(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(
                DrawBrush.linearGradient(
                    colors = listOf(DarkPlumCard, DeepPlum, RoseGold)
                )
            )
            .border(1.5.dp, MutedRoseBorder, RoundedCornerShape(24.dp))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Background soft circles
            drawCircle(
                color = SoftGold.copy(alpha = 0.15f),
                radius = w * 0.35f,
                center = Offset(w * 0.8f, h * 0.2f)
            )
            drawCircle(
                color = RoseGold.copy(alpha = 0.2f),
                radius = w * 0.45f,
                center = Offset(w * 0.2f, h * 0.8f)
            )

            // Central vanity mirror shape
            val mirrorWidth = w * 0.45f
            val mirrorHeight = h * 0.6f
            val mirrorLeft = (w - mirrorWidth) / 2
            val mirrorTop = (h - mirrorHeight) / 2

            // Mirror Frame
            drawOval(
                color = SoftGold,
                topLeft = Offset(mirrorLeft - 6f, mirrorTop - 6f),
                size = Size(mirrorWidth + 12f, mirrorHeight + 12f),
                style = Stroke(width = 8f)
            )
            drawOval(
                color = Color.White.copy(alpha = 0.25f),
                topLeft = Offset(mirrorLeft, mirrorTop),
                size = Size(mirrorWidth, mirrorHeight)
            )

            // Sparkle stars around mirror
            val sparkles = listOf(
                Offset(w * 0.15f, h * 0.25f),
                Offset(w * 0.85f, h * 0.35f),
                Offset(w * 0.2f, h * 0.7f),
                Offset(w * 0.8f, h * 0.75f)
            )
            for (p in sparkles) {
                drawCircle(color = AccentGold, radius = 6f, center = p)
                drawLine(
                    color = Color.White,
                    start = Offset(p.x - 14f, p.y),
                    end = Offset(p.x + 14f, p.y),
                    strokeWidth = 3f
                )
                drawLine(
                    color = Color.White,
                    start = Offset(p.x, p.y - 14f),
                    end = Offset(p.x, p.y + 14f),
                    strokeWidth = 3f
                )
            }
        }

        // Overlaid Label
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(RoseGold.copy(alpha = 0.9f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Radiance Studio Salon",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Shivaji Chowk, Ratnagiri",
                fontSize = 13.sp,
                color = SoftGold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CategoryCardVisual(
    category: ServiceCategory,
    modifier: Modifier = Modifier
) {
    val (bgColors, iconVector) = when (category) {
        ServiceCategory.HAIR -> Pair(
            listOf(RoseGold, LightRoseContainer),
            Icons.Default.Brush
        )
        ServiceCategory.SKIN -> Pair(
            listOf(DeepPlum, DarkPlumCard),
            Icons.Default.Spa
        )
        ServiceCategory.BRIDAL -> Pair(
            listOf(DarkPlumCard, RoseGold),
            Icons.Default.Favorite
        )
        ServiceCategory.NAILS -> Pair(
            listOf(RoseGold, SoftGold),
            Icons.Default.Palette
        )
        ServiceCategory.THREADING_WAXING -> Pair(
            listOf(DeepPlum, RoseGold),
            Icons.Default.Face
        )
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(DrawBrush.linearGradient(bgColors)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = category.displayName,
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun CustomerAvatar(
    name: String,
    modifier: Modifier = Modifier.size(48.dp)
) {
    val initial = name.firstOrNull()?.uppercase() ?: "R"
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                DrawBrush.linearGradient(
                    colors = listOf(RoseGold, DeepPlum)
                )
            )
            .border(2.dp, SoftGold, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Composable
fun GalleryVisualCard(
    item: GalleryItem,
    modifier: Modifier = Modifier
) {
    val gradientColors = when (item.category) {
        ServiceCategory.BRIDAL -> listOf(DarkPlumCard, DeepPlum, RoseGold)
        ServiceCategory.HAIR -> listOf(RoseGold, LightRoseContainer)
        ServiceCategory.SKIN -> listOf(DeepPlum, DarkPlumCard)
        ServiceCategory.NAILS -> listOf(SoftGold, RoseGold)
        ServiceCategory.THREADING_WAXING -> listOf(DeepPlum, RoseGold)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(DrawBrush.linearGradient(gradientColors))
            .border(1.dp, MutedRoseBorder, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.BottomStart
    ) {
        // Decorative Canvas Background
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.White.copy(alpha = 0.15f),
                radius = size.width * 0.4f,
                center = Offset(size.width * 0.8f, size.height * 0.2f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    DrawBrush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                    )
                )
                .padding(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(AccentGold)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = item.tag,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkPlumText
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun DesignBlueprintPlaceholder(
    label: String,
    dimensions: String,
    placeholderId: String,
    modifier: Modifier = Modifier,
    height: Dp = 180.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .background(LightRoseContainer)
            .drawWithContent {
                drawContent()
                val stroke = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
                )
                drawRoundRect(
                    color = RoseGold,
                    style = stroke
                )
            }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = null,
                tint = RoseGold,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "[PLACEHOLDER: $label]",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPlumText,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Dimensions: $dimensions • ID: $placeholderId",
                fontSize = 11.sp,
                color = SoftGrayText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DesignLookCard(
    look: DesignLookItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgGradient = when (look.category) {
        ServiceCategory.BRIDAL -> listOf(DarkPlumCard, DeepPlum)
        ServiceCategory.HAIR -> listOf(DeepPlum, RoseGold)
        ServiceCategory.SKIN -> listOf(DarkPlumCard, RoseGold)
        ServiceCategory.NAILS -> listOf(RoseGold, SoftGold)
        ServiceCategory.THREADING_WAXING -> listOf(DeepPlum, DarkPlumCard)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(DrawBrush.linearGradient(bgGradient))
            .border(1.dp, MutedRoseBorder, RoundedCornerShape(18.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.BottomStart
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = SoftGold.copy(alpha = 0.15f),
                radius = size.width * 0.5f,
                center = Offset(size.width * 0.85f, size.height * 0.15f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    DrawBrush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                    )
                )
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(AccentGold)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = look.tag,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkPlumText
                    )
                }

                if (look.isPopular) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(RoseGold)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Popular",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = look.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = SoftGold,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${look.durationMinutes} mins",
                        fontSize = 11.sp,
                        color = LightRoseContainer
                    )
                }

                Text(
                    text = "₹${look.estimatedPrice}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SoftGold
                )
            }
        }
    }
}


package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.window.Dialog
import com.example.data.BusinessConfig
import com.example.data.DesignLookItem
import com.example.data.ServiceCategory
import com.example.data.ServiceRepository
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
 * HERO SECTION — Two-tone Split Contrast Design with CTAs & Trust Badges
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
    onBookClick: () -> Unit,
    onEstimateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("hero_section"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = DarkPlumCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Certification Chip
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(RoseGold.copy(alpha = 0.3f))
                    .border(1.dp, SoftGold, RoundedCornerShape(30.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = AccentGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "VLCC Certified Beauty Studio • Ratnagiri",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // RULE 2
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Main Headline
            Text(
                text = "Look and Feel Your Best — Every Time You Visit",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White, // RULE 2
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subheadline
            Text(
                text = "Ratnagiri's premier destination for HD Bridal Makeup, Organic Facials & Luxury Hair Spa. Experience hygienic, 100% authentic care with transparent pricing.",
                fontSize = 13.sp,
                color = LightRoseContainer, // RULE 2
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Hero Canvas Illustration
            HeroIllustration(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Action CTAs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onBookClick,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RoseGold,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1.2f)
                        .height(50.dp)
                        .testTag("hero_book_button")
                ) {
                    Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Book Appointment",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // RULE 2
                    )
                }

                OutlinedButton(
                    onClick = onEstimateClick,
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, SoftGold),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SoftGold),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .testTag("hero_estimator_button")
                ) {
                    Text(
                        text = "Cost Estimator",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SoftGold // RULE 2
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Trust Badges Grid
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                maxItemsInEachRow = 3
            ) {
                TrustBadgeChip(text = "6+ Years Exp")
                TrustBadgeChip(text = "2,000+ Clients")
                TrustBadgeChip(text = "100% Hygienic")
            }
        }
    }
}

@Composable
private fun TrustBadgeChip(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp)
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = AccentGold,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = LightIvory // RULE 2
        )
    }
}

/**
 * PAIN POINTS → SOLUTIONS SECTION
 */
@Composable
fun PainPointsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "WHY CLIENTS CHOOSE US",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RoseGold,
            letterSpacing = 1.2.sp
        )
        Text(
            text = "Common Parlour Doubts — Solved",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPlumText
        )
        Spacer(modifier = Modifier.height(14.dp))

        ServiceRepository.PAIN_POINTS.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, MutedRoseBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(LightRoseContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = DeepPlum,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "\"${item.painPoint}\"",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkPlumText
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = RoseGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Solution: ${item.solution}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = DeepPlum
                        )
                    }
                }
            }
        }
    }
}

/**
 * STATS COUNTER BAR WITH ANIMATED COUNT-UP
 */
@Composable
fun StatsBarSection(modifier: Modifier = Modifier) {
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val yearsVal by animateIntAsState(
        targetValue = if (startAnimation) 6 else 0,
        animationSpec = tween(durationMillis = 1200),
        label = "years"
    )
    val clientsVal by animateIntAsState(
        targetValue = if (startAnimation) 2000 else 0,
        animationSpec = tween(durationMillis = 1500),
        label = "clients"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = DeepPlum)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem(number = "${yearsVal}+", label = "Years in Business")
            StatItem(number = "${"%,d".format(clientsVal)}+", label = "Happy Clients")
            StatItem(number = "VLCC", label = "Certified Staff")
            StatItem(number = "4.9★", label = "Google Rating")
        }
    }
}

@Composable
private fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = SoftGold // RULE 2
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = Color.White, // RULE 2
            textAlign = TextAlign.Center
        )
    }
}

/**
 * PROCESS / HOW IT WORKS SECTION
 */
@Composable
fun ProcessSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "HOW IT WORKS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RoseGold,
            letterSpacing = 1.2.sp
        )
        Text(
            text = "4 Steps to Your Radiant Look",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPlumText
        )
        Spacer(modifier = Modifier.height(14.dp))

        ServiceRepository.PROCESS_STEPS.forEach { step ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(DeepPlum),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${step.stepNumber}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // RULE 2
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = step.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkPlumText
                    )
                    Text(
                        text = step.description,
                        fontSize = 12.sp,
                        color = SoftGrayText,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

/**
 * TESTIMONIALS / REVIEWS SECTION
 */
@Composable
fun TestimonialsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "REAL REVIEWS FROM RATNAGIRI BRIDES & CLIENTS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RoseGold,
            letterSpacing = 1.2.sp
        )
        Text(
            text = "What Our Clients Say",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPlumText
        )
        Spacer(modifier = Modifier.height(14.dp))

        ServiceRepository.TESTIMONIALS.forEach { item ->
            // SAMPLE TESTIMONIAL — replace with real customer quote before going live
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, MutedRoseBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CustomerAvatar(name = item.name)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = item.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkPlumText
                                )
                                Text(
                                    text = item.locality,
                                    fontSize = 11.sp,
                                    color = RoseGold
                                )
                            }
                        }

                        Row {
                            repeat(item.rating) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = AccentGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "\"${item.quote}\"",
                        fontSize = 13.sp,
                        color = DarkPlumText,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(LightRoseContainer)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Service: ${item.serviceUsed}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepPlum
                        )
                    }
                }
            }
        }
    }
}

/**
 * LOOKBOOK & DESIGN CATALOG SECTION
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GallerySection(
    onSelectLookForBooking: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedCategoryFilter by remember { mutableStateOf<ServiceCategory?>(null) }
    var selectedLookForDetail by remember { mutableStateOf<DesignLookItem?>(null) }

    val filteredLooks = remember(selectedCategoryFilter) {
        if (selectedCategoryFilter == null) {
            ServiceRepository.DESIGN_LOOKS
        } else {
            ServiceRepository.DESIGN_LOOKS.filter { it.category == selectedCategoryFilter }
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "LOOKBOOK & PORTFOLIO",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RoseGold,
            letterSpacing = 1.2.sp
        )
        Text(
            text = "Real Studio Transformations",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPlumText
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Category Filter Pills
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            LookFilterPill(
                label = "All Looks",
                isSelected = selectedCategoryFilter == null,
                onClick = { selectedCategoryFilter = null }
            )

            ServiceCategory.values().forEach { cat ->
                LookFilterPill(
                    label = cat.displayName,
                    isSelected = selectedCategoryFilter == cat,
                    onClick = { selectedCategoryFilter = cat }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Design Looks Grid
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            filteredLooks.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowItems.forEach { lookItem ->
                        DesignLookCard(
                            look = lookItem,
                            onClick = { selectedLookForDetail = lookItem },
                            modifier = Modifier
                                .weight(1f)
                                .height(160.dp)
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }

    // Design Look Details Dialog
    selectedLookForDetail?.let { look ->
        DesignLookDetailDialog(
            look = look,
            onDismiss = { selectedLookForDetail = null },
            onBookThisLook = {
                selectedLookForDetail = null
                onSelectLookForBooking("${look.title} (${look.tag})")
            }
        )
    }
}

@Composable
private fun LookFilterPill(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) DeepPlum else LightRoseContainer)
            .border(
                width = 1.dp,
                color = if (isSelected) DeepPlum else MutedRoseBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Color.White else DarkPlumText
        )
    }
}

@Composable
fun DesignLookDetailDialog(
    look: DesignLookItem,
    onDismiss: () -> Unit,
    onBookThisLook: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Top Close & Category Tag
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(RoseGold.copy(alpha = 0.2f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = look.category.displayName,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepPlum
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(LightRoseContainer)
                            .clickable { onDismiss() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = DarkPlumText,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Title
                Text(
                    text = look.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkPlumText
                )

                Text(
                    text = look.tag,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = RoseGold
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Description
                Text(
                    text = look.description,
                    fontSize = 13.sp,
                    color = SoftGrayText,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Key Styling Highlights:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkPlumText
                )

                Spacer(modifier = Modifier.height(6.dp))

                look.highlights.forEach { highlight ->
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = RoseGold,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = highlight,
                            fontSize = 12.sp,
                            color = DarkPlumText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Duration & Price
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = LightRoseContainer)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = DeepPlum,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Duration: ${look.durationMinutes} mins",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = DeepPlum
                            )
                        }

                        Text(
                            text = "₹${look.estimatedPrice}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = DeepPlum
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onBookThisLook,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepPlum,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = SoftGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Reserve This Design Look",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}


/**
 * FINAL CTA BANNER & FOOTER
 */
@Composable
fun FinalCtaAndFooter(
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxWidth()) {
        // Final CTA Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkPlumCard)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ready to Experience True Radiance?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, // RULE 2
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Book your appointment online today or call Sneha Patil directly.",
                    fontSize = 12.sp,
                    color = LightRoseContainer, // RULE 2
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBookClick,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RoseGold,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(48.dp)
                        .testTag("final_cta_book_button")
                ) {
                    Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Book Your Appointment Now",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // RULE 2
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // FOOTER DETAILS — RULE 1
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(DeepPlum)
                .padding(20.dp)
        ) {
            Text(
                text = BusinessConfig.BUSINESS_NAME,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = SoftGold // RULE 2
            )
            Text(
                text = BusinessConfig.TAGLINE,
                fontSize = 12.sp,
                color = LightRoseContainer // RULE 2
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Address
            Row(verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = RoseGold, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = BusinessConfig.ADDRESS,
                    fontSize = 12.sp,
                    color = Color.White // RULE 2
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Service Area
            Row(verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.Map, contentDescription = null, tint = RoseGold, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Service Area: ${BusinessConfig.SERVICE_AREA}",
                    fontSize = 12.sp,
                    color = Color.White // RULE 2
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Phone
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${BusinessConfig.PHONE}"))
                    context.startActivity(intent)
                }
            ) {
                Icon(Icons.Default.Call, contentDescription = null, tint = RoseGold, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Call: ${BusinessConfig.PHONE} (${BusinessConfig.OWNER_NAME})",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SoftGold // RULE 2
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Email
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Email, contentDescription = null, tint = RoseGold, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = BusinessConfig.EMAIL,
                    fontSize = 12.sp,
                    color = Color.White // RULE 2
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Map and Instagram links
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BusinessConfig.GOOGLE_MAPS_URL))
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SoftGold),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SoftGold),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Google Maps", fontSize = 11.sp, color = SoftGold)
                }

                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BusinessConfig.INSTAGRAM_URL))
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = SoftGold),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SoftGold),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Public, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Instagram", fontSize = 11.sp, color = SoftGold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // MANDATORY CREDIT LINE
            Text(
                text = "Website by ebookcharm Web Services",
                fontSize = 11.sp,
                color = SoftGold.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

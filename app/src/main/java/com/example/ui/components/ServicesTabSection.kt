package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ServiceCategory
import com.example.data.ServiceItem
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

@Composable
fun ServicesTabSection(
    onSelectForEstimator: (ServiceItem) -> Unit,
    onBookServiceDirectly: (ServiceItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(ServiceCategory.HAIR) }

    val activeServices = remember(selectedTab) {
        ServiceRepository.SERVICES.filter { it.category == selectedTab }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("services_section")
    ) {
        // Section Title Header
        Text(
            text = "OUR SIGNATURE SERVICES",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RoseGold,
            letterSpacing = 1.2.sp
        )
        Text(
            text = "Crafted for Radiance & Comfort",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPlumText
        )
        Spacer(modifier = Modifier.height(14.dp))

        // Tabs Row
        ScrollableTabRow(
            selectedTabIndex = selectedTab.ordinal,
            containerColor = Color.Transparent,
            contentColor = DeepPlum,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab.ordinal]),
                    color = DeepPlum,
                    height = 3.dp
                )
            }
        ) {
            ServiceCategory.values().forEach { cat ->
                val isSelected = selectedTab == cat
                Tab(
                    selected = isSelected,
                    onClick = { selectedTab = cat },
                    text = {
                        Text(
                            text = cat.displayName,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) DeepPlum else SoftGrayText
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Services Horizontal Carousel / Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            activeServices.forEach { service ->
                ServiceCardItem(
                    service = service,
                    onGetEstimate = { onSelectForEstimator(service) },
                    onBookDirect = { onBookServiceDirectly(service) }
                )
            }
        }
    }
}

/**
 * RULE 3 — CARD ALIGNMENT & RESPONSIVE LAYOUT
 */
@Composable
fun ServiceCardItem(
    service: ServiceItem,
    onGetEstimate: () -> Unit,
    onBookDirect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("service_card_${service.id}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MutedRoseBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryCardVisual(
                        category = service.category,
                        modifier = Modifier.size(44.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = service.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkPlumText
                            )
                            if (service.isPopular) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(AccentGold)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Popular",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkPlumText
                                    )
                                }
                            }
                        }
                        Text(
                            text = "${service.durationMinutes} min session",
                            fontSize = 11.sp,
                            color = SoftGrayText
                        )
                    }
                }

                // Price Tag
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Starts at",
                        fontSize = 10.sp,
                        color = SoftGrayText
                    )
                    Text(
                        text = "₹${service.startingPrice}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DeepPlum
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = service.description,
                fontSize = 13.sp,
                color = DarkPlumText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(14.dp))

            // RULE 3: Action Buttons aligned in one bottom row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onGetEstimate,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepPlum),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DeepPlum),
                    modifier = Modifier
                        .weight(1f)
                        .height(42.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Estimate",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onBookDirect,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepPlum,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(42.dp)
                ) {
                    Text(
                        text = "Book Now",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // RULE 2
                    )
                }
            }
        }
    }
}

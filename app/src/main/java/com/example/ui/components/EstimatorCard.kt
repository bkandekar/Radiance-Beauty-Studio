package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AddOnOption
import com.example.data.PackageTier
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EstimatorCard(
    onBookPackage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(ServiceCategory.HAIR) }
    
    val categoryServices = remember(selectedCategory) {
        ServiceRepository.SERVICES.filter { it.category == selectedCategory }
    }
    
    var selectedService by remember(selectedCategory) {
        mutableStateOf(categoryServices.firstOrNull() ?: ServiceRepository.SERVICES.first())
    }
    
    var selectedTier by remember { mutableStateOf(PackageTier.DELUXE) }
    val selectedAddOns = remember { mutableStateListOf<AddOnOption>() }

    var dropdownExpanded by remember { mutableStateOf(false) }

    val (minEst, maxEst) = remember(selectedService, selectedTier, selectedAddOns.toList()) {
        ServiceRepository.calculateEstimate(selectedService, selectedTier, selectedAddOns)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("estimator_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header Banner
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(DeepPlum),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalOffer,
                        contentDescription = null,
                        tint = SoftGold,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Instant Service & Package Cost Estimator",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkPlumText
                    )
                    Text(
                        text = "Customize your beauty package in seconds with zero hidden fees",
                        fontSize = 12.sp,
                        color = RoseGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Step 1: Category Selector Chips
            Text(
                text = "1. Select Category:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPlumText
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ServiceCategory.values().forEach { cat ->
                    val isSelected = selectedCategory == cat
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) DeepPlum else LightRoseContainer)
                            .border(
                                width = 1.dp,
                                color = if (isSelected) DeepPlum else MutedRoseBorder,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable {
                                selectedCategory = cat
                                selectedService = ServiceRepository.SERVICES.first { it.category == cat }
                            }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cat.displayName.split(" ").first(),
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else DarkPlumText // RULE 2
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Step 2: Specific Service Dropdown
            Text(
                text = "2. Select Specific Service:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPlumText
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(LightIvory)
                    .border(1.dp, MutedRoseBorder, RoundedCornerShape(14.dp))
                    .clickable { dropdownExpanded = true }
                    .padding(horizontal = 14.dp, vertical = 14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = selectedService.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkPlumText
                        )
                        Text(
                            text = "Starts at ₹${selectedService.startingPrice} • ${selectedService.durationMinutes} mins",
                            fontSize = 12.sp,
                            color = RoseGold
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Service",
                        tint = DeepPlum
                    )
                }

                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth(0.85f)
                ) {
                    categoryServices.forEach { serv ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(
                                        text = serv.name,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkPlumText
                                    )
                                    Text(
                                        text = serv.description,
                                        fontSize = 11.sp,
                                        color = SoftGrayText
                                    )
                                }
                            },
                            onClick = {
                                selectedService = serv
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Step 3: Package Tier Selection
            Text(
                text = "3. Choose Package Tier:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPlumText
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PackageTier.values().forEach { tier ->
                    val isSelected = selectedTier == tier
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedTier = tier },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) LightRoseContainer else LightIvory
                        ),
                        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, DeepPlum) else androidx.compose.foundation.BorderStroke(1.dp, MutedRoseBorder)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = tier.title,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) DeepPlum else DarkPlumText
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tier.includes,
                                fontSize = 10.sp,
                                color = SoftGrayText,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Step 4: Optional Add-ons
            Text(
                text = "4. Optional Add-ons:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPlumText
            )
            Spacer(modifier = Modifier.height(4.dp))
            ServiceRepository.ADD_ONS.take(3).forEach { addOn ->
                val isChecked = selectedAddOns.contains(addOn)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isChecked) selectedAddOns.remove(addOn) else selectedAddOns.add(addOn)
                        }
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {
                            if (it) selectedAddOns.add(addOn) else selectedAddOns.remove(addOn)
                        },
                        colors = CheckboxDefaults.colors(checkedColor = DeepPlum)
                    )
                    Text(
                        text = addOn.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = DarkPlumText,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "+₹${addOn.price}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepPlum
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // RULE 2: Colored Display Box with explicit white / light text contrast
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(DarkPlumCard)
                    .padding(18.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "ESTIMATED PACKAGE COST",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SoftGold, // RULE 2
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    AnimatedContent(
                        targetState = Pair(minEst, maxEst),
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "price_anim"
                    ) { (minP, maxP) ->
                        Text(
                            text = "₹${"%,d".format(minP)} – ₹${"%,d".format(maxP)}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White // RULE 2
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "*Final price confirmed after in-person consultation.",
                        fontSize = 11.sp,
                        color = LightRoseContainer, // RULE 2
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            val packageSummary = "${selectedService.name} (${selectedTier.title})" +
                                    if (selectedAddOns.isNotEmpty()) " + ${selectedAddOns.joinToString { it.name }}" else ""
                            onBookPackage(packageSummary)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RoseGold,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("book_this_package_button")
                    ) {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Book This Package",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // RULE 2
                        )
                    }
                }
            }
        }
    }
}

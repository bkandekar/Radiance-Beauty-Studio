package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentGold
import com.example.ui.theme.DarkPlumText
import com.example.ui.theme.DeepPlum
import com.example.ui.theme.LightRoseContainer
import com.example.ui.theme.MutedRoseBorder
import com.example.ui.theme.RoseGold
import com.example.ui.theme.SoftGold

data class DesignOption(
    val id: String,
    val title: String,
    val styleTag: String,
    val price: Int,
    val durationMins: Int
)

object CustomDesignData {
    val HAIR_DESIGNS = listOf(
        DesignOption("h_1", "Hollywood Soft Waves & Head Spa", "Glam Waves", 800, 45),
        DesignOption("h_2", "Keratin Sleek Mirror Finish", "Sleek Smooth", 2500, 120),
        DesignOption("h_3", "Balayage Caramel Gold Shimmer", "Dimensional Color", 1800, 90),
        DesignOption("h_4", "Traditional Marathi Floral Khopa", "Bridal Hair Art", 600, 30)
    )

    val MAKEUP_DESIGNS = listOf(
        DesignOption("m_1", "HD Water-Resistant Bridal Glam", "Sweatproof HD", 4500, 90),
        DesignOption("m_2", "Silicone Airbrush Feather Base", "24-Hr Airbrush", 6500, 120),
        DesignOption("m_3", "Soft Dewy Rose Sangeet Look", "Luminous Glow", 2200, 60),
        DesignOption("m_4", "Natural Mineral Fresh Glow", "Minimalist Clean", 1500, 45)
    )

    val NAIL_DESIGNS = listOf(
        DesignOption("n_1", "Swarovski Crystal 3D Gel Art", "Luxury Gems", 1500, 60),
        DesignOption("n_2", "French Ombre Shimmer Overlay", "Classic Elegant", 800, 40),
        DesignOption("n_3", "Rose Gold Foil Extension Polish", "Metallic Sparkle", 1100, 45)
    )

    val FACIAL_DESIGNS = listOf(
        DesignOption("f_1", "24K Gold Foil Polish Therapy", "Pure Gold Radiance", 1200, 50),
        DesignOption("f_2", "O3+ Oxygen De-Tan & Peel", "Tan Removal", 950, 40),
        DesignOption("f_3", "Fresh Herbal Fruit Clean-Up", "Organic Hydration", 500, 30)
    )
}

/**
 * INTERACTIVE "CUSTOM LOOK DESIGNER" MODULE
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomLookDesigner(
    onBookCustomLook: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedHair by remember { mutableStateOf(CustomDesignData.HAIR_DESIGNS[0]) }
    var selectedMakeup by remember { mutableStateOf(CustomDesignData.MAKEUP_DESIGNS[0]) }
    var selectedNail by remember { mutableStateOf<DesignOption?>(CustomDesignData.NAIL_DESIGNS[0]) }
    var selectedFacial by remember { mutableStateOf<DesignOption?>(CustomDesignData.FACIAL_DESIGNS[0]) }

    val totalPrice = selectedHair.price + selectedMakeup.price +
            (selectedNail?.price ?: 0) + (selectedFacial?.price ?: 0)
    val totalMins = selectedHair.durationMins + selectedMakeup.durationMins +
            (selectedNail?.durationMins ?: 0) + (selectedFacial?.durationMins ?: 0)

    val hours = totalMins / 60
    val remainingMins = totalMins % 60
    val durationText = if (hours > 0) "${hours}h ${remainingMins}m" else "${remainingMins} mins"

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("custom_look_designer_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, MutedRoseBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header Title
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(RoseGold.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = null,
                        tint = DeepPlum,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "DESIGN YOUR CUSTOM LOOK",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = RoseGold,
                        letterSpacing = 1.2.sp
                    )
                    Text(
                        text = "Mix & Match Beauty Styles",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkPlumText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Step 1: Hair Design
            DesignSelectorSection(
                sectionTitle = "1. Hair Styling Design",
                options = CustomDesignData.HAIR_DESIGNS,
                selectedOption = selectedHair,
                onSelect = { selectedHair = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Step 2: Makeup Design
            DesignSelectorSection(
                sectionTitle = "2. Makeup & Skin Base Design",
                options = CustomDesignData.MAKEUP_DESIGNS,
                selectedOption = selectedMakeup,
                onSelect = { selectedMakeup = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Step 3: Nail Art Design
            OptionalDesignSelectorSection(
                sectionTitle = "3. Nail Extension / Art (Optional)",
                options = CustomDesignData.NAIL_DESIGNS,
                selectedOption = selectedNail,
                onToggle = { option ->
                    selectedNail = if (selectedNail?.id == option.id) null else option
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Step 4: Facial Radiance Polish
            OptionalDesignSelectorSection(
                sectionTitle = "4. Pre-Care Facial Polish (Optional)",
                options = CustomDesignData.FACIAL_DESIGNS,
                selectedOption = selectedFacial,
                onToggle = { option ->
                    selectedFacial = if (selectedFacial?.id == option.id) null else option
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Live Customized Package Summary Box
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "YOUR CUSTOM DESIGNED PACKAGE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SoftGold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        SelectedTagChip(text = selectedHair.title)
                        SelectedTagChip(text = selectedMakeup.title)
                        selectedNail?.let { SelectedTagChip(text = it.title) }
                        selectedFacial?.let { SelectedTagChip(text = it.title) }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

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
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Est. Time: $durationText",
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "Total Estimated Cost",
                                fontSize = 10.sp,
                                color = SoftGold
                            )
                            Text(
                                text = "₹${"%,d".format(totalPrice)}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            val summary = "Custom Look: ${selectedHair.styleTag} + ${selectedMakeup.styleTag}" +
                                    (selectedNail?.let { " + ${it.styleTag}" } ?: "") +
                                    (selectedFacial?.let { " + ${it.styleTag}" } ?: "") +
                                    " (Total ₹$totalPrice)"
                            onBookCustomLook(summary)
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RoseGold,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                            .testTag("book_custom_design_button")
                    ) {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Reserve This Custom Look",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DesignSelectorSection(
    sectionTitle: String,
    options: List<DesignOption>,
    selectedOption: DesignOption,
    onSelect: (DesignOption) -> Unit
) {
    Column {
        Text(
            text = sectionTitle,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPlumText
        )
        Spacer(modifier = Modifier.height(6.dp))

        options.forEach { option ->
            val isSelected = option.id == selectedOption.id
            DesignOptionChoiceTile(
                option = option,
                isSelected = isSelected,
                onClick = { onSelect(option) }
            )
        }
    }
}

@Composable
private fun OptionalDesignSelectorSection(
    sectionTitle: String,
    options: List<DesignOption>,
    selectedOption: DesignOption?,
    onToggle: (DesignOption) -> Unit
) {
    Column {
        Text(
            text = sectionTitle,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPlumText
        )
        Spacer(modifier = Modifier.height(6.dp))

        options.forEach { option ->
            val isSelected = option.id == selectedOption?.id
            DesignOptionChoiceTile(
                option = option,
                isSelected = isSelected,
                onClick = { onToggle(option) }
            )
        }
    }
}

@Composable
private fun DesignOptionChoiceTile(
    option: DesignOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) LightRoseContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) DeepPlum else MutedRoseBorder,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) DeepPlum else Color.White)
                        .border(1.dp, DeepPlum, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = option.title,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = DarkPlumText
                    )
                    Text(
                        text = "${option.styleTag} • ${option.durationMins} mins",
                        fontSize = 10.sp,
                        color = RoseGold
                    )
                }
            }

            Text(
                text = "+₹${option.price}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DeepPlum
            )
        }
    }
}

@Composable
private fun SelectedTagChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(RoseGold.copy(alpha = 0.3f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

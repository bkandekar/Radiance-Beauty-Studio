package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BusinessConfig
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Palette
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.MaterialTheme
import com.example.ui.components.BookingModal
import com.example.ui.components.CustomLookDesigner
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.RadianceTheme
import com.example.ui.components.EstimatorCard
import com.example.ui.components.FinalCtaAndFooter
import com.example.ui.components.GallerySection
import com.example.ui.components.HeroSection
import com.example.ui.components.PainPointsSection
import com.example.ui.components.ProcessSection
import com.example.ui.components.ServicesTabSection
import com.example.ui.components.StatsBarSection
import com.example.ui.components.TestimonialsSection
import com.example.ui.theme.DarkPlumCard
import com.example.ui.theme.DarkPlumText
import com.example.ui.theme.DeepPlum
import com.example.ui.theme.LightIvory
import com.example.ui.theme.LightRoseContainer
import com.example.ui.theme.MutedRoseBorder
import com.example.ui.theme.RoseGold
import com.example.ui.theme.SoftGold
import kotlinx.coroutines.launch

enum class MainNavTab(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    HOME("Home", Icons.Default.Home),
    ESTIMATOR("Estimator", Icons.Default.Calculate),
    SERVICES("Services", Icons.Default.Spa),
    REVIEWS("Reviews", Icons.Default.RateReview),
    GALLERY("Gallery", Icons.Default.PhotoLibrary)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var selectedThemePreset by remember { mutableStateOf(AppThemePreset.ROSE_GOLD) }
    var showThemeSelectorDialog by remember { mutableStateOf(false) }

    var showBookingModal by remember { mutableStateOf(false) }
    var prefilledModalService by remember { mutableStateOf("") }

    var selectedNavTab by remember { mutableStateOf(MainNavTab.HOME) }

    fun openBooking(serviceName: String = "") {
        prefilledModalService = serviceName
        showBookingModal = true
    }

    // Index mappings for scroll targets
    val indexHero = 0
    val indexEstimator = 1
    val indexCustomDesigner = 2
    val indexServices = 3
    val indexPainPoints = 4
    val indexStats = 5
    val indexProcess = 6
    val indexReviews = 7
    val indexGallery = 8
    val indexFooter = 9

    fun scrollToSection(tab: MainNavTab) {
        selectedNavTab = tab
        coroutineScope.launch {
            val targetIndex = when (tab) {
                MainNavTab.HOME -> indexHero
                MainNavTab.ESTIMATOR -> indexEstimator
                MainNavTab.SERVICES -> indexServices
                MainNavTab.REVIEWS -> indexReviews
                MainNavTab.GALLERY -> indexGallery
            }
            listState.animateScrollToItem(targetIndex)
        }
    }

    RadianceTheme(themePreset = selectedThemePreset) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = BusinessConfig.BUSINESS_NAME,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = BusinessConfig.TAGLINE,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    actions = {
                        // Theme Switcher Button
                        IconButton(
                            onClick = { showThemeSelectorDialog = true },
                            modifier = Modifier.testTag("theme_selector_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Palette,
                                contentDescription = "Switch Theme Palette",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Header Prominent "Book Appointment" button
                        Button(
                            onClick = { openBooking() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .height(38.dp)
                                .testTag("header_book_appointment_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = SoftGold,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Book",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = SoftGold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets(0, 0, 0, 0))
                ) {
                    MainNavTab.values().forEach { tab ->
                        val isSelected = selectedNavTab == tab
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { scrollToSection(tab) },
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                    tint = if (isSelected) SoftGold else Color.White.copy(alpha = 0.6f)
                                )
                            },
                            label = {
                                Text(
                                    text = tab.label,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) SoftGold else Color.White.copy(alpha = 0.6f)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    // Section 1: Hero
                    HeroSection(
                        onBookClick = { openBooking() },
                        onEstimateClick = { scrollToSection(MainNavTab.ESTIMATOR) }
                    )
                }

                item {
                    // Section 2: Estimator Card (Core Feature)
                    EstimatorCard(
                        onBookPackage = { pkgSummary ->
                            openBooking(pkgSummary)
                        }
                    )
                }

                item {
                    // Section 3: Custom Look Designer (Interactive Mix & Match)
                    CustomLookDesigner(
                        onBookCustomLook = { customSummary ->
                            openBooking(customSummary)
                        }
                    )
                }

                item {
                    // Section 4: Tabbed Services
                    ServicesTabSection(
                        onSelectForEstimator = { service ->
                            scrollToSection(MainNavTab.ESTIMATOR)
                        },
                        onBookServiceDirectly = { service ->
                            openBooking(service.name)
                        }
                    )
                }

                item {
                    // Section 5: Pain Points -> Solutions
                    PainPointsSection()
                }

                item {
                    // Section 6: Stats Counter Bar
                    StatsBarSection()
                }

                item {
                    // Section 7: Process
                    ProcessSection()
                }

                item {
                    // Section 8: Testimonials
                    TestimonialsSection()
                }

                item {
                    // Section 9: Gallery Lookbook & Design Catalog
                    GallerySection(
                        onSelectLookForBooking = { lookTitle ->
                            openBooking(lookTitle)
                        }
                    )
                }

                item {
                    // Section 10: Final CTA Banner & Footer
                    FinalCtaAndFooter(
                        onBookClick = { openBooking() }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }

    // Theme Switcher Dialog
    if (showThemeSelectorDialog) {
        ThemeSelectorDialog(
            currentPreset = selectedThemePreset,
            onSelectPreset = { preset ->
                selectedThemePreset = preset
                showThemeSelectorDialog = false
            },
            onDismiss = { showThemeSelectorDialog = false }
        )
    }

    // Booking Dialog Modal
    if (showBookingModal) {
        BookingModal(
            prefilledService = prefilledModalService,
            onDismiss = { showBookingModal = false }
        )
    }
}

@Composable
fun ThemeSelectorDialog(
    currentPreset: AppThemePreset,
    onSelectPreset: (AppThemePreset) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Choose Studio Theme",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                AppThemePreset.values().forEach { preset ->
                    val isSelected = preset == currentPreset
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { onSelectPreset(preset) },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant
                        ),
                        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = preset.displayName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(preset.primaryColor)
                                )
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(preset.containerColor)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


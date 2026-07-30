package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class AppThemePreset(
    val displayName: String,
    val primaryColor: Color,
    val containerColor: Color,
    val cardColor: Color
) {
    ROSE_GOLD("Rose Gold Studio", RoseGold, DeepPlum, DarkPlumCard),
    ROYAL_GOLD("Royal Velvet Gold", RoyalGold, CrimsonWine, RoyalNavyCard),
    EMERALD_GODDESS("Emerald Goddess", EmeraldGold, DeepEmerald, EmeraldCard),
    BLUSH_ROMANCE("Blush Romance", SoftBlushPink, DeepVelvetRose, BlushRoseCard)
}

private fun getLightColorScheme(preset: AppThemePreset) = when (preset) {
    AppThemePreset.ROSE_GOLD -> lightColorScheme(
        primary = RoseGold,
        onPrimary = Color.White,
        primaryContainer = DeepPlum,
        onPrimaryContainer = Color.White,
        secondary = DeepPlum,
        onSecondary = Color.White,
        secondaryContainer = LightRoseContainer,
        onSecondaryContainer = DarkPlumText,
        tertiary = AccentGold,
        onTertiary = DarkPlumText,
        background = LightIvory,
        onBackground = DarkPlumText,
        surface = Color.White,
        onSurface = DarkPlumText,
        surfaceVariant = WarmBlush,
        onSurfaceVariant = DarkPlumText,
        outline = MutedRoseBorder
    )
    AppThemePreset.ROYAL_GOLD -> lightColorScheme(
        primary = RoyalGold,
        onPrimary = Color.Black,
        primaryContainer = CrimsonWine,
        onPrimaryContainer = Color.White,
        secondary = CrimsonWine,
        onSecondary = Color.White,
        secondaryContainer = ChampagneIvory,
        onSecondaryContainer = RoyalNavy,
        tertiary = RoyalGold,
        onTertiary = Color.Black,
        background = ChampagneIvory,
        onBackground = RoyalNavy,
        surface = Color.White,
        onSurface = RoyalNavy,
        surfaceVariant = ChampagneIvory,
        onSurfaceVariant = RoyalNavy,
        outline = RoyalGold
    )
    AppThemePreset.EMERALD_GODDESS -> lightColorScheme(
        primary = SageGreen,
        onPrimary = Color.White,
        primaryContainer = DeepEmerald,
        onPrimaryContainer = Color.White,
        secondary = DeepEmerald,
        onSecondary = Color.White,
        secondaryContainer = EmeraldMint,
        onSecondaryContainer = DeepEmerald,
        tertiary = EmeraldGold,
        onTertiary = Color.Black,
        background = EmeraldMint,
        onBackground = DeepEmerald,
        surface = Color.White,
        onSurface = DeepEmerald,
        surfaceVariant = EmeraldMint,
        onSurfaceVariant = DeepEmerald,
        outline = SageGreen
    )
    AppThemePreset.BLUSH_ROMANCE -> lightColorScheme(
        primary = SoftBlushPink,
        onPrimary = Color.White,
        primaryContainer = DeepVelvetRose,
        onPrimaryContainer = Color.White,
        secondary = DeepVelvetRose,
        onSecondary = Color.White,
        secondaryContainer = PearlIvory,
        onSecondaryContainer = DeepVelvetRose,
        tertiary = SoftBlushPink,
        onTertiary = Color.White,
        background = PearlIvory,
        onBackground = DeepVelvetRose,
        surface = Color.White,
        onSurface = DeepVelvetRose,
        surfaceVariant = PearlIvory,
        onSurfaceVariant = DeepVelvetRose,
        outline = SoftBlushPink
    )
}

private fun getDarkColorScheme(preset: AppThemePreset) = when (preset) {
    AppThemePreset.ROSE_GOLD -> darkColorScheme(
        primary = RoseGold,
        onPrimary = Color.White,
        primaryContainer = DarkPlumCard,
        onPrimaryContainer = Color.White,
        secondary = SoftGold,
        onSecondary = DarkPlumText,
        secondaryContainer = DeepPlum,
        onSecondaryContainer = Color.White,
        tertiary = SoftGold,
        background = DarkPlumText,
        onBackground = LightIvory,
        surface = DarkPlumCard,
        onSurface = LightIvory,
        surfaceVariant = DeepPlum,
        onSurfaceVariant = LightIvory,
        outline = MutedRoseBorder
    )
    AppThemePreset.ROYAL_GOLD -> darkColorScheme(
        primary = RoyalGold,
        onPrimary = Color.Black,
        primaryContainer = RoyalNavyCard,
        onPrimaryContainer = Color.White,
        secondary = RoyalGold,
        onSecondary = Color.Black,
        secondaryContainer = CrimsonWine,
        onSecondaryContainer = Color.White,
        tertiary = RoyalGold,
        background = RoyalNavyCard,
        onBackground = ChampagneIvory,
        surface = RoyalNavy,
        onSurface = ChampagneIvory,
        surfaceVariant = CrimsonWine,
        onSurfaceVariant = ChampagneIvory,
        outline = RoyalGold
    )
    AppThemePreset.EMERALD_GODDESS -> darkColorScheme(
        primary = SageGreen,
        onPrimary = Color.White,
        primaryContainer = EmeraldCard,
        onPrimaryContainer = Color.White,
        secondary = EmeraldGold,
        onSecondary = Color.Black,
        secondaryContainer = DeepEmerald,
        onSecondaryContainer = Color.White,
        tertiary = EmeraldGold,
        background = EmeraldCard,
        onBackground = EmeraldMint,
        surface = DeepEmerald,
        onSurface = EmeraldMint,
        surfaceVariant = DeepEmerald,
        onSurfaceVariant = EmeraldMint,
        outline = SageGreen
    )
    AppThemePreset.BLUSH_ROMANCE -> darkColorScheme(
        primary = SoftBlushPink,
        onPrimary = Color.White,
        primaryContainer = BlushRoseCard,
        onPrimaryContainer = Color.White,
        secondary = SoftBlushPink,
        onSecondary = Color.White,
        secondaryContainer = DeepVelvetRose,
        onSecondaryContainer = Color.White,
        tertiary = SoftBlushPink,
        background = BlushRoseCard,
        onBackground = PearlIvory,
        surface = DeepVelvetRose,
        onSurface = PearlIvory,
        surfaceVariant = DeepVelvetRose,
        onSurfaceVariant = PearlIvory,
        outline = SoftBlushPink
    )
}

@Composable
fun RadianceTheme(
    themePreset: AppThemePreset = AppThemePreset.ROSE_GOLD,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) getDarkColorScheme(themePreset) else getLightColorScheme(themePreset)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    RadianceTheme(themePreset = AppThemePreset.ROSE_GOLD, darkTheme = darkTheme, content = content)
}



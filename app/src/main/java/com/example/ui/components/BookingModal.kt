package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.BusinessConfig
import com.example.ui.theme.DarkPlumCard
import com.example.ui.theme.DarkPlumText
import com.example.ui.theme.DeepPlum
import com.example.ui.theme.LightIvory
import com.example.ui.theme.LightRoseContainer
import com.example.ui.theme.MutedRoseBorder
import com.example.ui.theme.RoseGold
import com.example.ui.theme.SoftGold
import com.example.ui.theme.SoftGrayText
import java.net.URLEncoder

@Composable
fun BookingModal(
    prefilledService: String = "",
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedService by remember { mutableStateOf(if (prefilledService.isBlank()) "HD Bridal Makeup / Gold Facial" else prefilledService) }
    var preferredDate by remember { mutableStateOf("Tomorrow (Flexible)") }
    var preferredTimeSlot by remember { mutableStateOf("Morning (10 AM - 1 PM)") }
    var additionalNotes by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var isRedirecting by remember { mutableStateOf(false) }

    val dateOptions = listOf("Today", "Tomorrow (Flexible)", "This Weekend", "Next Week")
    val timeSlotOptions = listOf("Morning (10 AM - 1 PM)", "Afternoon (1 PM - 4 PM)", "Evening (4 PM - 7 PM)")

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(LightRoseContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Spa,
                                contentDescription = null,
                                tint = DeepPlum,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Book Appointment",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkPlumText
                            )
                            Text(
                                text = BusinessConfig.BUSINESS_NAME,
                                fontSize = 12.sp,
                                color = RoseGold
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("close_booking_modal_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Modal",
                            tint = DarkPlumText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isRedirecting) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = DeepPlum)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Redirecting to WhatsApp...",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkPlumText
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Opening chat with ${BusinessConfig.BUSINESS_NAME} (${BusinessConfig.WHATSAPP})",
                            fontSize = 13.sp,
                            color = RoseGold,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    // Full Name
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = {
                            fullName = it
                            if (it.isNotBlank()) nameError = false
                        },
                        label = { Text("Full Name *") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = DeepPlum) },
                        isError = nameError,
                        supportingText = { if (nameError) Text("Full Name is required", color = Color.Red) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = DeepPlum,
                            unfocusedBorderColor = MutedRoseBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_full_name")
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Phone Number
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            if (it.isNotBlank()) phoneError = false
                        },
                        label = { Text("Phone Number *") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = DeepPlum) },
                        isError = phoneError,
                        supportingText = { if (phoneError) Text("10-digit phone number required", color = Color.Red) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = DeepPlum,
                            unfocusedBorderColor = MutedRoseBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_phone_number")
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Preferred Service
                    OutlinedTextField(
                        value = selectedService,
                        onValueChange = { selectedService = it },
                        label = { Text("Preferred Service / Estimated Package") },
                        leadingIcon = { Icon(Icons.Default.Spa, contentDescription = null, tint = DeepPlum) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = DeepPlum,
                            unfocusedBorderColor = MutedRoseBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_selected_service")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Date Selection
                    Text(
                        text = "Preferred Day:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkPlumText
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        dateOptions.take(3).forEach { option ->
                            val isSelected = preferredDate == option
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) DeepPlum else LightRoseContainer)
                                    .clickable { preferredDate = option }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else DarkPlumText
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Time Slot
                    Text(
                        text = "Preferred Time Slot:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkPlumText
                    )
                    timeSlotOptions.forEach { slot ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { preferredTimeSlot = slot }
                                .padding(vertical = 2.dp)
                        ) {
                            RadioButton(
                                selected = preferredTimeSlot == slot,
                                onClick = { preferredTimeSlot = slot },
                                colors = RadioButtonDefaults.colors(selectedColor = DeepPlum)
                            )
                            Text(
                                text = slot,
                                fontSize = 13.sp,
                                color = DarkPlumText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Additional Notes
                    OutlinedTextField(
                        value = additionalNotes,
                        onValueChange = { additionalNotes = it },
                        label = { Text("Special Instructions / Skin Sensitivity (Optional)") },
                        leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null, tint = DeepPlum) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = DeepPlum,
                            unfocusedBorderColor = MutedRoseBorder
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // RULE 1: Direct WhatsApp redirect using BUSINESS_CONFIG.WHATSAPP
                    Button(
                        onClick = {
                            var valid = true
                            if (fullName.isBlank()) {
                                nameError = true
                                valid = false
                            }
                            if (phoneNumber.isBlank() || phoneNumber.length < 8) {
                                phoneError = true
                                valid = false
                            }

                            if (valid) {
                                isRedirecting = true
                                val messageStr = buildString {
                                    append("Hello ${BusinessConfig.BUSINESS_NAME}!\n")
                                    append("I would like to book an appointment.\n\n")
                                    append("*Name:* ${fullName.trim()}\n")
                                    append("*Phone:* ${phoneNumber.trim()}\n")
                                    append("*Service:* ${selectedService.trim()}\n")
                                    append("*Preferred Day:* $preferredDate\n")
                                    append("*Time Slot:* $preferredTimeSlot\n")
                                    if (additionalNotes.isNotBlank()) {
                                        append("*Notes:* ${additionalNotes.trim()}\n")
                                    }
                                    append("\nPlease confirm available slot!")
                                }

                                try {
                                    val encodedMsg = URLEncoder.encode(messageStr, "UTF-8")
                                    val whatsappUrl = "https://wa.me/${BusinessConfig.WHATSAPP}?text=$encodedMsg"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not open WhatsApp", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DeepPlum,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("submit_booking_whatsapp_button")
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Book Now via WhatsApp",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // RULE 2
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Instant response from Sneha Patil (${BusinessConfig.PHONE})",
                        fontSize = 11.sp,
                        color = SoftGrayText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

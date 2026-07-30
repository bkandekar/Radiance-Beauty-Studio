package com.example.data

/**
 * RULE 1 — SINGLE SOURCE OF TRUTH FOR BUSINESS CONTACT INFO
 * All phone, WhatsApp, address, and client details are centralized here.
 */
object BusinessConfig {
    const val PHONE = "9876543210"
    const val WHATSAPP = "919876543210"
    const val BUSINESS_NAME = "Radiance Beauty Studio"
    const val TAGLINE = "Where Every Look Feels Like You"
    const val OWNER_NAME = "Sneha Patil"
    const val EMAIL = "radiancebeautystudio@gmail.com"
    const val ADDRESS = "Shop No. 4, Shivaji Chowk, Ratnagiri, Maharashtra 415612"
    const val SERVICE_AREA = "Ratnagiri city and nearby areas (Nachane, Zadgaon, Kasarveli)"
    const val YEARS_IN_BUSINESS = "6 Years"
    const val CLIENTS_SERVED = "2,000+"
    const val CERTIFICATION = "Certified from VLCC Institute of Beauty & Nutrition"
    const val INSTAGRAM_HANDLE = "@radiancebeautystudio_rtn"
    const val INSTAGRAM_URL = "https://instagram.com/radiancebeautystudio_rtn"
    const val GOOGLE_MAPS_URL = "https://maps.google.com/?q=Shop+No.+4,+Shivaji+Chowk,+Ratnagiri,+Maharashtra+415612"
}

enum class ServiceCategory(val displayName: String) {
    HAIR("Hair Care"),
    SKIN("Skin & Facials"),
    BRIDAL("Bridal & Party"),
    NAILS("Nails"),
    THREADING_WAXING("Threading & Waxing")
}

data class ServiceItem(
    val id: String,
    val category: ServiceCategory,
    val name: String,
    val description: String,
    val startingPrice: Int,
    val durationMinutes: Int,
    val isPopular: Boolean = false
)

enum class PackageTier(
    val title: String,
    val multiplier: Float,
    val subtitle: String,
    val includes: String
) {
    BASIC("Basic Care", 1.0f, "Standard parlour service with essential care", "Standard Products + Essential Care"),
    DELUXE("Deluxe Glow", 1.3f, "Upgraded premium products & serum massage", "Premium Ampoule + Serum Massage"),
    PREMIUM("Luxury Royal", 1.6f, "VIP spa therapy + complimentary LED/scalp treat", "Luxury Spa Therapy + Complimentary Gift")
}

data class AddOnOption(
    val id: String,
    val name: String,
    val price: Int,
    val description: String
)

data class PainPointSolution(
    val painPoint: String,
    val solution: String,
    val iconName: String
)

data class Testimonial(
    val id: String,
    val name: String,
    val locality: String,
    val rating: Int = 5,
    val quote: String,
    val serviceUsed: String
)

data class GalleryItem(
    val id: String,
    val category: ServiceCategory,
    val title: String,
    val tag: String
)

data class DesignLookItem(
    val id: String,
    val category: ServiceCategory,
    val title: String,
    val tag: String,
    val description: String,
    val highlights: List<String>,
    val durationMinutes: Int,
    val estimatedPrice: Int,
    val isPopular: Boolean = false
)

data class ProcessStep(
    val stepNumber: Int,
    val title: String,
    val description: String
)

/**
 * STATIC SERVICE CATALOGUE & ESTIMATOR DATA
 */
object ServiceRepository {

    val PAIN_POINTS = listOf(
        PainPointSolution(
            painPoint = "I never know what a service will actually cost until I'm billed.",
            solution = "Transparent instant cost estimator right on our app with zero hidden charges.",
            iconName = "calculator"
        ),
        PainPointSolution(
            painPoint = "I'm not sure if products used are safe or hygienic for my skin & hair.",
            solution = "100% genuine named brands, disposable capes, and VLCC sterilized toolkits.",
            iconName = "sanitized"
        ),
        PainPointSolution(
            painPoint = "Bridal makeup is a huge decision — I need to see real local work first.",
            solution = "Explore 50+ real Ratnagiri bride photos, HD makeup portfolios, and video trials.",
            iconName = "bridal"
        ),
        PainPointSolution(
            painPoint = "I don't have time to wait around endlessly at a crowded parlour.",
            solution = "Slot-based appointment booking system ensuring zero waiting time.",
            iconName = "time"
        ),
        PainPointSolution(
            painPoint = "I want a stylist who understands modern aesthetic trends & skin types.",
            solution = "Sneha Patil & staff are VLCC-certified with annual trend upgrade masterclasses.",
            iconName = "certified"
        )
    )

    val SERVICES = listOf(
        // Hair
        ServiceItem("h1", ServiceCategory.HAIR, "Haircut & Custom Styling", "Precision cut, wash, blow-dry & expert heat styling", 350, 45, true),
        ServiceItem("h2", ServiceCategory.HAIR, "Deep Nourishing Hair Spa", "Scalp steam, intense keratin mask & 20-min head massage", 800, 60, true),
        ServiceItem("h3", ServiceCategory.HAIR, "Permanent Hair Smoothening", "L'Oreal / Matrix protein treatment for ultra-sleek shiny locks", 2500, 180),
        ServiceItem("h4", ServiceCategory.HAIR, "Organic Henna / Color Touchup", "Ammonia-free herbal root touchup & conditioning", 450, 45),

        // Skin
        ServiceItem("s1", ServiceCategory.SKIN, "Radiance Gold Facial", "24K Gold foil extract, deep pore massage & radiance mask", 1200, 75, true),
        ServiceItem("s2", ServiceCategory.SKIN, "Herbal Fruit Glow Facial", "Fresh organic fruit enzyme blend for sensitive & youth skin", 800, 60),
        ServiceItem("s3", ServiceCategory.SKIN, "O3+ Anti-Tan / De-Tan Facial", "Instant sun-tan removal, melanin control & cooling ice globe massage", 950, 60, true),
        ServiceItem("s4", ServiceCategory.SKIN, "Hydra-Glow Cleanup", "Ultrasonic exfoliation, blackhead extraction & hyaluronic surge", 650, 40),

        // Bridal & Party
        ServiceItem("b1", ServiceCategory.BRIDAL, "HD Bridal Makeover Package", "Waterproof HD air-puff makeup, saree draping, hair art & lashes", 8000, 180, true),
        ServiceItem("b2", ServiceCategory.BRIDAL, "Airbrush Luxury Bridal Look", "Ultra-lightweight 24-hr flawless silicone airbrush bridal finish", 12000, 210),
        ServiceItem("b3", ServiceCategory.BRIDAL, "Glam Party / Sangeet Makeup", "Soft glam foundation, subtle shimmer eye, hairstyling & draping", 2500, 90, true),
        ServiceItem("b4", ServiceCategory.BRIDAL, "Pre-Bridal Glow Ritual (3-Day)", "Full body polish, gold facial, body wax, hair spa & manicure", 5000, 240),

        // Nails
        ServiceItem("n1", ServiceCategory.NAILS, "Spa Manicure & Pedicure", "Aromatherapy bath, heel scrub, nail shaping & cuticle butter massage", 1100, 75, true),
        ServiceItem("n2", ServiceCategory.NAILS, "Gel Polish & Nail Art", "Long-lasting chip-free UV gel overlay with custom glitter/floral art", 1500, 60),
        ServiceItem("n3", ServiceCategory.NAILS, "Express Nail Shaping & Polish", "Cuticle trim, buffing & premium O.P.I shade application", 300, 30),

        // Threading & Waxing
        ServiceItem("w1", ServiceCategory.THREADING_WAXING, "Full Face Threading & Eyebrows", "Organic cotton thread eyebrow shaping, upper lip & forehead", 120, 20),
        ServiceItem("w2", ServiceCategory.THREADING_WAXING, "RICA Chocolate Waxing (Full Arms + Legs)", "Low-pain painless RICA wax, skin soothing aloe gel coat", 950, 50, true),
        ServiceItem("w3", ServiceCategory.THREADING_WAXING, "Underarms RICA Waxing", "Soothing anti-tan chocolate wax for delicate underarm skin", 180, 15)
    )

    val ADD_ONS = listOf(
        AddOnOption("a1", "Extra Scalp/Hair Repair Serum", 400, "Concentrated argan & keratin ampoule infusion"),
        AddOnOption("a2", "Upgraded De-Tan Scrub Coat", 300, "Clove & eucalyptus sun tan extraction pack"),
        AddOnOption("a3", "3D Glitter Nail Accents", 200, "Swarovski stone & foil embellishment per hand"),
        AddOnOption("a4", "Hydrating Eye Contour Mask", 250, "Collagen hydrogel patch for dark circles & puffiness"),
        AddOnOption("a5", "Extended 15-min Foot Massage", 350, "Warm lavender oil reflexology massage")
    )

    val TESTIMONIALS = listOf(
        Testimonial(
            "t1",
            "Anagha Joshi",
            "Zadgaon, Ratnagiri",
            5,
            "Sneha tai made me look like an absolute dream on my wedding day! The HD bridal makeup stayed fresh for 14 hours through all rituals. Every bride in Ratnagiri must book Radiance!",
            "HD Bridal Package"
        ),
        Testimonial(
            "t2",
            "Pooja Sawant",
            "Nachane, Ratnagiri",
            5,
            "The Fruit Facial and Deep Hair Spa are super therapeutic after a long work week. Cleanest and most hygienic parlour in Shivaji Chowk. Extremely polite staff!",
            "Fruit Facial & Hair Spa"
        ),
        Testimonial(
            "t3",
            "Rutuja Mane",
            "Kasarveli, Ratnagiri",
            5,
            "I love the interactive cost estimator on their app! Knowing the package cost beforehand builds so much trust. The RICA waxing was virtually painless.",
            "RICA Full Body Wax & Manicure"
        ),
        Testimonial(
            "t4",
            "Priya Kulkarni",
            "Shivaji Chowk, Ratnagiri",
            5,
            "VLCC certified expertise really shows. Sneha ma'am analyzed my sensitive skin before recommending the Gold Facial. My skin was glowing for weeks!",
            "24K Gold Facial"
        )
    )

    val PROCESS_STEPS = listOf(
        ProcessStep(1, "Easy Online Booking", "Select your desired services or use our estimator to reserve a guaranteed time slot via WhatsApp."),
        ProcessStep(2, "Personal Consultation", "Upon arrival, our VLCC-certified experts analyze your hair/skin type to customize products."),
        ProcessStep(3, "Pampering & Service", "Relax in our hygienic studio while receiving expert care with 100% authentic branded products."),
        ProcessStep(4, "Aftercare & Glow Tips", "Leave with a flawless look plus customized home-care beauty routines for lasting radiance.")
    )

    val GALLERY_ITEMS = listOf(
        GalleryItem("g1", ServiceCategory.BRIDAL, "Traditional Maharashtrian Bridal Look", "HD Bridal Makeup"),
        GalleryItem("g2", ServiceCategory.HAIR, "Silk Shine Keratin Hair Spa", "Hair Care"),
        GalleryItem("g3", ServiceCategory.SKIN, "24K Gold Radiance Facial Treatment", "Skin Treatment"),
        GalleryItem("g4", ServiceCategory.NAILS, "Rose Gold Shimmer Gel Nail Art", "Nail Extensions"),
        GalleryItem("g5", ServiceCategory.BRIDAL, "Glam Sangeet Party Makeover", "Party Makeup"),
        GalleryItem("g6", ServiceCategory.HAIR, "Balayage & Modern Layer Haircut", "Styling & Cut")
    )

    val DESIGN_LOOKS = listOf(
        DesignLookItem(
            id = "dl1",
            category = ServiceCategory.BRIDAL,
            title = "Traditional Maharashtrian Nauvari Bride",
            tag = "HD Water-Resistant Makeup",
            description = "Rich traditional Marathi bride makeover featuring gold/bronze shimmer eyes, long-wear matte base, nauvari saree draping & khopa hair adornment.",
            highlights = listOf("14-Hour Sweatproof Finish", "Chandrakor Bindi & Nath Styling", "Silk Saree Draping Included", "Custom Mink Eye Lashes"),
            durationMinutes = 180,
            estimatedPrice = 8500,
            isPopular = true
        ),
        DesignLookItem(
            id = "dl2",
            category = ServiceCategory.BRIDAL,
            title = "Royal Rajwadi Airbrush Bridal Look",
            tag = "24-Hr Silicone Airbrush",
            description = "Ultra-lightweight feather-feel airbrush finish designed for high-definition photography with velvet royal red/maroon hues and jewelry setting.",
            highlights = listOf("Flawless Airbrush Base", "Zero Flashback Powder", "Duplicating Jewelry Setting", "Complimentary Touch-up Kit"),
            durationMinutes = 210,
            estimatedPrice = 12000,
            isPopular = true
        ),
        DesignLookItem(
            id = "dl3",
            category = ServiceCategory.BRIDAL,
            title = "Soft Glam Sangeet & Reception Look",
            tag = "Dewy Rose Glow",
            description = "Modern luminous soft glam with champagne glitter eyes, glossy blush lips, and textured soft Hollywood waves for evening celebrations.",
            highlights = listOf("Dewy Luminous Foundation", "Textured Wave Hairstyling", "Soft Contour & Highlight", "Long-Wear Lip Stain"),
            durationMinutes = 90,
            estimatedPrice = 2800
        ),
        DesignLookItem(
            id = "dl4",
            category = ServiceCategory.HAIR,
            title = "Silk Shine Keratin Hair Transformation",
            tag = "Frizz-Free Smoothing",
            description = "Intense formaline-safe protein infusion sealing split ends, eliminating humidity frizz, and restoring 100% natural mirror shine.",
            highlights = listOf("4-6 Month Smoothness", "Deep Nourishing Mask", "Heat Protection Shield", "Post-Care Shampoo Guide"),
            durationMinutes = 150,
            estimatedPrice = 3200,
            isPopular = true
        ),
        DesignLookItem(
            id = "dl5",
            category = ServiceCategory.HAIR,
            title = "Balayage Caramel & Gold Highlights",
            tag = "Ammonia-Free Color",
            description = "Hand-painted seamless dimensional balayage highlights tailored to Indian skin tones with volumizing layer haircut & blowdry.",
            highlights = listOf("Custom Shade Consultation", "Bond Building Treatment", "Precision Layer Haircut", "Vibrant Color Lock Coat"),
            durationMinutes = 120,
            estimatedPrice = 2500
        ),
        DesignLookItem(
            id = "dl6",
            category = ServiceCategory.SKIN,
            title = "24K Gold Foil Polish Radiance Facial",
            tag = "Luxury Skin Renewal",
            description = "Pure 24K gold leaves massage with ultrasonic peel, lymphatic drainage roller therapy, and gold collagen rubberizing mask.",
            highlights = listOf("Instant Luminous Radiance", "Collagen Tightening Massage", "Cooling Ice Globe Therapy", "Dark Circle Brightening"),
            durationMinutes = 75,
            estimatedPrice = 1800,
            isPopular = true
        ),
        DesignLookItem(
            id = "dl7",
            category = ServiceCategory.NAILS,
            title = "Rose Gold Swarovski Gel Extensions",
            tag = "3D Nail Art & Foils",
            description = "Long-wearing UV gel extensions with custom metallic rose gold foils, hand-placed Swarovski crystals, and cuticle oil hydration.",
            highlights = listOf("Non-Chipping 4-Week Gel", "Custom Nail Shape Choice", "Swarovski Embellishments", "Cuticle Butter Seal"),
            durationMinutes = 75,
            estimatedPrice = 1500
        ),
        DesignLookItem(
            id = "dl8",
            category = ServiceCategory.SKIN,
            title = "O3+ Oxygen De-Tan & Brightening Ritual",
            tag = "Instant Tan Extraction",
            description = "Medical-grade O3+ botanical peel removing deep sun tan from Ratnagiri coastal humidity, followed by oxygenating serum infusion.",
            highlights = listOf("100% Tan Extraction", "Hyaluronic Acid Surge", "Pore Refining Scrub", "SPF 50 Solar Shield Coat"),
            durationMinutes = 60,
            estimatedPrice = 1200
        )
    )

    fun calculateEstimate(
        service: ServiceItem,
        tier: PackageTier,
        selectedAddOns: List<AddOnOption>
    ): Pair<Int, Int> {
        val baseTierPrice = (service.startingPrice * tier.multiplier).toInt()
        val addOnTotal = selectedAddOns.sumOf { it.price }
        val exactMin = baseTierPrice + addOnTotal
        val exactMax = (exactMin * 1.15f).toInt() // realistic range window
        return Pair(exactMin, exactMax)
    }
}

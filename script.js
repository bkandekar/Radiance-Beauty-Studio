/**
 * ==========================================================================
 * RULE 1 — SINGLE SOURCE OF TRUTH FOR BUSINESS CONTACT INFO
 * All interactive booking/dialer code pulls strictly from this single object.
 * ==========================================================================
 */
const BUSINESS_CONFIG = {
  phone: "9876543210",
  whatsapp: "919876543210",
  businessName: "Radiance Beauty Studio"
};

/**
 * ==========================================================================
 * RULE 5 — STATIC VS DYNAMIC CONTENT: EDITABLE DATA ARRAYS
 * Edit service details, prices, testimonials, and gallery items below.
 * ==========================================================================
 */

const CATEGORY_LABELS = {
  hair: 'Hair Care',
  skin: 'Skin & Facials',
  bridal: 'Bridal & Party',
  nails: 'Nails',
  waxing: 'Threading & Waxing'
};

const SERVICES_DATA = [
  // HAIR CARE
  { id: 'h1', category: 'hair', name: 'Hydrating Hair Spa', price: 650, duration: 45, isPopular: true, desc: 'Deep nourishment treatment with intense steam & scalp massage.' },
  { id: 'h2', category: 'hair', name: 'Keratin Hair Treatment', price: 2500, duration: 120, isPopular: true, desc: 'Frizz-control keratin smoothing for sleek, shiny, manageable hair.' },
  { id: 'h3', category: 'hair', name: 'Global Hair Color & Highlights', price: 1800, duration: 90, isPopular: false, desc: 'Ammonia-free vibrant hair color customized to your skin tone.' },
  { id: 'h4', category: 'hair', name: 'Advance Layer Haircut & Blowdry', price: 350, duration: 30, isPopular: false, desc: 'Precision haircut with volumizing blowdry styling.' },

  // SKIN & FACIALS
  { id: 's1', category: 'skin', name: 'O3+ Bridal Glow Facial', price: 1800, duration: 60, isPopular: true, desc: 'Oxygenating brightening facial for instant luminous radiance.' },
  { id: 's2', category: 'skin', name: 'Herbal Organic Clean-Up', price: 450, duration: 30, isPopular: false, desc: 'Pore decongestion & gentle exfoliation using pure herbal extracts.' },
  { id: 's3', category: 'skin', name: 'Gold Radiance Facial', price: 1200, duration: 50, isPopular: true, desc: '24K gold foil infusion facial restoring elasticity & glow.' },
  { id: 's4', category: 'skin', name: 'Anti-Tan De-Tan Facial', price: 800, duration: 40, isPopular: false, desc: 'Removes stubborn sun tan and restores natural skin tone.' },

  // BRIDAL & PARTY
  { id: 'b1', category: 'bridal', name: 'HD Airbrush Bridal Makeup Package', price: 8500, duration: 180, isPopular: true, desc: 'Complete 12-hr water-resistant HD bridal look with hair & saree draping.' },
  { id: 'b2', category: 'bridal', name: 'Engagement / Pre-Wedding Makeup', price: 3500, duration: 90, isPopular: false, desc: 'Sophisticated subtle glam makeup with custom hairstyle.' },
  { id: 'b3', category: 'bridal', name: 'Party Glam Makeup & Hair Styling', price: 1500, duration: 60, isPopular: true, desc: 'Flawless foundation base with dramatic eye makeup.' },

  // NAILS
  { id: 'n1', category: 'nails', name: 'Gel Nail Extensions & Art', price: 1200, duration: 60, isPopular: true, desc: 'Long-lasting chip-free gel extensions with custom nail art.' },
  { id: 'n2', category: 'nails', name: 'Deluxe Spa Manicure & Pedicure', price: 750, duration: 60, isPopular: false, desc: 'Relaxing foot soak, scrub, nail shaping & gel polish.' },

  // THREADING & WAXING
  { id: 'w1', category: 'waxing', name: 'Full Body Rica Liposoluble Wax', price: 1400, duration: 75, isPopular: true, desc: 'Painless Italian Rica wax suitable for sensitive skin.' },
  { id: 'w2', category: 'waxing', name: 'Eyebrow & Upperlip Threading', price: 80, duration: 15, isPopular: false, desc: 'Precise eyebrow shaping with gentle cotton thread.' }
];

const ADD_ONS_DATA = [
  { id: 'add1', name: 'Gold Collagen Eye Mask', price: 150 },
  { id: 'add2', name: 'Argan Oil Hair Gloss Serum', price: 100 },
  { id: 'add3', name: 'Deep Back & Neck Massage', price: 250 },
  { id: 'add4', name: 'De-Tan Hand Polish', price: 200 }
];

const TESTIMONIALS_DATA = [
  {
    name: 'Anagha Joshi',
    locality: 'Zadgaon, Ratnagiri',
    rating: 5,
    quote: 'Sneha did my HD Bridal Makeup for my wedding. The makeup lasted over 14 hours without any touch-up and looked so natural in all photos!',
    service: 'HD Bridal Package'
  },
  {
    name: 'Pooja Sawant',
    locality: 'Nachane, Ratnagiri',
    rating: 5,
    quote: 'The Keratin treatment and O3+ facial at Radiance changed my hair texture completely. Extremely hygienic parlour with zero long waiting times.',
    service: 'Keratin & O3+ Facial'
  },
  {
    name: 'Rutuja Mane',
    locality: 'Kasarveli, Ratnagiri',
    rating: 5,
    quote: 'Loved the transparent pricing! I estimated my haircut and spa package on their website beforehand, and the bill was exactly what was shown.',
    service: 'Hair Spa & Cut'
  },
  {
    name: 'Priya Kulkarni',
    locality: 'Shivaji Chowk, Ratnagiri',
    rating: 5,
    quote: 'Sneha is so soft-spoken and professional. Using sealed original products gives me total peace of mind for my sensitive skin.',
    service: 'Gold Radiance Facial'
  }
];

/* GALLERY_DATA — 6 items. Each renders an <img src="images/gallery-<id>.jpg">
   Drop your Google Drawings export into /images/ using the exact filename shown below. */
const GALLERY_DATA = [
  { id: 'g1', title: 'HD Bridal Makeup Look', category: 'Bridal', desc: 'Flawless water-resistant HD bridal makeover' },
  { id: 'g2', title: 'Keratin Smooth Transformation', category: 'Hair', desc: 'Silky frizz-free hair transformation' },
  { id: 'g3', title: 'O3+ Facial Radiance', category: 'Skin', desc: 'Instant luminous bridal glow facial' },
  { id: 'g4', title: 'Designer Gel Nail Art', category: 'Nails', desc: 'Custom floral & glitter gel extensions' },
  { id: 'g5', title: 'Deluxe Party Glam Look', category: 'Bridal', desc: 'Dramatic party-ready eye makeup and hairstyling' },
  { id: 'g6', title: 'Rica Wax Smooth Finish', category: 'Skin', desc: 'Full body Rica wax result — smooth, even skin' }
];

/* State variables for Estimator */
let currentCategory = 'hair';
let currentServiceId = 'h1';
let currentPackageTier = 'deluxe';
let selectedAddonIds = [];

// DOM Loaded Event Listener
document.addEventListener('DOMContentLoaded', () => {
  initEstimator();
  renderServicesTab('hair');
  renderTestimonials();
  renderGallery();
  initStatsObserver();
  populateModalServiceDropdown();
  initMobileMenuAutoClose();
});

/**
 * ESTIMATOR INITIALIZATION & LOGIC
 */
function initEstimator() {
  const categoryPills = document.querySelectorAll('#estimatorCategoryPills .pill-btn');
  categoryPills.forEach(btn => {
    btn.addEventListener('click', (e) => {
      categoryPills.forEach(b => b.classList.remove('active'));
      e.target.classList.add('active');
      currentCategory = e.target.getAttribute('data-category');
      updateEstimatorServices();
    });
  });

  const serviceSelect = document.getElementById('estimatorServiceSelect');
  if (serviceSelect) {
    serviceSelect.addEventListener('change', (e) => {
      currentServiceId = e.target.value;
      calculateEstimatorTotal();
    });
  }

  const tierRadios = document.querySelectorAll('input[name="packageTier"]');
  tierRadios.forEach(radio => {
    radio.addEventListener('change', (e) => {
      currentPackageTier = e.target.value;
      calculateEstimatorTotal();
    });
  });

  renderAddonsList();
  updateEstimatorServices();
}

function updateEstimatorServices() {
  const serviceSelect = document.getElementById('estimatorServiceSelect');
  if (!serviceSelect) return;

  const categoryServices = SERVICES_DATA.filter(s => s.category === currentCategory);
  serviceSelect.innerHTML = categoryServices.map(s => `
    <option value="${s.id}">
      ${s.name} — Starts at ₹${s.price} (${s.duration} mins)
    </option>
  `).join('');

  if (categoryServices.length > 0) {
    currentServiceId = categoryServices[0].id;
  }
  calculateEstimatorTotal();
}

function renderAddonsList() {
  const addonsContainer = document.getElementById('estimatorAddonsList');
  if (!addonsContainer) return;

  addonsContainer.innerHTML = ADD_ONS_DATA.map(addon => `
    <label class="addon-label">
      <input type="checkbox" value="${addon.id}" onchange="toggleAddon('${addon.id}')">
      <span>${addon.name} (+₹${addon.price})</span>
    </label>
  `).join('');
}

function toggleAddon(addonId) {
  if (selectedAddonIds.includes(addonId)) {
    selectedAddonIds = selectedAddonIds.filter(id => id !== addonId);
  } else {
    selectedAddonIds.push(addonId);
  }
  calculateEstimatorTotal();
}

function calculateEstimatorTotal() {
  const service = SERVICES_DATA.find(s => s.id === currentServiceId) || SERVICES_DATA[0];
  let basePrice = service ? service.price : 500;

  let tierMultiplier = 1.0;
  if (currentPackageTier === 'deluxe') tierMultiplier = 1.25;
  if (currentPackageTier === 'premium') tierMultiplier = 1.5;

  let addonsTotal = 0;
  selectedAddonIds.forEach(id => {
    const addon = ADD_ONS_DATA.find(a => a.id === id);
    if (addon) addonsTotal += addon.price;
  });

  const estimatedBase = Math.round((basePrice * tierMultiplier) + addonsTotal);
  const estimatedMax = Math.round(estimatedBase * 1.15);

  const priceDisplay = document.getElementById('estimatorPriceDisplay');
  if (priceDisplay) {
    priceDisplay.textContent = `₹${estimatedBase.toLocaleString()} – ₹${estimatedMax.toLocaleString()}`;
  }
}

function bookCurrentEstimate() {
  const service = SERVICES_DATA.find(s => s.id === currentServiceId);
  const serviceName = service ? service.name : 'Beauty Service';
  const tierName = currentPackageTier.toUpperCase();

  openBookingModal(`${serviceName} (${tierName} Package)`);
}

/**
 * TABBED SERVICES RENDERER
 */
function renderServicesTab(category) {
  const grid = document.getElementById('servicesGrid');
  if (!grid) return;

  const filtered = SERVICES_DATA.filter(s => s.category === category);
  grid.innerHTML = filtered.map(service => `
    <div class="card service-card">
      <div class="service-card-header">
        <div>
          <span class="service-card-title">${service.name}</span>
          ${service.isPopular ? '<span class="popular-badge">Popular</span>' : ''}
        </div>
      </div>
      <p class="service-card-desc">${service.desc}</p>

      <div class="service-card-footer">
        <div class="service-price-tag">
          <span class="price-start-lbl">Starts at</span>
          <span class="price-val">₹${service.price}</span>
        </div>
        <div class="service-card-actions">
          <button type="button" class="btn btn-outline-dark" onclick="selectServiceForEstimator('${service.category}', '${service.id}')">Estimate</button>
          <button type="button" class="btn btn-primary" onclick="openBookingModal('${service.name}')">Book Now</button>
        </div>
      </div>
    </div>
  `).join('');

  // Update Active Tab Button
  const tabs = document.querySelectorAll('#servicesTabs .tab-btn');
  tabs.forEach(btn => {
    btn.classList.toggle('active', btn.getAttribute('data-tab') === category);
    btn.onclick = () => renderServicesTab(btn.getAttribute('data-tab'));
  });
}

function selectServiceForEstimator(category, serviceId) {
  currentCategory = category;
  currentServiceId = serviceId;

  // Activate Category Pill
  const categoryPills = document.querySelectorAll('#estimatorCategoryPills .pill-btn');
  categoryPills.forEach(b => {
    b.classList.toggle('active', b.getAttribute('data-category') === category);
  });

  updateEstimatorServices();
  const selectElem = document.getElementById('estimatorServiceSelect');
  if (selectElem) selectElem.value = serviceId;
  calculateEstimatorTotal();

  // Scroll to Estimator
  const estimatorElem = document.getElementById('estimator');
  if (estimatorElem) estimatorElem.scrollIntoView({ behavior: 'smooth' });
}

/**
 * TESTIMONIALS RENDERER
 */
function renderTestimonials() {
  const grid = document.getElementById('testimonialsGrid');
  if (!grid) return;

  grid.innerHTML = TESTIMONIALS_DATA.map(item => `
    <div class="card testimonial-card">
      <div class="testimonial-header">
        <div class="client-info">
          <div class="client-avatar">${item.name.charAt(0)}</div>
          <div>
            <div class="client-name">${item.name}</div>
            <div class="client-locality">${item.locality}</div>
          </div>
        </div>
        <div class="star-rating">${'★'.repeat(item.rating)}</div>
      </div>
      <p class="testimonial-quote">"${item.quote}"</p>
      <span class="service-used-tag">Service: ${item.service}</span>
    </div>
  `).join('');
}

/**
 * GALLERY RENDERER — renders real <img> tags.
 * Files must be placed at /images/gallery-<id>.jpg (e.g. images/gallery-g1.jpg)
 */
function renderGallery() {
  const grid = document.getElementById('galleryGrid');
  if (!grid) return;

  grid.innerHTML = GALLERY_DATA.map(item => `
    <div class="card gallery-card">
      <img
        src="images/gallery-${item.id}.jpg"
        alt="${item.title} — ${item.desc}"
        class="gallery-img"
        data-placeholder-id="gallery-${item.id}"
      >
    </div>
  `).join('');
}

/**
 * STATS COUNTER ANIMATION
 */
function initStatsObserver() {
  const statNumbers = document.querySelectorAll('.stat-number');
  if (statNumbers.length === 0) return;

  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        animateValue(entry.target);
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.5 });

  statNumbers.forEach(num => observer.observe(num));
}

function animateValue(elem) {
  const target = parseInt(elem.getAttribute('data-target'), 10);
  let start = 0;
  const duration = 1500;

  const timer = setInterval(() => {
    start += Math.max(1, Math.ceil(target / 40));
    if (start >= target) {
      elem.textContent = target.toLocaleString();
      clearInterval(timer);
    } else {
      elem.textContent = start.toLocaleString();
    }
  }, 30);
}

/**
 * MOBILE HAMBURGER MENU
 */
function toggleMobileMenu() {
  const nav = document.getElementById('navMenu');
  const btn = document.getElementById('hamburgerBtn');
  if (!nav || !btn) return;

  const isOpen = nav.classList.toggle('nav-open');
  btn.classList.toggle('active', isOpen);
  btn.setAttribute('aria-expanded', isOpen ? 'true' : 'false');
}

function initMobileMenuAutoClose() {
  const nav = document.getElementById('navMenu');
  if (!nav) return;
  nav.querySelectorAll('.nav-link').forEach(link => {
    link.addEventListener('click', () => {
      nav.classList.remove('nav-open');
      const btn = document.getElementById('hamburgerBtn');
      if (btn) {
        btn.classList.remove('active');
        btn.setAttribute('aria-expanded', 'false');
      }
    });
  });
}

/**
 * PREFERRED SERVICE DROPDOWN — populates the Book Appointment modal's
 * service select with every service grouped by category, plus a
 * General Consultation fallback. If a prefilled value (e.g. from the
 * estimator, which includes a package tier label) doesn't match any
 * existing option, it is added as a one-off option so the selection
 * is never lost.
 */
function populateModalServiceDropdown(selectedValue) {
  const select = document.getElementById('modalService');
  if (!select) return;

  let html = '<option value="">Select a service...</option>';

  Object.keys(CATEGORY_LABELS).forEach(cat => {
    const options = SERVICES_DATA.filter(s => s.category === cat).map(s =>
      `<option value="${s.name}">${s.name} — Starts at ₹${s.price}</option>`
    ).join('');
    html += `<optgroup label="${CATEGORY_LABELS[cat]}">${options}</optgroup>`;
  });

  html += '<option value="General Consultation">General Consultation / Not Sure Yet</option>';

  select.innerHTML = html;

  if (selectedValue) {
    const exists = Array.from(select.options).some(o => o.value === selectedValue);
    if (!exists) {
      const customOpt = document.createElement('option');
      customOpt.value = selectedValue;
      customOpt.textContent = selectedValue;
      select.appendChild(customOpt);
    }
    select.value = selectedValue;
  }
}

/**
 * BOOKING MODAL & WHATSAPP REDIRECT LOGIC
 */
function openBookingModal(prefilledService = '') {
  const modal = document.getElementById('bookingModal');

  populateModalServiceDropdown(prefilledService);

  if (modal) modal.classList.add('active');
}

function closeBookingModal() {
  const modal = document.getElementById('bookingModal');
  if (modal) modal.classList.remove('active');
}

function handleModalOverlayClick(e) {
  if (e.target.id === 'bookingModal') {
    closeBookingModal();
  }
}

function handleBookingSubmit(e) {
  e.preventDefault();

  const name = document.getElementById('modalFullName').value.trim();
  const phone = document.getElementById('modalPhone').value.trim();
  const service = document.getElementById('modalService').value.trim() || 'General Consultation';
  const date = document.getElementById('modalDate').value || 'Earliest Available';
  const time = document.getElementById('modalTime').value;
  const notes = document.getElementById('modalNotes').value.trim();

  // Validate Required Fields
  if (!name) {
    document.getElementById('nameError').style.display = 'block';
    return;
  }
  if (!phone || phone.length < 10) {
    document.getElementById('phoneError').style.display = 'block';
    return;
  }

  // Hide errors
  document.getElementById('nameError').style.display = 'none';
  document.getElementById('phoneError').style.display = 'none';

  // Format WhatsApp Message
  const message = `Hello ${BUSINESS_CONFIG.businessName},\n` +
    `I would like to book an appointment:\n\n` +
    `👤 Name: ${name}\n` +
    `📞 Phone: ${phone}\n` +
    `💅 Service: ${service}\n` +
    `📅 Preferred Date: ${date}\n` +
    `⏰ Time Slot: ${time}\n` +
    (notes ? `📝 Notes: ${notes}\n` : '') +
    `\nPlease confirm slot availability!`;

  const statusMsg = document.getElementById('modalRedirectStatus');
  if (statusMsg) statusMsg.style.display = 'block';

  // Redirect to WhatsApp via BUSINESS_CONFIG.whatsapp (Rule 1)
  setTimeout(() => {
    const waUrl = `https://wa.me/${BUSINESS_CONFIG.whatsapp}?text=${encodeURIComponent(message)}`;
    window.open(waUrl, '_blank');
    closeBookingModal();
    if (statusMsg) statusMsg.style.display = 'none';
  }, 800);
}

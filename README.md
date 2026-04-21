# MSD Hotel Reservation System


## 🎯 The Mission: System Integrity Over Visual Polish
A Hotel Booking system is a high-stakes ecosystem. Following the **MSD blueprint**, this project prioritizes **Offline-First reliability**, **Race-Condition handling**, and **Data Consistency**. It is built to thrive under complexity rather than just looking good on the surface.

---

## 🛠️ Phase 1: Intelligent Auto-Complete Engine
The first milestone focuses on a high-performance, resilient search engine designed for speed and resource efficiency.

### 🧠 "Under the Hood" Breakdown

* **MVI (Model-View-Intent) & UDF**: Managed via a single `StateFlow`. This ensures that Query, Suggestions, and Loading states are always synchronized, eliminating UI glitches.
* **Offline-First Mastery (Room)**: Implemented the **Single Source of Truth** principle. Instant results are served from local storage, ensuring a seamless experience regardless of connectivity.
* **Background Prefetching (WorkManager)**: A `PrefetchWorker` intelligently syncs trending queries in the background. 
    * *Constraints:* Triggered only on **Wi-Fi** and while **Charging** to preserve battery and data.
* **Reactive Stream Power**: Leveraging Kotlin Flows for high-frequency input handling:
    * `debounce(300ms)`: Optimizes battery and server resources.
    * `distinctUntilChanged()`: Prevents redundant processing.
    * `flatMapLatest`: Automatically cancels stale network requests when new input is detected.
* **Dependency Injection (Hilt)**: Modular architecture built for testability and scale from day one.

---

##  Technical Stack & Architecture

- **UI**: Jetpack Compose (Declarative UI)
- **Asynchrony**: Kotlin Coroutines & Flows
- **Local DB**: Room (Single Source of Truth)
- **Background Tasks**: WorkManager (Custom Hilt-Worker Configuration)
- **DI**: Hilt (Standard MSD Modularization)
- **Network**: Retrofit (with custom Flow-based BaseUseCase handling)



---

## ⏳ Roadmap: What's Next?
This is just the gateway. Future development chapters include:
- [ ] **Concurrency Control**: Robust logic to prevent double-bookings.
- [ ] **Multi-step Checkout**: Complex state management for reservation flows.

---

Developed with a focus on **Resiliency**, **Scalability**, and **Maintainability**.

---

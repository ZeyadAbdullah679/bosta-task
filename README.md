# 📱 Bosta Android Task - City & District List 🗺️  
**A sleek Android app to view cities and districts using Bosta's API!**  

---

## 🚀 Features  
- **🌆 City & District Listing**: Fetch and display all cities/districts from the Bosta API.  
- **🔍 Modern Architecture**: Built with **MVVM**, **Kotlin Coroutines**, and **Retrofit 2**.  
- **📊 RecyclerView**: Smooth scrolling with dynamic data loading.  
- **🧩 Dependency Injection**: Clean code using **Dagger 2**.  

---

## 🛠️ Technologies & Tools  
| Category       | Tools/Libraries                                                                 |  
|----------------|---------------------------------------------------------------------------------|  
| **Language**   | Kotlin 🅚                                                                        |  
| **Architecture**| MVVM                                                                            |  
| **Networking** | Retrofit 2 🌐                                                                   |  
| **DI**         | Dagger 2 🧬                                                                     |  
| **Async**      | Coroutines ⚡                                                                   |  
| **UI**         | RecyclerView, Jetpack Components 🎨                                            |  

---

## 📥 Installation  
1. Clone the repository:  
   ```bash  
   git clone https://github.com/ZeyadAbdullah679/bosta-task
   ```  
2. Open the project in Android Studio.  
3. Build and run!  

---

## 🎥 Video Demonstration  
📹 **Watch the app in action!**  
👉 [Click here to view the video](record.mp4) 👈   

---

## 🔌 API Endpoints  
- **Base URL**: `https://stg-app.bosta.co/api/v2`  
- **Cities & Districts**:  
  ```  
  GET /cities/getAllDistricts?countryId=60e4482c7cb7d4bc4849c4d5  
  ```

---

## 🎨 Design Inspiration  
Inspired by Bosta’s **"Choose Delivery Area"** concept:  
- Clean, minimal interface.  
- Hierarchical city/district grouping (e.g., Cairo → Dokki, Zamalek).  

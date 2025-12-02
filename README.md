<div align="center">

# 🎯 **TARGET LOCK** 🎯

<p>
    <img src="https://img.shields.io/badge/version-v1.0-green" alt="Version">
    <img src="https://img.shields.io/badge/project-OOP-blue" alt="License">
    <img src="https://img.shields.io/badge/SDG-3_Good_Health-blueviolet" alt="SDG 3">
  </p>

</div>

<br />

# 🎯 TrialTargetLock
**TargetLock** is a **console-based system** that helps users make smarter food choices by aligning what they eat with their personal health goals whether **💪 gaining muscle, 📉 losing weight, 🧘 reducing stress, ✨ improving skin, 😴 enhancing sleep**, and etc.

---

## **📝 DESCRIPTION/OVERVIEW** 🍏

TargetLock is designed to transform the process of healthy eating into a guided, data-driven experience. The system's core function is to connect a user's **🎯 target goal** directly with the nutritional impact of their food choices.

### **How it Works:**
1.  **Goal Selection:** The user first selects a specific health objective (**💪 Muscle Gain, 📉 Weight Loss**, etc.).
2.  **Food Logging:** After choosing a food item (either from a pre-defined list or using the **Custom Food** option), TargetLock analyzes its **🧪 nutritional value, 🛡️ safety level**, and overall health impact.
3.  **Personalized Feedback:** The system then provides clear, personalized recommendations, informing the user whether the food **✅ supports or ❌ goes against** their selected goal.
4.  **Motivation:** TargetLock includes a **🔥 streak tracker** that records each logged food, providing **📢 encouraging messages** to motivate consistency and support long-term healthy habits, aligning with **🌏 SDG 3 – Good Health and Well-Being**.

---

## **💻 OOP CONCEPTS APPLIED** 💡

The application of **Object-Oriented Programming (OOP)** principles ensures a robust, maintainable, and scalable system structure.

| OOP Concept | Icon | Description & Application in TargetLock |
| :--- | :--- | :--- |
| **Encapsulation** | 🛡️ | Achieved by bundling data (food name, nutritional values, health goal) and the methods that operate on that data within classes like `FoodItem` and `UserProfile`. **Private** attributes protect internal data, ensuring it is only modified via controlled, **public** methods (getters/setters). |
| **Inheritance** | 👨🏻‍👩🏻‍👧🏻‍👦🏻 | Used to model specialized behaviors from a general type. A base class, **`HealthGoal`**, is extended by subclasses like **`WeightLossGoal`** or **`MuscleGainGoal`**. These subclasses share common properties but implement specific, distinct goal-checking logic. |
| **Abstraction** | 🪄 | Focuses on showing essential features while hiding complex implementation details. The **`Analyzer`** class exposes a simple **`getRecommendation()`** method, abstracting away the complex nutritional calculations and safety checks performed internally. |
| **Polymorphism** | 🎭 | Allows a single interface to be used for different implementations. The core **`Analyzer`** interacts with all goal types through a single **`HealthGoal`** reference. When the **`analyze(food)`** method is called, the correct, goal-specific logic is executed. |

---

## **⚙️ PROGRAM STRUCTURE** 🧩

The system is designed with several key classes to manage functionality and data flow.

* **`TargetLockApp` (Main Class):** **▶️ Orchestrates** the main menu, user interaction, and overall program execution.
* **`UserProfile`:** **👤 Stores** the user's chosen goal and manages the **🔥 streak tracker**.
* **`HealthGoal` (Abstract Base Class):** **📝 Defines** the common interface for all health objectives.
    * *Subclasses:* `MuscleGainGoal`, `WeightLossGoal`, etc.
* **`FoodItem`:** **🍎 Represents** a piece of food with all nutritional attributes. Includes the **Custom Food** option.
* **`Analyzer`:** **🧠 Contains** the core logic for processing a food item against the user's goal, calculating the health impact, and generating **✅ personalized recommendations**.
* **`StreakTracker`:** **📈 Manages** the streak count and provides motivational messages for consistency.

---

## **▶️ HOW TO RUN THE PROGRAM** 🚀

This is a console-based application requiring a Java environment.

1.  **Select Goal:** First, the user selects their **🎯 desired health goal**.
2.  **Select Food:** After choosing a goal, the system displays food categories, and the user selects a specific item.
3.  **Custom Food Option:** If the food is not listed, they can use the **⌨️ Custom Food** option, manually entering the food name and its nutritional values.
4.  **Analysis and Recommendations:** Once submitted, the system **🔬 analyzes** the input and informs the user about the food’s overall healthiness, its safety level, and personalized recommendations on whether it **✅ supports or ❌ goes against** their selected goal. It also suggests **🥕 healthier options**.
5.  **Motivation:** The **🔥 streak tracker** is included to motivate consistency, encouraging users to regularly check their food choices and maintain a long-term healthy lifestyle.

---

## **🖥️ SAMPLE OUTPUT** 📊

*** TARGET LOCK: Personalized Health Tracker ***


---

## **🧑🏻‍🤝‍🧑🏻AUTHOR AND ACKNOWLEDGEMENT** 💖

### **🙏 Gratitude to Our Instructor**

We thank our CS 211 Instructor, Ms. **Christiana Grace Alib**, for her invaluable mentorship and guidance in applying Object-Oriented Programming principles, which was instrumental in the successful development of **TargetLock**.

---

### **✨ Project Made By: Looter's Members** 👥

This table uses the **raw image links** and the **Facebook** badge for professional formatting. The names are not links (to prevent the blue color), ensuring a clean, dark-theme appearance.

### ✨ Project Made By: Looter's Members 👥

This table uses the **raw image links** and the **Facebook** badge for professional formatting. The names are not links (to prevent the blue color), ensuring a clean, dark-theme appearance.

<table>
  <tr>
    <td align="center">
            <a href="[Person 1 Facebook URL]">
        <img src="https://github.com/Seyoung20/TrialTargetLock/raw/main/img/p3.png" width="100px;" alt="Alea, Mariane"/>
      </a>
      <br />
      <span style="color: #ffffff; text-decoration: none;">
        <sub><b>Alea, Mariane</b></sub>
      </span>
      <br />
      <a href="[Person 1 Facebook URL]">
        <img src="https://img.shields.io/badge/Facebook-1877F2?style=flat&logo=facebook&logoColor=white" />
      </a>
      <br />
      <a href="[Person 1 Instagram URL]">
        <img src="https://img.shields.io/badge/Instagram-E4405F?style=flat&logo=instagram&logoColor=white" />
      </a>
    </td>
    <td align="center">
      <a href="https://www.facebook.com/justinjake.baliwag">
        <img src="https://github.com/Seyoung20/TrialTargetLock/raw/main/img/p1.png" width="100px;" alt="Baliwag, Justin Jake"/>
      </a>
      <br />
      <span style="color: #ffffff; text-decoration: none;">
        <sub><b>Baliwag, Justin Jake</b></sub>
      </span>
      <br />
      <a href="https://www.facebook.com/justinjake.baliwag">
        <img src="https://img.shields.io/badge/Facebook-1877F2?style=flat&logo=facebook&logoColor=white" />
      </a>
      <br />
      <a href="https://www.instagram.com/4sdfgh.jj/">
        <img src="https://img.shields.io/badge/Instagram-E4405F?style=flat&logo=instagram&logoColor=white" />
      </a>
    </td>
    <td align="center">
      <a href="[Person 3 Facebook URL]">
        <img src="https://github.com/Seyoung20/TrialTargetLock/raw/main/img/p4.png" width="100px;" alt="Ibea, Daniel"/>
      </a>
      <br />
      <span style="color: #ffffff; text-decoration: none;">
        <sub><b>Ibea, Daniel</b></sub>
      </span>
      <br />
      <a href="[Person 3 Facebook URL]">
        <img src="https://img.shields.io/badge/Facebook-1877F2?style=flat&logo=facebook&logoColor=white" />
      </a>
      <br />
      <a href="[Person 3 Instagram URL]">
        <img src="https://img.shields.io/badge/Instagram-E4405F?style=flat&logo=instagram&logoColor=white" />
      </a>
    </td>
    <td align="center">
      <a href="[Person 4 Facebook URL]">
        <img src="https://github.com/Seyoung20/TrialTargetLock/raw/main/img/p2.png" width="100px;" alt="Pabito, Sam Angelo"/>
      </a>
      <br />
      <span style="color: #ffffff; text-decoration: none;">
        <sub><b>Pabito, Sam Angelo</b></sub>
      </span>
      <br />
      <a href="[Person 4 Facebook URL]">
        <img src="https://img.shields.io/badge/Facebook-1877F2?style=flat&logo=facebook&logoColor=white" />
      </a>
      <br />
      <a href="[Person 4 Instagram URL]">
        <img src="https://img.shields.io/badge/Instagram-E4405F?style=flat&logo=instagram&logoColor=white" />
      </a>
    </td>
  </tr>
</table>

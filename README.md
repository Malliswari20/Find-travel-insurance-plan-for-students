
# Find Travel Insurance Plan for Students — Test Automation Suite

Automated UI tests for key insurance user journeys (Policybazaar-style flows):
1) **Travel insurance for students** — find the 3 lowest international plans for 2 students (ages 22 & 21) for a European destination.
2) **Car insurance quote** — proceed without car number, enter invalid email/phone, capture validation error.
3) **Health insurance** — scrape all menu items and display/store them.

---

## 🧱 Tech Stack
- **Language:** Java 8+/11+
- **Automation:** Selenium WebDriver
- **Design Pattern:** Page Object Model (POM) with PageFactory
- **Test Runner:** TestNG
- **Build Tool:** Maven
- **Reporting:** Allure (optional)
- **Logging:** Log4j / SLF4J
- **CI/CD:** GitHub Actions / Jenkins

---

## 📂 Project Structure
```
project-root/
│
├── src/
│   ├── main/java/pages/          # Page Object Classes
│   └── test/java/tests/          # All Test Classes
│   └── test/java/utils/          # WebDriver, Config, Wait Utilities
│
├── testng.xml                    # Test Suite File
├── pom.xml                       # Dependencies & Build Configuration
└── README.md
```

---

## 🚦 Test Scenarios
### **1️⃣ Student Travel Insurance**
- Select any European country.
- Choose valid start and end travel dates.
- Add 2 student travellers (Age 22 & 21).
- Select *no pre-existing medical conditions*.
- Extract all plans → Sort by price → Display **3 lowest**.

### **2️⃣ Car Insurance (Negative Test)**
- Proceed without entering car number.
- Enter invalid email/phone.
- Capture and validate the warning message.

### **3️⃣ Health Insurance Menu**
- Navigate to Health Insurance menu.
- Scrape all submenu items and display/store them.
---
## 📊 Reporting
- TestNG HTML Reports
- Allure Reports (step logs, screenshots, failure traces)

---

## 🧠 Highlights
- Clean POM architecture
- Explicit Waits
- Data-driven support
- Sorting logic for cheapest plans
- Validation for negative scenarios
- Menu scraping using Selenium locators

---

## 🛠 Future Enhancements
- Add Cucumber BDD
- Integrate Jenkins Pipeline
- Add Selenium Grid for parallel execution
- Add screenshots on failure

---

## 📜 License
Open for educational and testing purposes.

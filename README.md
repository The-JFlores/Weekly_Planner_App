# Weekly Planner App

## Description
Weekly Planner App is a simple Java utility that helps users organize their weekly tasks.  
You can add between 3 and 7 tasks, assign priorities (High, Medium, Low), and the app automatically generates a **Markdown (.md)** file with your tasks and relevant emojis based on keywords in the task description.

## Features
- Add 3–7 tasks per week.  
- Assign task priorities: High, Medium, Low.  
- Automatically adds emojis depending on the task:
  - 💧 Wash  
  - 🍽️ Eat / Cook  
  - 🐶 Dog  
  - 🏃 Run / Gym / Exercise  
  - 📚 Study / Read / Java  
  - 🚗 Car  
  - 🛒 Shop / Buy  
  - 🧹 Clean  
  - 💦 Water  
  - 📞 Call  
  - 🛌 Sleep / Nap  
  - 🎵 Music / Listen  
- Generates a clean, readable **Markdown file** (`WeeklyPlanner.md`) with tasks organized by day and sorted by priority (High → Medium → Low).  

## Installation

### 1️⃣ Compile
```bash
javac WeeklyPlanner.java

### 2️⃣ Run from terminal 
java WeeklyPlanner

3️⃣ Optional: Create a Mac .app to run by double-click

# Create an executable jar
jar cfe WeeklyPlanner.jar WeeklyPlanner *.class

# Then use Automator on Mac to create an application that runs this jar

Example Usage

Welcome to Weekly Planner!
How many tasks do you want to add? (3-7): 3

Task 1: Wash the car
Priority (High, Medium, Low): High

Task 2: Study Java
Priority (High, Medium, Low): Medium

Task 3: Go to the gym
Priority (High, Medium, Low): Low

Generated Markdown (WeeklyPlanner.md):

# Weekly Planner

## Monday
- 💧 Wash the car _(Priority: High)_
- 📚 Study Java _(Priority: Medium)_
- 🏃 Go to the gym _(Priority: Low)_

Screenshots

(Add images of your terminal showing the app running here)

Author Jose Flores— 2025
 

License

This project is open-source and free to use.
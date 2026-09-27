# NTSS Booth Floor Plan System

A Java Swing application for creating and managing an interactive NTSS exhibition booth floor plan.

The system allows users to place booths on a visual floor plan, select different booth shapes and sizes, customize booth colors, change the floor-plan background, select existing booths, and save the completed floor plan.

## GitHub Repository

https://github.com/Juldiaz1/NTSS-Booth-Floor-Plan

## Features

- Interactive floor-plan canvas
- Circle, square, and rectangle booths
- Small, medium, and large booth sizes
- Custom booth colors
- Preset booth colors
- Custom background colors
- Light, Blueprint, Night, and Warm backgrounds
- Select existing booths
- Recolor existing booths
- Grid-based floor plan
- Booth placement validation
- Booth coordinate information
- Save floor plan to a text file
- Demonstration booths shown when the application starts

## Design Patterns

The project demonstrates the following design patterns:

- Controller
- Creator
- Expert
- Composite
- Iterator
- Flyweight

### Controller

`FloorPlanController` manages booth placement, validation, and saving.

### Creator

`FloorPlan` creates and manages `Booth` objects.

### Expert

`FloorPlan` manages information and operations related to the booth collection.

### Composite

`BoothComponent`, `Booth`, and `BoothGroup` allow individual booths and booth groups to use a common structure.

### Iterator

`BoothIterator` and `FloorPlanIterator` provide traversal of the booth collection.

### Flyweight

`BoothType` and `BoothTypeFactory` allow shared booth type information to be reused.

Individual booths can still have their own positions and custom colors.

## Main Files

```text
Booth.java
BoothComponent.java
BoothGroup.java
BoothIterator.java
BoothType.java
BoothTypeFactory.java
FloorPlan.java
FloorPlanCanvas.java
FloorPlanController.java
FloorPlanIterator.java
Main.java
NTSSStaffUI.java
```

# Requirements

The project requires the Java Development Kit (JDK).

## Check Java Installation

Open PowerShell or a terminal and run:

```powershell
java -version
javac -version
```

Both commands should return an installed Java version.

The project also requires Git if you want to clone the repository from GitHub.

Check Git:

```powershell
git --version
```

# How to Get and Run the Project

There are two ways to access and compile this project.

# Option 1: Clone the GitHub Repository

This option downloads the project to your computer.

## Step 1: Clone the Repository

Open PowerShell:

```powershell
git clone https://github.com/Juldiaz1/NTSS-Booth-Floor-Plan.git
```

## Step 2: Enter the Project Folder

```powershell
cd NTSS-Booth-Floor-Plan
```

## Step 3: Check Java

```powershell
java -version
javac -version
```

## Step 4: Compile the Java Files

```powershell
javac *.java
```

## Step 5: Run the Application

```powershell
java Main
```

## Recommended Clone Build

The project can also be compiled into a separate `out` directory:

```powershell
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue

New-Item -ItemType Directory -Path out -Force | Out-Null

javac -d out (Get-ChildItem -Filter *.java |
    ForEach-Object {
        $_.FullName
    })
```

Then run:

```powershell
java -cp out Main
```

# Option 2: GitHub Codespaces

This option allows the project to be opened directly in a cloud development environment.

## Step 1: Open the Repository

Go to:

https://github.com/Juldiaz1/NTSS-Booth-Floor-Plan

## Step 2: Create a Codespace

Select:

```text
Code
→ Codespaces
→ Create codespace on main
```

## Step 3: Check Java

After the Codespace opens, open the terminal and run:

```bash
java -version
javac -version
```

## Step 4: Compile the Project

```bash
javac *.java
```

## Step 5: Run the Application

```bash
java Main
```

## Recommended Codespace Build

```bash
rm -rf out
mkdir out
javac -d out *.java
```

Then:

```bash
java -cp out Main
```

# Important

Both options use the same Java source code.

## Clone Method

```text
GitHub Repository
       |
     Clone
       |
  Project Folder
       |
   javac *.java
       |
    java Main
```

## Codespaces Method

```text
GitHub Repository
       |
    Codespaces
       |
 Create Codespace
       |
   javac *.java
       |
    java Main
```

# How to Use the Application

1. Select a booth shape.
2. Select a booth size.
3. Select a booth color.
4. Click `Place Booth`.
5. Click somewhere on the floor-plan canvas.
6. The booth is placed at that location.
7. Click an existing booth to select it.
8. Use the color controls to recolor the selected booth.
9. Use the background controls to change the floor-plan background.
10. Click `Save Floor Plan` to save the current design.

# Booth Shapes

```text
Circle
Square
Rectangle
```

# Booth Sizes

```text
Small
Medium
Large
```

# Booth Colors

Preset colors include:

```text
Blue
Green
Orange
Red
Purple
Teal
Gold
Pink
```

A custom booth color can also be selected using the Java color chooser.

# Background Themes

Preset backgrounds include:

```text
Light
Blueprint
Night
Warm
Custom
```

A custom background color can also be selected.

# Selecting and Editing Booths

Click an existing booth on the floor plan to select it.

The selected booth is highlighted.

After selecting a booth, its color can be changed without creating a new booth.

# Placement Validation

The controller checks whether a booth fits inside the floor-plan boundaries before adding it.

If the booth does not fit, the application displays a placement error instead of adding the booth.

# Saving a Floor Plan

The `Save Floor Plan` button exports the current floor plan to a text file.

Saved information includes:

```text
Date Created
Floor Width
Floor Height
Booth Count
Booth Number
Shape
Size
Price
X Position
Y Position
Color
Image
```

# Application Entry Point

The main entry point is:

```text
Main.java
```

`Main.java` creates the controller, creates demonstration booths, and launches the Java Swing interface.

# Project Structure

```text
NTSS-Booth-Floor-Plan/
|
|-- Booth.java
|-- BoothComponent.java
|-- BoothGroup.java
|-- BoothIterator.java
|-- BoothType.java
|-- BoothTypeFactory.java
|-- FloorPlan.java
|-- FloorPlanCanvas.java
|-- FloorPlanController.java
|-- FloorPlanIterator.java
|-- Main.java
|-- NTSSStaffUI.java
|
|-- README.md
|-- NTSS_BoothFloorPlan_Report.md
|-- NTSS_BoothFloorPlan_Report.pdf
```

# Future Enhancements

- Booth deletion
- Booth movement
- Undo and redo
- Booth search
- Booth filtering
- Booth reservation status
- Booth names and identifiers
- Collision detection
- Loading previously saved floor plans
- Database storage
- Additional booth shapes

# Author

Julissa Diaz

Software Engineering

University of Texas at Arlington

# Repository

https://github.com/Juldiaz1/NTSS-Booth-Floor-Plan

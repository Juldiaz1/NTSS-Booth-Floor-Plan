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

This project demonstrates the following design patterns:

- Controller
- Creator
- Expert
- Composite
- Iterator
- Flyweight

### Controller

`FloorPlanController` manages application operations such as booth placement, validation, and saving.

### Creator

`FloorPlan` creates and manages `Booth` objects.

### Expert

`FloorPlan` is responsible for operations involving the booth collection and floor plan.

### Composite

`BoothComponent`, `Booth`, and `BoothGroup` allow individual booths and booth collections to be handled through a common structure.

### Iterator

`BoothIterator` and `FloorPlanIterator` provide a way to traverse the booth collection.

### Flyweight

`BoothType` and `BoothTypeFactory` allow shared booth type information to be reused.

Individual booths can still have their own locations and custom colors.

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
Requirements

The project requires the Java Development Kit (JDK).

Check Java:

java -version
javac -version

Both commands should return a Java version.

Running the Project

The project can be run directly from Windows PowerShell or through GitHub Codespaces.

Option 1: Windows PowerShell

Open PowerShell and enter the project folder:

cd "C:\Users\julis\NTSS-Booth-Floor-Plan"

Compile the project:

javac *.java

Run the application:

java Main
Recommended PowerShell Build

A cleaner build uses a separate out directory:

Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue

New-Item -ItemType Directory -Path out -Force | Out-Null

javac -d out (Get-ChildItem -Filter *.java |
    ForEach-Object {
        $_.FullName
    })

Run the compiled application:

java -cp out Main
Option 2: GitHub Codespaces

Open:

https://github.com/Juldiaz1/NTSS-Booth-Floor-Plan

Select:

Code
→ Codespaces
→ Create codespace on main

After the Codespace opens:

javac *.java

Then:

java Main

Or use:

rm -rf out
mkdir out
javac -d out *.java
java -cp out Main
How to Use the Application
Select a booth shape.
Select a booth size.
Select a booth color.
Click Place Booth.
Click somewhere on the floor-plan canvas.
The booth is placed at that location.
Click an existing booth to select it.
Use the color controls to recolor the selected booth.
Use the background controls to change the floor-plan background.
Click Save Floor Plan to save the current design.
Booth Shapes

The application supports:

Circle
Square
Rectangle
Booth Sizes

Each shape supports:

Small
Medium
Large
Booth Colors

Preset colors include:

Blue
Green
Orange
Red
Purple
Teal
Gold
Pink

A custom booth color can also be selected using the Java color chooser.

Background Themes

Preset backgrounds include:

Light
Blueprint
Night
Warm
Custom

A custom background color can also be selected using the color chooser.

Selecting and Editing Booths

Click an existing booth on the floor plan to select it.

The selected booth is highlighted.

After selecting a booth, its color can be changed without creating a new booth.

Placement Validation

The controller checks whether a booth fits inside the floor-plan boundaries before adding it.

If the booth does not fit, the application displays a placement error instead of adding the booth.

Saving a Floor Plan

The Save Floor Plan button allows the current floor plan to be exported to a text file.

The saved file contains information such as:

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
Application Entry Point

The main entry point is:

Main.java

Main.java creates the controller, creates demonstration booths, and launches the Java Swing user interface.

Project Structure
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
Future Enhancements

Possible future improvements include:

Booth deletion
Booth movement
Undo and redo
Booth search
Booth filtering
Booth reservation status
Booth names and identifiers
Collision detection
Loading previously saved floor plans
Database storage
Additional booth shapes
Author

Julissa Diaz

Software Engineering
University of Texas at Arlington

Repository

https://github.com/Juldiaz1/NTSS-Booth-Floor-Plan

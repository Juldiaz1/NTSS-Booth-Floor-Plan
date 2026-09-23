# NTSS Booth Floor Plan System

## Overview
This project implements a Java Swing booth floor plan system for NTSS staff. Staff can choose a booth shape and size, place booths on the floor plan, remove a selected booth, and save the current floor plan to a text file.

## Project Structure

- `Main.java` - starts the application.
- `FloorPlanController.java` - handles UI requests and floor plan operations.
- `FloorPlan.java` - stores the floor plan and creates booths.
- `BoothComponent.java` - common component for booths and groups.
- `Booth.java` - individual booth object.
- `BoothGroup.java` - composite collection of booth components.
- `BoothIterator.java` - iterates through booth components.
- `FloorPlanIterator.java` - iterator exposed by `FloorPlan`.
- `BoothType.java` - shared booth type data.
- `BoothTypeFactory.java` - reuses booth type objects.
- `NTSSStaffUI.java` - main Swing interface.
- `FloorPlanCanvas.java` - drawing and mouse interaction.

## Design Patterns

### Controller
`FloorPlanController` handles requests coming from the user interface.

### Creator / Expert
`FloorPlan` creates booths and manages the booth collection.

### Composite
`BoothComponent`, `Booth`, and `BoothGroup` allow individual booths and groups to be handled through a common type.

### Iterator
`BoothIterator` and `FloorPlanIterator` provide sequential access to booths without exposing the collection logic to the UI.

### Flyweight
`BoothType` contains shared booth data, while `BoothTypeFactory` reuses the same booth type for matching shape and size combinations.

## Requirements

- Java JDK 8 or newer
- No external libraries are required.

## Compile

Open a terminal in this folder:

```text
javac *.java
```

## Run

```text
java Main
```

## Basic Use

1. Select a booth shape.
2. Select a booth size.
3. Click `Select Booth`, or use one of the palette buttons.
4. Click an empty location on the floor plan.
5. Use the remove option if a booth needs to be deleted.
6. Click `Save` to export the floor plan.

## GitHub

The `.gitignore` file excludes compiled Java `.class` files and common IDE/build files.

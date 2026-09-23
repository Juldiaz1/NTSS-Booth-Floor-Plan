# NTSS Booth Floor Plan System
## Project Report

**Student:** Julissa Diaz  
**Project:** NTSS Booth Floor Plan System  
**Language:** Java  
**Interface:** Java Swing

## 1. Introduction

The NTSS Booth Floor Plan System is a desktop application designed to help NTSS staff place and manage booths on a floor plan. The application provides a graphical interface where staff can select a booth shape and size, place the booth on the floor plan, remove booths, and save the floor plan.

The implementation uses several object-oriented design patterns to separate responsibilities and keep the booth objects reusable.

## 2. System Design

The application is divided into model, controller, and user-interface responsibilities.

`FloorPlan` represents the floor plan and owns the collection of booths. `Booth` represents one booth. `BoothGroup` stores booth components as a composite structure. `FloorPlanController` handles operations requested by the interface. `NTSSStaffUI` and `FloorPlanCanvas` provide the graphical interface.

## 3. Design Patterns

### 3.1 Controller

`FloorPlanController` acts as the controller between the user interface and the floor plan model. It provides operations for placing and removing booths and for accessing the current floor plan.

### 3.2 Creator

`FloorPlan` creates `Booth` objects through its `createBooth` method. This keeps booth creation in the class that manages the floor plan.

### 3.3 Expert

`FloorPlan` has the information needed to manage the booths on the floor plan, so it performs responsibilities such as adding booths and reporting the booth count.

### 3.4 Composite

`BoothComponent` is the common component type. `Booth` is a leaf and `BoothGroup` is a composite. This allows the floor plan to treat an individual booth and a group of components through the same abstraction.

### 3.5 Iterator

`BoothIterator` walks through booth components without requiring callers to work directly with the internal collection. `FloorPlanIterator` exposes that behavior through the `FloorPlan` class.

### 3.6 Flyweight

`BoothType` stores shared booth information such as shape and size. `BoothTypeFactory` reuses an existing `BoothType` when another booth has the same shape and size. This avoids creating duplicate shared type data.

## 4. User Interface

The Swing interface contains shape and size selections, palette buttons, a floor plan canvas, status information, and save/remove controls.

The canvas responds to mouse clicks. When placement mode is active, the selected booth is placed at the clicked location if it fits inside the floor plan and does not overlap an existing booth.

## 5. Booth Placement

Before a booth is added, the controller/canvas logic checks the selected booth dimensions against the floor plan boundaries. Existing booth positions are also checked so that a new booth does not overlap a booth already on the floor plan.

The booth shape affects how the booth is drawn. Size affects the booth dimensions.

## 6. Saving

The Save option uses a file chooser and writes the current floor plan information to a text file. The saved information includes the floor plan dimensions and the booths currently placed on it.

## 7. Testing

The implementation was checked for the following basic cases:

| Test | Expected Result |
|---|---|
| Start application | Main NTSS window opens |
| Select booth shape and size | Selected values are displayed |
| Place booth inside floor plan | Booth is added and drawn |
| Place booth outside boundary | Booth is rejected |
| Place overlapping booth | Booth is rejected |
| Remove booth | Selected booth is removed |
| Save empty floor plan | Save is prevented with a message |
| Save populated floor plan | Floor plan is written to a text file |
| Create repeated booth types | Shared booth type is reused |
| Iterate through booths | All placed booths are returned |
| Use booth group | Group can contain booth components |

## 8. Files

The project includes all Java source files, a README, a Git ignore file, and this report.

## 9. Conclusion

The completed system provides the requested booth floor plan functionality while separating responsibilities between the model, controller, iterator, composite, flyweight, and Swing interface classes. The design also makes it possible to add more booth types or extend the floor plan without placing all of the application logic in one class.

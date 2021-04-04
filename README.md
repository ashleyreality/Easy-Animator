# Easy-Animator
// Still a draft but feel free to add or change things!

The Easy Animator model accepts IShape objects and IEvent objects. You can use these objects to 
create a simple animation model.

### AnimationModel
The AnimationModel interface describes methods you must use if you implement AnimationModel. 
AnimationModel objects are designed to represent an animation. 

#### AnimationModelImpl
Use AnimationModelImpl to create an easy animation model. Animations consist of two types of 
objects: IShapes, and IEvents that happen to those shapes. 
###### Useful methods
* `addShape(IShape shape, int appear, int disappear)` - add an IShape to your animation
* `addEvent(IShape shape, IEvent event, int appear, int disappear)` - add an IEvent to your IShape
* `getEventList(IShape shape)` - returns a list of all events associated with a shape
* `getShapesAtTick(int tick)` - get all shapes and their states at a given tick 

### IShape 
The IShape interface describes methods you must use if you implement IShape. IShape objects are 
designed to represent shapes.  

#### AbstractShape 
The AbstractShape class implements IShape. It includes a comparator that enables comparison by shape 
width. AbstractShape includes setters and getters for all shape attributes. 

#### Rectangle
The Rectangle class extends AbstractShape. It allows you to construct a rectangle shape. 

`IShape example = new Rectangle("name", 1.0, 1.0, 255, 255, 255, 20.0, 25.0);`

#### Oval
The Oval class extends AbstractShape. It allows you to construct an oval shape. 

`IShape example = new Oval("name", 1.0, 1.0, 255, 255, 255, 20.0, 25.0);`

### IEvent
The IEvent interface describes methods you must use if you implement IEvent. IEvent objects are
designed to represent events that happen on shapes: move, scale, and change color.

#### AbstractEvent
The AbstractEvent class implements IEvent. It includes a change() stub that will be implemented once 
we have more information about how changes will work in our controller.

#### MoveShape
The MoveShape class extends AbstractEvent. Use the MoveShape class to create a move event on an IShape. 

`IEvent example = new MoveShape(example, 1.0, 1.0, 2.0, 2.0);`

#### ChangeColor
The MoveShape class extends AbstractEvent. Use the ChangeColor class to create a color change event on an IShape.

`IEvent example = new ChangeColor(example, 1.0, 1.0, 2.0, 2.0);`

#### ScaleShape
The MoveShape class extends AbstractEvent. Use the ScaleShape class to create a scale shape event on an IShape.

`IEvent example = new ScaleShape(example, 1.0, 1.0, 2.0, 2.0);`

### Helper classes 
#### Color
Color is a class used to store RGB colors. Its constructor accepts three parameters: 
**R**, **G**, and **B**. We created this class so that we could more easily store color values. 

#### Point2D
Point2D is a class used to store (x, y) coordinates. Its constructor accepts two parameters: 
**x** and **y**. We created this class so that we could more easily store location values.

#### EventType
EventType is an enum used to store the types of possible events. Currently, it allows events to be
set as **move**, **scale**, or **recolor**.
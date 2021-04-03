# Easy-Animator
// Still a draft but feel free to add or change things!

The Easy Animator model accepts IShape objects and IEvent objects. You can use these objects to 
create a simple animation.

### AnimationModel
The AnimationModel interface describes methods you must use if you implement AnimationModel. 
AnimationModel objects are designed to represent an animation. 

#### AnimationModelImpl
Use AnimationModelImpl to create an easy animation model. Animations consist of two types of 
objects: IShapes, and IEvents that happen to those shapes. 
###### Useful methods
* addShape(IShape shape) - add an IShape to your animation
* addEvent(IEvent event) - add an IEvent to your animation
* getShapesAtTick(int tick) - get all shapes and their states at a given tick 

### IShape 
The IShape interface describes methods you must use if you implement IShape. IShape objects are 
designed to represent shapes.  

#### AbstractShape 
The AbstractShape class extends IShape. It includes a comparator that enables comparison by shape 
width.

#### Rectangle
The Rectangle class allows you to construct a rectangle shape. 

#### Oval
The Oval class allows you to construct an oval shape. 

### IEvent
The IEvent interface describes methods you must use if you implement IEvent. IEvent objects are
designed to represent events that happen on shapes: move, scale, and change color.

#### AbstractEvent
The AbstractEvent class extends IEvent. It includes a change() stub that will be implemented once 
we have more information about how changes will work in our controller.

#### MoveShape
Use the MoveShape class to create a move event on an IShape. 

#### ChangeColor
Use the ChangeColor class to create a color change event on an IShape.

#### ScaleShape
Use the ScaleShape class to create a scale shape event on an IShape.

### Helper classes 
#### Color
Color is a class used to store RGB colors. Its constructor accepts three parameters: 
**R**, **G**, and **B**. We created this class so that we could more easily store color values. 

#### Point2D
Point2D is a class used to store (x, y) coordinates. Its constructor accepts two parameters: 
**x** and **y**. We created this class so that we could more easily store location values.


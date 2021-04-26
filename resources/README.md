# Easy-Animator

The EasyAnimator model accepts IShape objects and IEvent objects. You can add these objects to the 
model to create text output describing a simple animation.

### EasyAnimator
The EasyAnimator class holds main, where animations are run. It takes in arguments from a formatted test
file to create animations using the AnimatorController and Builder classes.

### AnimatorController
The AnimatorController (changed from AnimatorHelper) class constructs an animation. It takes in a 
readable object and decides the view output. It calls other objects, parses the readable, and builds
the model for a view. It is updated to take in actions events from the new PlaybackView. It takes 
the specific command to the AnimationCommand interface.

### IAnimationModel
The IAnimationModel interface describes methods you must use if you implement IAnimationModel. 
IAnimationModel objects are designed to represent an animation. We created this interface to expose 
only the public methods in the animation model for ease of use by the client.

#### AnimationModelImpl
AnimationModelImpl implements the AnimationModel interface. It generates an animation model. 
Animations consist of two types of objects: IShapes, and IEvents that act on those shapes. 
Each shape must have a unique name so that one or more IEvents can be associated with it. The 
IShapes and IEvents are stored in a LinkedHashMap to keep the IShapes in the order they are 
declared. Originally they were stored in a TreeMap, to sort by the time the shapes appear, but this
was not helpful due to how the shapes overlap. The AnimationModel also has been updated to include
the bounds of the animation, and the end tick of the animation. These changes were based on the text
file inputs for creating animations. This implementation includes private helper methods that don't 
need to be exposed to the client. 
###### Useful methods
* `addShape(IShape shape, int appear, int disappear)` - add an IShape to your animation
* `addEvent(IShape shape, IEvent event, int appear, int disappear)` - add an IEvent to your IShape
* `getShapeMap()` - gets the Linked Hashmap. This method is used by the animation model.
* `getShape()` - gets the keys of the Linked Hashmap, the IShape objects
* `addBounds()` - sets the bounds of an animation model’s view. This method is used by 
  the Builder class, which builds the animation model from an input file
* `getEventList(IShape shape)` - returns a list of all events associated with a shape
* `getShapesAtTick(int tick)` - get all shapes and their states at a given tick. This was 
  implemented for the View’s use in the AnimationPanel class to access the shape types at a given 
  tick, where the frame rate (ticks/second) is user-specified for speed of the animation.
  

### Builder
The Builder class implements the methods from the provided AnimationBuilder interface. The Builder constructs
an AnimationBuilder instance using the passed in model of type IAnimationModel. It builds the 
animation model and sets the model's boundaries.

### ViewFactory
The ViewFactory class takes data from the user and sends it to the correct view type. It was added 
for the View’s use and considers the view type being requested, the model containing the animation 
data, the output file’s name, and the speed at which the animation should animate.

###### Useful methods
* `create()` -  constructs an instance of the requested view using the model, 
  the output file’s name, and the desired speed of the animation

### IView
The IView interface describes the methods common to all view types. We added many methods to the 
interface for the new Playback view.

###### Useful methods
* `createView()` - creates a view of the specified type.
* ` getViewState()` - gets the String representation of the current state of the view.

### TextView
The TextView outputs a text description of the animation to an appendable. Added a method 
to print this view without exiting the program.

###### Useful methods
* `buildTheTextString()` - adds the shapes, their appearance times, and their associated events 
  as Strings to the constructor’s declared StringBuilder. This method helps create the Text View 
  file output.
* `createView()` - creates the text view using the built StringBuilder.
* `getViewState()` - gets the state of the view using the built StringBuilder.

### SVGView
The SVGView outputs an SVG file description of the animation to an appendable. This was added for 
the View’s use. The SVG is written in XML and is used to display the animation. Added a method
to print this view without exiting the program.

###### Useful methods
* `buildTheSVGString()` - considers XML formatting to add the shapes and their associated events as 
  Strings to the constructor’s declared StringBuilder. This method helps create the SVG View file 
  output.
* `createView()` - creates the SVG view using the built StringBuilder.
* `getViewState()` - gets the state of the view using the built StringBuilder.
* `addEvents()` - adds the shape’s events to the StringBuilder.
* `addRectangle(IShape shp)` - returns an SVG format string describing an IShape rectangle.
* `addEllipse(IShape shp)` - returns an SVG format string describing an IShape ellipse.
* `addMove(IShape shp, MoveShape e)` - returns an SVG formatted string describing a move event.
* `addScale(IShape shp, ScaleShape e)` - returns an SVG formatted string describing a scale shape 
  event.
* `addColorChange(IShape shp, IEvent e)` - returns an SVG formatted  string describing a color 
  change event.

### VisualView
The VisualView outputs a visual display of the animation using the JFrame JFC/Swing component 
architecture. It reads the text files and transforms them into an animation. 

###### Useful methods
* `createView()` - creates the visual view using methods extended from the JFrame library and 
  considers the speed being requested at the command line to calculate the speed that the animation 
  will play.

### PlaybackView
The PlaybackView outputs a visual display of the animation using the JFrame JFC/Swing component
architecture. It extends the VisualView and includes new buttons. The playback view includes new 
buttons to affect how the animation plays. The current functionality features:

* Start button (to either begin or resume the animation playing), 
* Stop button (to pause the animation), 
* Start over button (to replay the animation from the beginning), 
* Faster button (to speed up the animation), 
* Slower button (to slow down the animation), 
* Loop checkbox (to make the animation replay when it ends, if checked),
* Load file button (to load a new file to play),
* Save as text button (to save the animation as a text view in a txt file)
* Save as SVG button (to save the animation as an SVG view in a svg file).

### AnimationCommand
The AnimationCommand interface is called after an action is performed in the PlaybackView and sent 
to the AnimatorController. Depending on the button or checkbox, one of the AnimationCommand classes 
is called to handle how the view reacts to the action. The classes include:
* StartAnimation (reacts to the Start button),
* StopAnimation (reacts to the Stop button),
* RestartAnimation (reacts to the Start over button),
* SpeedUp (reacts to the Faster button),
* SlowDown (reacts to the Slower button),
* Loop (reacts to the Loop checkbox being checked or unchecked),
* LoadFile (reacts to the Load button),
* SaveAsText (reacts to the Save as text button),
* SaveAsSVG (reacts to the Save as SVG button),


### AnimationPanel
The AnimationPanel extends JPanel and is a container that can store an organized group of 
components. The AnimationPanel object assists the VisualView class.
###### Useful methods
* `paintComponent(Graphics g)`- this is an override of a method from the JPanel library so that 
  each rectangle and ellipse shape can be drawn at the appropriate tick.

### IShape
The IShape interface describes methods you must use if you implement IShape. IShape objects are 
designed to represent shapes.  We created this interface so that any helper methods stay private and
don't need to be exposed to the client, and there's no need for the client to look at the 
implementation itself. The interface could be extended with additional shape types later. 
It allows a shape to be instantiated without specifying the type of shape.

#### AbstractShape 
The AbstractShape class implements IShape. We removed the comparator in our AbstractShape because we
are no longer using a TreeMap in our model because the shapes do not need to be sorted. 
AbstractShape includes setters and getters for all shape attributes so that the AnimationModel and 
IEvent can easily access and change them. We created this abstract class to reuse code that's common 
to all shapes. 

#### Rectangle
The Rectangle class extends AbstractShape. It allows you to construct a rectangle shape. We created
this concrete class to enable the client to generate a rectangle shape. The constructor takes
in a unique name, an initial location, R, G, and B values for color, and width/height values. A new 
constructor takes in just a unique name, based off how the text files declare shapes. We added a 
ShapeType RECTANGLE field to identify what type of shape it is for the views.

`IShape example = new Rectangle("name", 1.0, 1.0, 255, 255, 255, 20.0, 25.0);`

`IShape example = new Rectangle("name");`

#### Ellipse
The Ellipse class extends AbstractShape. Its name was changed from Oval. It allows you to construct 
an ellipse shape. We created this concrete class to enable the client to generate an ellipse shape. 
The constructor takes in a unique name, an initial location, R, G, and B values for color, and 
width/height values. A new constructor takes in just a unique name, based off how the text files 
declare shapes. We added a ShapeType ELLIPSE field to identify what type of shape it is for the 
views.

`IShape example = new Ellipse("name", 1.0, 1.0, 255, 255, 255, 20.0, 25.0);`

`IShape shape = new Ellipse("name")`

### IEvent
The IEvent interface describes methods you must use if you implement IEvent. IEvent objects are
designed to represent events that happen on shapes. We created this interface as a contract for 
the client, so that helper methods aren't exposed and there's no need for the client to look at 
the implementation itself. The interface could be extended with additional events later. It allows
an event to be instantiated without specifying the type of the event. 

#### AbstractEvent
The AbstractEvent class implements IEvent. It is updated to include a tweening method that works
helps the model get the state of shapes at specific frames in the animation. We created this 
abstract class in order to reuse code common to all events. 

#### MoveShape
The MoveShape class extends AbstractEvent. Use the MoveShape class to create a move event on an
IShape. We created this concrete class so that the client can generate a move event. The constructor 
takes in a shape, an initial location, and a new location. This class is updated to include an
applyEvent method that takes in a copy of a shape and a tick, and mutates the shape location based 
on where it is at the given tick.

`IEvent example = new MoveShape(example, 1.0, 1.0, 2.0, 2.0);`

#### ChangeColor
The MoveShape class extends AbstractEvent. Use the ChangeColor class to create a color change event
on an IShape. We created this concrete class so that the client can generate a color change event. 
The constructor takes in a shape, an initial color, and a new color.This class is updated to include
an applyEvent method that takes in a copy of a shape and a tick, and mutates the shape color based
on its color at the given tick.

`IEvent example = new ChangeColor(example, 0, 0, 0, 255, 255, 255);`

#### ScaleShape
The MoveShape class extends AbstractEvent. Use the ScaleShape class to create a scale shape event
on an IShape. We created this concrete class so that the client can generate a scale shape event.
The constructor takes in a shape, an initial size, and a new size. This class is updated to include 
an applyEvent method that takes in a copy of a shape and a tick, and mutates the shape width or
height based on how it looks at the given tick.

`IEvent example = new ScaleShape(example, 1.0, 1.0, 2.0, 2.0);`

### Helper classes and enums
#### Color
Color is a class used to store RGB colors. Its constructor accepts three ints as parameters: 
**R**, **G**, and **B**. We created this class so that we could more easily store color values. 

#### Point2D
Point2D is a class used to store (x, y) coordinates. Its constructor accepts two doubles as 
parameters: **x** and **y**. We created this class so that we could more easily store location 
values.

#### EventType
EventType is an enum used to store the types of possible events. Currently, it allows events to be
set as **move**, **scale**, or **recolor**. We created this enum so that AnimationModel can easily 
identify an event's type, which helps make sure that the same type of event doesn't overlap for
a given shape. 

#### ShapeType
ShapeType is an enum different types of shapes. It currently has **rectangle** and **ellipse** 
shapes. We created this enum so that EasyAnimator can easily identify a shape's type because the 
shapes behave differently in the views.
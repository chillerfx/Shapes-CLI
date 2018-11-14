# Shapes-CLI
Project for TeleSoftas.

## Instructions

It is a full programming exercise whose outcome should be code that can be compiled, executed and tested
with its own set of unit testing. We expect you to show your best technical skills applying the right patterns.
You may provide an IDE project (Eclipse, IntelliJ) or, if preferred, a maven project to build source code.

## Requirements

Please write a console application with the following behavior:

**1.** When the user enters the name of a shape followed by the corresponding number of numeric
parameters, define that shape and keep it in memory. The numbers may be of type double. Examples:
circle 1.7 -5.05 6.
triangle 4.5 1 -2.5 -33 23 0.
donut 4.5 7.8 1.5 1.
● For the circle, the numbers are the x and y coordinates of the centre followed by the radius.
● For the triangle it is the x and y coordinates of the three vertices (six numbers in total).
● For the donut it is the x and y of the centre followed by the two radiuses.
In addition, every time such a line is entered, the application should give it a unique identifier and print it out
in a standardized form, for example:
=> shape 1: circle with centre at (1.7, -5.05) and radius 6.
**2.** When the user enters a pair of numbers, the application should print out all the shapes that include
that point in the (x, y) space, i.e. it should print out shape S if the given point is inside S. (A point is inside a
donut shape if it is inside the outer circle but not inside the inner one.)
It should also print out the surface area of each shape found, and the total area of all the shapes returned for a
given point.
**3.** It should accept the commands “help” for printing instructions and “exit” for terminating the
execution.
**4.** If the user enters anything unexpected (including errors like too few/many arguments, incorrect
number format, etc.), it should print a meaningful error message and continue the execution.
**5.** Unit Testing. Feel free to use any frameworks for unit testing.


## Extras

**6.** Think about implementing it in a way which would perform well even for a very large number
shapes (e.g., tens of millions, but assuming it can still be held in the program memory).
**7.** Allow input from a file as well as the console. It should be possible, for example, to read a file
with shape definitions and then to continue with an interactive session. Please provide a sample input file
for testing.
**8.** Feel free to add additional shapes (e.g. square, rectangle, ellipsis) and operations (e.g. to delete a
given shape). An advanced option could be to find all the shapes that overlap one that’s named by the
user.
**9.** Build file (ANT, Maven, Gradle, ...) project.
**10.** When calculating all figures that contains a specific point (x, y), use threading for parallelism.
**11.** Dependency injection.
**12.** Use any database to store the figures.
    **Redacted contact info**
       +
    **Redacted contact info**

## Implementation

Project has been implemented using Spring boot, Spring shell and MySql to store figures. Maven is used for dependecy managment and packaging.

### Implemention details

Shell commands are parsed by Spring Shell @ShellComponent and arguments are forwarded to coresponding class constructor.
Each Shape class commands (circle, square, donut etc) constructor construct new **shape** class relation magment ( handled by JPA annotations ).
Constructed Shape itself is represented as java.awt.geom.Path2D class in order perform simple computations. Each shape along with its computed area and meta information is persisted within shape repository that depends on Shape class using dependency injection.

Other commands such as contains, overlaps are implemented using shape repository in order to decrease query complexity of quereing multiple tables.
In order to find shapes that contain point or  overlaps (intersects) with shape is just using or contains(x, y) intersects(boundry2d) method. There was few different concideration of way of implementing (intresect and contains) such as using geometry libraries such as arcgis or JTS to construct geometries and perfome computatinons over them.
For example command **contains** is just quering all shapes repository for all shapes and then proccessing them in parrallel stream to aggregate them into desireble output. Shell-command **overlaps** follows simlar pattern.
For more detailed implementation deltails that I forgot to mention here look into source code I provided here.

### System dependences

JavaSE-1.8
MySQL5

### How to
**1.** Clone this repo
**3.** Install dependecies with 
```
mvn install
```
**2.** Edit  ``` application.properties ``` file with mysql connection strings and table(shema) name
**3.** Run ``` mvn package ``` to package the application

**4.** Get back to me with feedback


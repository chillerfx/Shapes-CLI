# Shapes-CLI
Project for TeleSoftas.

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
**1.** Clone this repo.

**2.** Install dependecies with 
```
mvn install
```
**3.** Edit  ``` application.properties ``` file with mysql connection strings and table(shema) name.

**4.** Run ``` mvn package ``` to package the application

**5.** Get back to me with feedback


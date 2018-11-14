package com.TelesSoftas.Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Circle {

	@Id
	public Integer id;
	public Double radius;
	public Double x;
	public Double y;
	@OneToOne(cascade = CascadeType.ALL)
    @MapsId
	public Shape shape;

	public Circle(Double x,Double y, Double radius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.shape = getShape(x, y, radius);
	}

	@Override
	public String toString() {
		return String.format("Circle with centre at (  %s, %s ), with radius of %s;", this.getX(),
				this.getY(), this.getRadius());
	}
	public Shape getShape(Double x, Double y, Double radius) {
	    Double newX = x - radius;
	    Double newY = y - radius;

		Ellipse2D.Double ellispse = new Ellipse2D.Double(newX, newY, radius*2, radius*2);
		Path2D.Double path = new Path2D.Double(ellispse);
		path.closePath();
		return new Shape(path, this.toString(), getArea()); 
	}
	public Double getArea() {
		Double area = Math.PI*this.getRadius()*this.getRadius();
		return area;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
}

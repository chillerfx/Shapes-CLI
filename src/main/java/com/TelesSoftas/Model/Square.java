package com.TelesSoftas.Model;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;


@Entity
public class Square {
    @Id
	public Integer id;
	public Double sideLength;
	public Double x;
	public Double y;
	@OneToOne(cascade = CascadeType.ALL)
    @MapsId
	public Shape shape;

	public Square(Double x, Double y, Double sideLength) {
		super();
		this.x = x;
		this.y = y;
		this.sideLength = sideLength;
		this.shape = getShape(x, y, sideLength); 
	}

	@Override
	public String toString() {
		return String.format("Square with at corner position (  %s, %s ), with side length of %s;", this.getX(),
				this.getY(), this.getSideLength());
	}
	public Shape getShape(Double x2, Double y2, Double sideLength2) {
		Rectangle2D.Double rect = new Rectangle2D.Double(x, y, sideLength, sideLength);
		Path2D area = new Path2D.Double(rect);
		return new Shape(area, this.toString(), getArea());
	}
	public Double getArea() {
		Double area = this.getSideLength()*this.getSideLength(); 
		return area;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSideLength() {
		return sideLength;
	}

	public void setSideLength(Double sideLength) {
		this.sideLength = sideLength;
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

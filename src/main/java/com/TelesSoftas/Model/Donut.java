package com.TelesSoftas.Model;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Donut {
	@Id
	public Integer id;
	public Double radius;
	public Double x;
	public Double y;
	public Double innerRadius;
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	public Shape shape;

	public Donut(Double x, Double y, Double radius, Double innerRadius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.innerRadius = innerRadius;
		this.shape = getShape(x, y, radius, innerRadius);
	}

	@Override
	public String toString() {
		return String.format("Donut with centre at (  %s, %s ), with outside radius of %s and inner radius of %s;",
				this.getX(), this.getY(), this.getRadius(), this.getInnerRadius());
	}

	public Shape getShape(Double x, Double y, Double radius, Double innerRadius) {

		Double newX = x - radius;
		Double newY = y - radius;

		Ellipse2D.Double ellipse = new Ellipse2D.Double(newX, newY, radius * 2, radius * 2);
		Ellipse2D.Double innerEllispse = new Ellipse2D.Double(newX + radius / 2, newY + radius / 2, innerRadius * 2,
				innerRadius * 2);
		Area area = new Area(ellipse);
		Area innerArea = new Area(innerEllispse);
		area.subtract(innerArea);
		Path2D poly = new Path2D.Double(area);
		poly.closePath();
		return new Shape(poly, this.toString(), getArea());
	}

	public Double getArea() {
		Double area = (Math.PI * this.getRadius() * this.getRadius())
				- (Math.PI * this.getInnerRadius() * this.getInnerRadius());
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

	public Double getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(Double innerRadius) {
		this.innerRadius = innerRadius;
	}
}

package com.TelesSoftas.Model;

import java.awt.geom.Path2D;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Triangle {
	@Id
	Integer id;
	Double vrtx1x;
	Double vrtx1y;
	Double vrtx2x;
	Double vrtx2y;
	Double vrtx3x;
	Double vrtx3y;
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	public Shape shape;

	public Triangle(Double vrtx1x, Double vrtx1y, Double vrtx2x, Double vrtx2y, Double vrtx3x, Double vrtx3y) {
		this.vrtx1x = vrtx1x;
		this.vrtx1y = vrtx1y;
		this.vrtx2x = vrtx2x;
		this.vrtx2y = vrtx2y;
		this.vrtx3x = vrtx3x;
		this.vrtx3y = vrtx3y;
		this.shape = getShape(vrtx1x, vrtx1y, vrtx2x, vrtx2y, vrtx3x, vrtx3y);
	}

	@Override
	public String toString() {
		return String.format("Triangle with at vertices at positions (%s, %s ), (%s, %s ), (%s, %s )",
				this.getVrtx1x(), this.getVrtx1y(), this.getVrtx2x(), this.getVrtx2y(), this.getVrtx3x(),
				this.getVrtx3y());
	}

	public Shape getShape(Double vrtx1x, Double vrtx1y, Double vrtx2x, Double vrtx2y, Double vrtx3x, Double vrtx3y) {
		Path2D.Double triangle = new Path2D.Double();
		triangle.moveTo(vrtx1x, vrtx1y);
		triangle.lineTo(vrtx2x, vrtx2y);
		triangle.lineTo(vrtx3x, vrtx3y);
		triangle.closePath();
		return new Shape(triangle, this.toString(), getArea());
	}

	public Double getArea() {
		Double area = Math.abs((this.getVrtx1x() * (this.getVrtx2y() - this.getVrtx3y())
				- this.getVrtx2x() * (this.getVrtx3y() - this.getVrtx1y())
				- this.getVrtx3x() * (this.getVrtx1y() - this.getVrtx2y())) / 2);
		return area;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getVrtx1x() {
		return vrtx1x;
	}

	public void setVrtx1x(Double vrtx1x) {
		this.vrtx1x = vrtx1x;
	}

	public Double getVrtx1y() {
		return vrtx1y;
	}

	public void setVrtx1y(Double vrtx1y) {
		this.vrtx1y = vrtx1y;
	}

	public Double getVrtx2x() {
		return vrtx2x;
	}

	public void setVrtx2x(Double vrtx2x) {
		this.vrtx2x = vrtx2x;
	}

	public Double getVrtx2y() {
		return vrtx2y;
	}

	public void setVrtx2y(Double vrtx2y) {
		this.vrtx2y = vrtx2y;
	}

	public Double getVrtx3x() {
		return vrtx3x;
	}

	public void setVrtx3x(Double vrtx3x) {
		this.vrtx3x = vrtx3x;
	}

	public Double getVrtx3y() {
		return vrtx3y;
	}

	public void setVrtx3y(Double vrtx3y) {
		this.vrtx3y = vrtx3y;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

}

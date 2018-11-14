package com.TelesSoftas.Model;

import java.awt.geom.Path2D;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Shape {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	@Type(type = "serializable")
	@Column(columnDefinition = "longblob")
	Path2D shape;
	String shapeMeta;
	Double shapeArea;

	@Override
	public String toString() {
		return String.format("ID %s : %s", this.getId(), this.getShapeMeta());
	}

	public Shape(Path2D area, String shapeMeta, Double shapeArea) {
		this.shape = area;
		this.shapeMeta = shapeMeta;
		this.shapeArea = shapeArea;
	}

	public String toResponse() {
		return String.format("ID %s : %s with area %s", this.getId(), this.getShapeMeta(), this.getShapeArea().toString());
	}

	/**
	 * @TODO implement method to get shape surface area whether using JTS or arcgis
	 *       lib.
	 */
	public void getArea() {
	}

	public Shape() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Path2D getShape() {
		return shape;
	}

	public void setShape(Path2D shape) {
		this.shape = shape;
	}

	public String getShapeMeta() {
		return shapeMeta;
	}

	public void setShapeMeta(String shapeMeta) {
		this.shapeMeta = shapeMeta;
	}

	public Double getShapeArea() {
		return shapeArea;
	}

	public void setShapeArea(Double shapeArea) {
		this.shapeArea = shapeArea;
	}

}

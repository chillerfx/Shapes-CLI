package com.TelesSoftas.Commands;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.TelesSoftas.Model.Circle;
import com.TelesSoftas.Model.Donut;
import com.TelesSoftas.Model.Shape;
import com.TelesSoftas.Model.Square;
import com.TelesSoftas.Model.Triangle;
import com.TelesSoftas.Repository.CircleRepo;
import com.TelesSoftas.Repository.DonutRepo;
import com.TelesSoftas.Repository.ShapeRepo;
import com.TelesSoftas.Repository.SquareRepo;
import com.TelesSoftas.Repository.TriangleRepo;
import com.opencsv.CSVReader;

@ShellComponent
public class ShapeCommands {

	@Autowired
	SquareRepo squareRepo;

	@ShellMethod("Creates new square shape.")
	public String square(@ShellOption() Double x, @ShellOption() Double y, @ShellOption() Double sideLength) {
		Square square = new Square(x, y, sideLength);
		squareRepo.save(square);
		String response = String.format("Shape %s: %s", square.getId().toString(), square.toString());
		return response;
	}

	@Autowired
	CircleRepo circleRepo;

	@ShellMethod("Creates new circle shape.")
	public String circle(@ShellOption(help = "X coordinate of circle center") Double x,
			@ShellOption(help = "Y coordinate of circle center") Double y,
			@ShellOption(help = "Radius of circle") @PositiveOrZero Double radius) {
		Circle circle = new Circle(x, y, radius);
		circleRepo.save(circle);
		String response = String.format("Shape %s: %s", circle.getId().toString(), circle.toString());
		return response;
	}

	@Autowired
	TriangleRepo triangleRepo;

	@ShellMethod("Creates new triangle shape.")
	public String triangle(@ShellOption(help = "X1 coordinate of triangle vertice") Double x1,
			@ShellOption(help = "Y1 coordinate of triangle vertice") Double y1,
			@ShellOption(help = "X2 coordinate of triangle vertice") Double x2,
			@ShellOption(help = "Y2 coordinate of triangle vertice") Double y2,
			@ShellOption(help = "X3 coordinate of triangle vertice") Double x3,
			@ShellOption(help = "Y3 coordinate of triangle vertice") Double y3) {
		Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);
		triangleRepo.save(triangle);
		String response = String.format("Shape %s: %s", triangle.getId().toString(), triangle.toString());
		return response;
	}

	@Autowired
	DonutRepo donutRepo;

	@ShellMethod("Creates new donut shape.")
	public String donut(@ShellOption(help = "X coordinate of Donut center") Double x,
			@ShellOption(help = "Y coordinate of Donut center") Double y,
			@ShellOption(help = "Outer radius of donut") @PositiveOrZero Double radius,
			@ShellOption(help = "Inner radius of donut") @PositiveOrZero Double innerRadius) {
		Donut donut = new Donut(x, y, radius, innerRadius);
		donutRepo.save(donut);
		String response = String.format("Shape %s: %s", donut.getId().toString(), donut.toString());
		return response;
	}

	@Autowired
	ShapeRepo shapeRepo;

	@ShellMethod(value = "Searches for shapes that contains given cooridantes.", group = "Other Commands")
	public String contains(@ShellOption() Double x, @ShellOption() Double y) {
		List<Shape> shapes = shapeRepo.findAll();

		List<Shape> ShapesContainingPoint = shapes.parallelStream().filter(item -> item.getShape().contains(x, y))
				.collect(Collectors.toList());

		String shapesString = ShapesContainingPoint.parallelStream().map(shape -> shape.toResponse())
				.collect(Collectors.joining("\n"));

		Double totalShapeArea = shapes.parallelStream().mapToDouble(shape -> shape.getShapeArea().doubleValue()).sum();

		String response = String.format("Shapes containting point (%s, %s): \n%s \n Total shape area: %s Square Units",
				x.toString(), y.toString(),
				shapesString.isEmpty() ? "No Shape contains these coordinates" : shapesString, totalShapeArea);
		return response;
	}

	@ShellMethod(value = "Searches for shapes that overlaps (intersects) with given shape ID.", group = "Other Commands")
	public String overlaps(@ShellOption() Integer id) {
		Optional<Shape> shape = shapeRepo.findById(id);
		List<Shape> shapes = shapeRepo.findAll();

		if (!shape.isPresent())
			return String.format("There is no shape with id %s", id.toString());

		String shapesString = shapes.parallelStream()
				.filter(s -> s.getShape().getBounds2D().intersects(shape.get().getShape().getBounds2D()))
				.map(s -> s.toResponse()).collect(Collectors.joining("\n"));
		return String.format("Shapes overlaping %s:\n%s", shape.get().getShapeMeta(),
				shapesString.isEmpty() ? "There is no shape overlaping this shape" : shapesString);
	}

	@ShellMethod(value = "Parses csv file with shape data", group = "Other Commands")
	public String parseCsv(@ShellOption(help = "relative path to csv file") String path) {
		String response;
		try {
			CSVReader reader = new CSVReader(new FileReader(path));
			List<String[]> csvEntries = reader.readAll();
			reader.close();
			response = csvEntries.parallelStream().map(line -> this.createClassFromLine(line))
					.collect(Collectors.joining("\n"));

		} catch (Exception e) {
			response = e.toString();
		}
		return response;
	}

	public String createClassFromLine(String[] line) {
		switch (line[0]) {
		case "circle":
			return this.circle(Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]));
		case "donut":
			return this.donut(Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]),
					Double.parseDouble(line[4]));
		case "square":
			return this.square(Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]));

		case "triangle":
			return this.triangle(Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]),
					Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6]));
		default:
			return (String.format("Invalid / non existing or not implemented shape %s", line.toString()));
		}
	}
}

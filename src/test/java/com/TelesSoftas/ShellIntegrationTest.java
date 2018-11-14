package com.TelesSoftas;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.TelesSoftas.Commands.ShapeCommands;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShellIntegrationTest {
	
	@Test
	public void testCircleCommand() {
		ShapeCommands sc = new ShapeCommands();
		String circlStingTest = sc.circle((double) 50,(double) 50,(double) 50);
		assertThat(circlStingTest, is(equalTo("Shape 1: Circle with centre at (  50.0, 50.0 ), with radius of 50.0;")));
	};

}
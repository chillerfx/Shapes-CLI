package com.TelesSoftas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TelesSoftas.Model.Circle;

@Repository
public interface CircleRepo extends JpaRepository<Circle, Integer>  {

}

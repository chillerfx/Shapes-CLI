package com.TelesSoftas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TelesSoftas.Model.Triangle;

@Repository
public interface TriangleRepo extends JpaRepository<Triangle, Integer> {

}

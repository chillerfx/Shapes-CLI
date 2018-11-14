package com.TelesSoftas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TelesSoftas.Model.Square;

@Repository
public interface SquareRepo extends JpaRepository<Square, Integer>  {

}

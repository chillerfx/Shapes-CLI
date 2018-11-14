package com.TelesSoftas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TelesSoftas.Model.Donut;

@Repository
public interface DonutRepo extends JpaRepository<Donut, Integer>  {

}

package com.salesianostriana.dam.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.proyecto.modelo.Personaje;
import com.salesianostriana.dam.proyecto.modelo.Skin;
/**
 * Interfaz donde guardamos los datos de los personajes en la base de datos
 * @author aleja
 *
 */
public interface PersonajeRepositorio extends JpaRepository<Personaje, Long>{


}

package com.salesianostriana.dam.proyecto.servicio;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyecto.modelo.Personaje;
import com.salesianostriana.dam.proyecto.modelo.Skin;
import com.salesianostriana.dam.proyecto.repositorio.PersonajeRepositorio;
import com.salesianostriana.dam.proyecto.servicio.base.ServicioBase;
/**
 * Clase que sirve para aplicar funcionalidades a los personajes
 * @author aleja
 *
 */
@Service
public class PersonajeServicio extends ServicioBase<Personaje, Long, PersonajeRepositorio>{
/**
 * Metodo para buscar por id de las skins
 * @param p
 * @param s
 * @return La id de la skin en la lista de la clase personaje
 */
	public int posicionSkinById(Personaje p, Skin s) {
		for (int i = 0; i <  p.getSkins().size() ; i++) {
			
			if(s.getIdSkin() == p.getSkins().get(i).getIdSkin()) {
				return i;
			}		
		}
		return -1;
	}
}

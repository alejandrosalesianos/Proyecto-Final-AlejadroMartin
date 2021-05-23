package com.salesianostriana.dam.proyecto;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.proyecto.modelo.Personaje;
import com.salesianostriana.dam.proyecto.modelo.Skin;
import com.salesianostriana.dam.proyecto.servicio.PersonajeServicio;

import lombok.RequiredArgsConstructor;
/**
 * Clase para iniciar datos en la aplicación web
 * @author aleja
 *
 */
@Component
@RequiredArgsConstructor
public class InitData {
	
	private final PersonajeServicio personajeSer;
	/**
	 * Inicia los datos y los guarda en la base de datos
	 */
	@PostConstruct
	public void init() {
		List<Personaje> listaPersonajes = List.of(
				new Personaje("Merlin", "Mago"),
				new Personaje("Artemis", "Arquera"),
				new Personaje("Sobek", "Support"),
				new Personaje("Cú Chulainn", "Guerrero"),
				new Personaje("Loki", "Asesino")
				);
		for (Personaje personaje : listaPersonajes) {
			personajeSer.save(personaje);
		}
		List<Skin> listaSkins = List.of(
				new Skin("LandShark","Tier 4",1200,"Esta Skin de Sobek hace que se vea como un tiburón playero","https://i.ytimg.com/vi/Q9ersHJLxH8/maxresdefault.jpg",listaPersonajes.get(2)),
				new Skin("Exploradora Espacial","Tier 4",1200,"Esta Skin hace ver a artemis como si estuviera en una mision espacial con un alien de mascota","https://i.ytimg.com/vi/XnjcvjE0KNU/maxresdefault.jpg",listaPersonajes.get(1)),
				new Skin("Dr.Strange","Tier 3",800,"Esta Skin hace que el personaje de merlin se parezca al de la película de Dr.Strange","https://i.redd.it/u575akcvquk41.jpg",listaPersonajes.get(0)),
				new Skin("Wasteland","Tier 4",1200,"Esta Skin hace que el personaje se vuelva calvo y radiactivo","https://i.ytimg.com/vi/rFEezdo8kwc/hqdefault.jpg",listaPersonajes.get(3)),
				new Skin("Infiltración","Tier 2",400,"Esta Skin hace que loki se vuelva un agente secreto","http://fc08.deviantart.net/fs71/f/2015/010/b/1/loki_by_plumbeck-d8ddfnc.png",listaPersonajes.get(4))
				);
		listaPersonajes.get(2).addSkin(listaSkins.get(0));
		personajeSer.save(listaPersonajes.get(2));
		listaPersonajes.get(1).addSkin(listaSkins.get(1));
		personajeSer.save(listaPersonajes.get(1));
		listaPersonajes.get(0).addSkin(listaSkins.get(2));
		personajeSer.save(listaPersonajes.get(0));
		listaPersonajes.get(3).addSkin(listaSkins.get(3));
		personajeSer.save(listaPersonajes.get(3));
		listaPersonajes.get(4).addSkin(listaSkins.get(4));
		personajeSer.save(listaPersonajes.get(4));
	}
}

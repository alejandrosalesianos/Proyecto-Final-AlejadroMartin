package com.salesianostriana.dam.proyecto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyecto.modelo.Personaje;
import com.salesianostriana.dam.proyecto.modelo.Skin;
import com.salesianostriana.dam.proyecto.servicio.PersonajeServicio;

import lombok.RequiredArgsConstructor;
/**
 * Esta clase controla todos los editar de borrar y editar tanto de personajes como de skins
 * @author aleja
 *
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/personaje")
public class PersonajeController {
	
private final PersonajeServicio personajeServicio;
	/**
	 * Pasamos todos los personajes para poder imprimirlos
	 * @param model
	 * @return la pagina con todos los personajes
	 */
		@GetMapping("/")
		public String todosLosPersonajes(Model model) {
		model.addAttribute("personajes", personajeServicio.findAll());		
		return "ListaPersonajes";
	
	}
		/**
		 * pasamos un nuevo personaje para registrar en el formulario
		 * @param model
		 * @return 
		 */
	@GetMapping("/nuevo")
	public String agregarPersonaje(Model model) {
		model.addAttribute("personaje", new Personaje());
		return "FormularioPersonaje";
	}
	/**
	 * registramos los datos del formulario
	 * @param personaje
	 * @param model
	 * @return El nuevo personaje guardado en la base de datos
	 */
	@PostMapping("/nuevo/submit")
	public String submit(@ModelAttribute("persForm") Personaje personaje, Model model) {
		personajeServicio.save(personaje);
		return "redirect:/personaje/";
	}
	/**
	 * Pasamos por la url la id del personaje a modificar
	 * @param id
	 * @param model
	 * @return Devuelve la pagina para editar personajes
	 */
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		Personaje personaje = personajeServicio.findById(id).orElse(null);
		if(personaje != null) {
			model.addAttribute("personaje", personaje);
			return "FormularioPersonaje";
		}else {
			return "redirect:/personaje/";
		}
	}
	/**
	 * Registramos los datos del formulario
	 * @param personaje
	 * @param model
	 * @return Guardamos los datos modificados
	 */
	@PostMapping("/editar/submit")
	public String submitEditar(@ModelAttribute("persForm") Personaje personaje, Model model) {
		personajeServicio.edit(personaje);
		return "redirect:/personaje/";
	}
	/**
	 * Borramos el personaje del que pasemos la Id
	 * @param id
	 * @param model
	 * @return Borra al personaje de la base de datos
	 */
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") Long id, Model model) {
		Personaje personaje = personajeServicio.findById(id).orElse(null);	
		personajeServicio.delete(personaje);
		return "redirect:/personaje/";
	}
	/**
	 * Nos redirige a la pagina de detalles de cada personaje segun la Id
	 * @param id
	 * @param model
	 * @return La página del personaje seleccionado
	 */
	@GetMapping("/detalles/{id}")
	public String detalles(@PathVariable("id")Long id, Model model) {
		Personaje pers = personajeServicio.findById(id).orElse(null);
		if(pers != null) {
			model.addAttribute("personaje", pers);
			return "DetallesSkins";
		}
			return "redirect:/personaje/";
	}
	/**
	 * Editamos la skin del personaje seleccionado en los detalles
	 * @param id
	 * @param idSkin
	 * @param model
	 * @return Editar la skin del personaje seleccionada
	 */
	@GetMapping("/detalles/{id}/editar/{idSkin}")
	public String editarSkin(@PathVariable("id")Long id , @PathVariable("idSkin")Long idSkin,Model model) {
		Personaje personaje = personajeServicio.findById(id).orElse(null);
		Skin skins = new Skin();
				List<String> tiers = List.of(
				"Tier 1",
				"Tier 2",
				"Tier 3",
				"Tier 4",
				"Tier 5"
				);
				model.addAttribute("tier", tiers);
				model.addAttribute("personaje", personaje);
		for (Skin skin : personaje.getSkins()) {
			if(skin.getIdSkin() == idSkin) {
				skins = skin;
			}
		}
		if(personaje != null) {
			model.addAttribute("skinForm", skins);
			return "formularioSkin";
		}
			return "redirect:/personaje/";
	}
	/**
	 * registramos los datos de la edición de skin
	 * @param skin
	 * @param model
	 * @param personaje
	 * @return La skin editada del personaje
	 */
	@PostMapping("/detalles/editar/submit")
	public String editarSkinSubmit(@ModelAttribute("skinForm")Skin skin,Model model,Personaje personaje) {
		model.addAttribute("skin", personaje);
		personaje.removeSkin(personaje.getSkins().get(personajeServicio.posicionSkinById(personaje, skin)));
		personaje.addSkin(skin);
		personajeServicio.save(personaje);
		return "redirect:/";
	}
	/**
	 * Borramos la skin del personaje seleccionado en los detalles
	 * @param id
	 * @param idSkin
	 * @param model
	 * @return Borrar la skin del personaje seleccionada
	 */
	@GetMapping("/detalles/{id}/Borrar/{idSkin}")
	public String borrarSkin(@PathVariable("id")Long idPer , @PathVariable("idSkin")Long idSkin,Model model) {
		Personaje personaje = personajeServicio.findById(idPer).orElse(null);
		Skin skins = new Skin();
		for (Skin skin : personaje.getSkins()) {
			if(skin.getIdSkin() == idSkin) {
				skins = skin;
				personaje.removeSkin(skins);
				personajeServicio.save(personaje);
				return "redirect:/";
			}
		}
		return "redirect:/";
	}
}

package com.salesianostriana.dam.proyecto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyecto.modelo.Personaje;
import com.salesianostriana.dam.proyecto.modelo.Skin;
import com.salesianostriana.dam.proyecto.servicio.PersonajeServicio;

import lombok.RequiredArgsConstructor;
/**
 * Esta clase controla toda la parte relacionada con la pagina de index
 * @author aleja
 *
 */
@Controller
@RequiredArgsConstructor
public class MainController {
	private final PersonajeServicio personajeServicio;
	/**
	 * 
	 * @param model
	 * @return Devuelve todos los personajes y sus skins
	 */
	@GetMapping("/")
	public String todosLosPersonajesYSkins(Model model) {
		model.addAttribute("personajes", personajeServicio.findAll());	
		return "index";
	
	}
	/**
	 * Pasamos por el modal los atributos necesarios para pintar la página
	 * @param model
	 * @return Te redirige a la pagina para registrar skins
	 */
	@GetMapping("/formularioSk")
	public String formularioSkins(Model model) {
		
		List<String> tiers = List.of(
				"Tier 1",
				"Tier 2",
				"Tier 3",
				"Tier 4",
				"Tier 5"
				);
		model.addAttribute("personaje", personajeServicio.findAll());
		model.addAttribute("tier", tiers);
		model.addAttribute("skinForm", new Skin());
		return "FormularioSkin";
	}
	/**
	 * Pasamos el tipo de dato que queremos que sea con ModelAttribute y creamos una skin a traves del formulario
	 * @param skin
	 * @param personaje
	 * @param model
	 * @return La skin que rellenamos en el formulario
	 */
	@PostMapping("/formularioSk/submit")
	public String submit(@ModelAttribute("skinForm")Skin skin,Personaje personaje, Model model) {
		model.addAttribute("skin", personaje);
		personaje.addSkin(skin);
		personajeServicio.save(personaje);
		return "redirect:/";
	}
}

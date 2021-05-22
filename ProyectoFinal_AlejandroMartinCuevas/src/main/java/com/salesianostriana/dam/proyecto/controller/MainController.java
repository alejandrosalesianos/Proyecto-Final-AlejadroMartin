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

@Controller
@RequiredArgsConstructor
public class MainController {
	private final PersonajeServicio personajeServicio;
	
	@GetMapping("/")
	public String todosLosPersonajesYSkins(Model model) {
		model.addAttribute("personajes", personajeServicio.findAll());	
		return "index";
	
	}
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
	@PostMapping("/formularioSk/submit")
	public String submit(@ModelAttribute("skinForm")Skin skin,Personaje personaje, Model model) {
		model.addAttribute("skin", personaje);
		personaje.addSkin(skin);
		personajeServicio.save(personaje);
		return "redirect:/";
	}
	@PostMapping("editar/submit")
	public String submitEditar(@ModelAttribute("skinForm") Skin skin,Personaje personaje, Model model) {
		model.addAttribute("skin", personaje);
		personaje.addSkin(skin);
		personajeServicio.save(personaje);
		return "redirect:/";
	}
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") Long id,Skin skin, Model model) {
		Personaje personaje = personajeServicio.findById(id).orElse(null);		
		personaje.removeSkin(personaje.getSkins().get(0));
		return "redirect:/";
	}
}

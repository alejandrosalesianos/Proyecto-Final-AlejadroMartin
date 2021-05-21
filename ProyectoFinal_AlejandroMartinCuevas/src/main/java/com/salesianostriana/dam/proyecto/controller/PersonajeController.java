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

@Controller
@RequiredArgsConstructor
@RequestMapping("/personaje")
public class PersonajeController {
	
private final PersonajeServicio personajeServicio;
	
		@GetMapping("/")
		public String todosLosPersonajesYSkins(Model model) {
		model.addAttribute("personajes", personajeServicio.findAll());		
		return "ListaPersonajes";
	
	}
	@GetMapping("/nuevo")
	public String agregarPersonaje(Model model) {
		model.addAttribute("personaje", new Personaje());
		return "FormularioPersonaje";
	}
	@PostMapping("/nuevo/submit")
	public String submit(@ModelAttribute("persForm") Personaje personaje, Model model) {
		personajeServicio.save(personaje);
		return "redirect:/personaje/";
	}
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
	@PostMapping("/editar/submit")
	public String submitEditar(@ModelAttribute("persForm") Personaje personaje, Model model) {
		personajeServicio.edit(personaje);
		return "redirect:/personaje/";
	}
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") Long id, Model model) {
		Personaje personaje = personajeServicio.findById(id).orElse(null);	
		personajeServicio.delete(personaje);
		return "redirect:/personaje/";
	}
	@GetMapping("/detalles/{id}")
	public String detalles(@PathVariable("id")Long id, Model model) {
		Personaje pers = personajeServicio.findById(id).orElse(null);
		if(pers != null) {
			model.addAttribute("personaje", pers);
			return "DetallesSkins";
		}
			return "redirect:/personaje/";
	}
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
	@PostMapping("/detalles/editar/submit")
	public String editarSkinSubmit(@ModelAttribute("skinForm")Skin skin,Model model,Personaje personaje) {
		model.addAttribute("skin", personaje);
		personaje.removeSkin(personaje.getSkins().get(personajeServicio.posicionSkinById(personaje, skin)));
		personaje.addSkin(skin);
		personajeServicio.save(personaje);
		return "redirect:/";
	}
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

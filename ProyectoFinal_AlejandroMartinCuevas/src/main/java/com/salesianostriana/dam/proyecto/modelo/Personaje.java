package com.salesianostriana.dam.proyecto.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.engine.profile.Fetch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Esta es la clase que va a gestionar todo el tema del control de personajes y donde se encuentra la lista de skins
 * que puede tener un personaje
 * @author aleja
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Personaje {

	@Id @GeneratedValue
	private long id;
	
	private String nombre;
	private String clasePersonaje;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "personaje", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Skin> skins = new ArrayList<>();
	
	/**
	 * 
	 * @param nombre
	 * @param clasePersonaje
	 */
	public Personaje(String nombre, String clasePersonaje) {
		this.nombre = nombre;
		this.clasePersonaje = clasePersonaje;
	}
	public void addSkin (Skin s) {
		this.skins.add(s);
		s.setPersonaje(this);
	}
	public void removeSkin (Skin s) {
		this.skins.remove(s);
		s.setPersonaje(null);
	}
	
}

package com.salesianostriana.dam.proyecto.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * La clase que da forma a las skins
 * @author aleja
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skin {

	@Id
	@GeneratedValue
	private long idSkin;

	private String nombreSkin;
	private String categoria; // hay categorias desde 1 a 5 siendo 5 la mejor
	private double precio;
	private String descripcion;
	private String img;

	@ManyToOne
	private Personaje personaje;

	/**
	 * 
	 * @param nombreSkin
	 * @param categoria
	 * @param precio
	 * @param descripcion
	 */
	public Skin(String nombreSkin, String categoria, double precio, String descripcion) {
		this.nombreSkin = nombreSkin;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * @param nombreSkin
	 * @param categoria
	 * @param precio
	 * @param descripcion
	 * @param img
	 * @param personaje
	 */
	public Skin(String nombreSkin, String categoria, double precio, String descripcion, String img,
			Personaje personaje) {
		this.nombreSkin = nombreSkin;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
		this.img = img;
		this.personaje = personaje;
	}
	

}

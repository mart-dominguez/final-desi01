package tsti.desi.final01.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Integer cantMaxima;
	private Integer antigMinima;

	@OneToMany(mappedBy = "cursoAsignado")
	@JsonIgnore
	@JsonIgnoreProperties("")
	private List<Docente> docentes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCantMaxima() {
		return cantMaxima;
	}
	public void setCantMaxima(Integer cantMaxima) {
		this.cantMaxima = cantMaxima;
	}
	public Integer getAntigMinima() {
		return antigMinima;
	}
	public void setAntigMinima(Integer antigMinima) {
		this.antigMinima = antigMinima;
	}
	public List<Docente> getDocentes() {
		return docentes;
	}
	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}
	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", cantMaxima=" + cantMaxima + ", antigMinima=" + antigMinima
				+ "]";
	}

	
	
}

package tsti.desi.final01.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Docente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Integer antiguedad;
	@ManyToOne
	private Curso cursoAsignado;
	
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
	public Integer getCreditos() {
		return antiguedad;
	}
	public void setCreditos(Integer creditos) {
		this.antiguedad = creditos;
	}
	public Curso getCursoAsignado() {
		return cursoAsignado;
	}
	public void setCursoAsignado(Curso cursoAsignado) {
		this.cursoAsignado = cursoAsignado;
	}
	@Override
	public String toString() {
		return "Docente [id=" + id + ", nombre=" + nombre + ", creditos=" + antiguedad + ", cursoAsignado="
				+ cursoAsignado + "]";
	}
	
	
}

package tsti.desi.final01.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsti.desi.final01.dominio.Curso;
import tsti.desi.final01.dominio.Docente;
import tsti.desi.final01.repositorio.DocenteRepository;

@Service
public class DocenteService {

	@Autowired // borrar
	private DocenteRepository docenteRepo;

	@Autowired 
	private CursoService cursoService;

	public Docente guardar(Docente d) {
		return this.docenteRepo.save(d); // borrar
	}
	
	public void borrar(Integer id) {
		this.docenteRepo.deleteById(id);
	}
	
	public Docente buscarPorId(Integer id) {
		Optional<Docente> resultado = this.docenteRepo.findById(id);
		return resultado.get();
	}

	public List<Docente> buscarTodos() {
		return this.docenteRepo.findAll();
	}

	public void asignarDocente(Integer idCurso, Integer idDocente) {
		Curso c =cursoService.buscarPorId(idCurso);
		Docente d1 = this.buscarPorId(idDocente);
		int cantidadDocentesAsignados = cursoService.docentesDelCurso(idCurso).size();
		
		if(c.getAntigMinima() < d1.getCreditos() && c.getCantMaxima() > cantidadDocentesAsignados) {
			d1.setCursoAsignado(c);
			this.docenteRepo.save(d1);
		}
	}

}

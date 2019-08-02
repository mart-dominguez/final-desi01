package tsti.desi.final01.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsti.desi.final01.dominio.Docente;
import tsti.desi.final01.dominio.Curso;
import tsti.desi.final01.repositorio.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepo;

	@Autowired
	private DocenteService docenteService;
	
	public Curso guardar(Curso d) {
		return this.cursoRepo.save(d);
	}
	
	public void borrar(Integer id) {
		this.cursoRepo.deleteById(id);
	}
	
	public Curso buscarPorId(Integer id) {
		Optional<Curso> resultado = this.cursoRepo.findById(id);
		return resultado.get();
	}

	public List<Curso> buscarTodos() {
		return this.cursoRepo.findAll();
	}
	
	public List<Docente> docentesDelCurso(Integer id){
		Optional<Curso> res = this.cursoRepo.findById(id);
		if(res.isPresent()) {
			return res.get().getDocentes();
		}
		return new ArrayList<>();
	}
	
}

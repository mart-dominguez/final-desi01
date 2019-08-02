package tsti.desi.final01.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import tsti.desi.final01.dominio.Curso;
import tsti.desi.final01.dominio.Docente;
import tsti.desi.final01.servicio.CursoService;

@RestController
@RequestMapping("/api")
public class CursoResource {
	
	@Autowired
	CursoService cursoService;
	
	@GetMapping("curso")
	public ResponseEntity<List<Curso>> buscar() {
		return  new ResponseEntity<List<Curso>>(this.cursoService.buscarTodos(), HttpStatus.OK);
	}

	
	@GetMapping("curso/{id}")
	public ResponseEntity<Curso> buscar(@PathVariable(value="id") Integer id) {
		return  new ResponseEntity<Curso>(this.cursoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@GetMapping("curso/{id}/docentes")
	public ResponseEntity<List<Docente>> buscarDocentes(@PathVariable(value="id") Integer id) {
		return  new ResponseEntity<List<Docente>>(this.cursoService.docentesDelCurso(id), HttpStatus.OK);
	}
		
	@PostMapping("curso")
	public ResponseEntity<Curso> crear(@RequestBody Curso p,UriComponentsBuilder builder) {
        Curso creado = this.cursoService.guardar(p);
        if (creado == null) {
        	return new ResponseEntity<Curso>(HttpStatus.NO_CONTENT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/api/proyecto/{id}").buildAndExpand(creado.getId()).toUri());
        return new ResponseEntity<Curso>(creado,headers, HttpStatus.CREATED);
	}
	
	@PutMapping("curso")
	public ResponseEntity<Curso> actualizar(@RequestBody Curso p,UriComponentsBuilder builder) {
        Curso actualizar = this.cursoService.guardar(p);
        return new ResponseEntity<Curso>(actualizar, HttpStatus.OK);
	}
	
	@DeleteMapping("curso/{id}")
	public ResponseEntity<Void> borrar(@PathVariable(value="id") Integer idCurso) {
        this.cursoService.borrar(idCurso);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

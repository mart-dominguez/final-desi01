package tsti.desi.final01.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import tsti.desi.final01.dominio.Curso;
import tsti.desi.final01.dominio.Docente;
import tsti.desi.final01.servicio.DocenteService;

@RestController
@RequestMapping("/api")
public class DocenteResource {

	
	@Autowired						// borrar
	DocenteService docenteService;  
	
	@GetMapping("docente")
	public ResponseEntity<List<Docente>> buscar() {
		return  new ResponseEntity<List<Docente>>(this.docenteService.buscarTodos(), HttpStatus.OK);
	}

	
	@GetMapping("docente/{id}")
	public ResponseEntity<Docente> buscar(@PathVariable(value="id") Integer id) {
		return  new ResponseEntity<Docente>(this.docenteService.buscarPorId(id), HttpStatus.OK);
	}
	
	@PostMapping("docente")
	public ResponseEntity<Docente> crear(@RequestBody Docente p,UriComponentsBuilder builder) {
        Docente creado = this.docenteService.guardar(p); // borrar
        if (creado == null) {
        	return new ResponseEntity<Docente>(HttpStatus.NO_CONTENT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/api/docente/{id}").buildAndExpand(creado.getId()).toUri());
        return new ResponseEntity<Docente>(creado,headers, HttpStatus.CREATED);
	}
	
	@PutMapping("docente")
	public ResponseEntity<Docente> actualizar(@RequestBody Docente p,UriComponentsBuilder builder) {
        Docente actualizar = this.docenteService.guardar(p);
        return new ResponseEntity<Docente>(actualizar, HttpStatus.OK);
	}
	
	@DeleteMapping("docente/{id}")
	public ResponseEntity<Void> borrar(@PathVariable(value="id") Integer idDocente) {
        this.docenteService.borrar(idDocente);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("docente/{idDocente}/curso")
	public ResponseEntity<Void> asignarDocentes(@PathVariable(value="idDocente") Integer idDocente,@RequestBody Curso curso) {
		this.docenteService.asignarDocente(curso.getId(), idDocente);
		return  new ResponseEntity<Void>(HttpStatus.OK);
	}

}

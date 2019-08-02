package tsti.desi.final01.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import tsti.desi.final01.dominio.Curso;
import tsti.desi.final01.dominio.Docente;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CursoResourceTest {
    @Autowired
    private TestRestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;
    
    private static final String SERVER_URL = "http://localhost";
	
    private String buildServerUrl(String path) {
    	System.out.println(SERVER_URL+ ":" + this.randomServerPort+ "/api/" + path);
    	return SERVER_URL+ ":" + this.randomServerPort+ "/api/" + path;
    }
    		
	@Test
	public void testAsignarDocente() {
		Docente d1= new Docente();
		d1.setNombre("martin");
		d1.setCreditos(5);
	    ResponseEntity<Docente> resultPost1 = restTemplate.postForEntity(buildServerUrl("docente"), d1, Docente.class);
	    assertEquals(d1.getNombre(), resultPost1.getBody().getNombre());
	    d1 =resultPost1.getBody();

	    Docente d2= new Docente();
		d2.setNombre("dardo");
		d2.setCreditos(10);
	    ResponseEntity<Docente> resultPost2 = restTemplate.postForEntity(buildServerUrl("docente"), d2, Docente.class);
	    assertEquals(d2.getNombre(), resultPost2.getBody().getNombre());
	    d2 =resultPost2.getBody();

		Docente d3= new Docente();
		d3.setNombre("juan");
		d3.setCreditos(7);
	    ResponseEntity<Docente> resultPost3 = restTemplate.postForEntity(buildServerUrl("docente"), d3, Docente.class);
	    assertEquals(d3.getNombre(), resultPost3.getBody().getNombre());
	    d3 =resultPost3.getBody();

		Curso c1 = new Curso();
		c1.setNombre("desi");
		c1.setAntigMinima(6);
		c1.setCantMaxima(1);
	    ResponseEntity<Curso> resultPost4 = restTemplate.postForEntity(buildServerUrl("curso"), c1, Curso.class);
	    assertEquals(c1.getNombre(), resultPost4.getBody().getNombre());
	    c1 =resultPost4.getBody();

		// cuantos docentes tiene asignados
	   //  ResponseEntity<List<Docente>> resultPost5 = restTemplate.getForEntity(buildServerUrl("curso/"+c1.getId()+"/docentes"),  new ParameterizedTypeReference<List<Docente>>() {});

	    ResponseEntity<List<Docente>> testTamanio = restTemplate.exchange(buildServerUrl("curso/"+c1.getId()+"/docentes"), HttpMethod.GET, null,new ParameterizedTypeReference<List<Docente>>() {});
	    assertNotNull(testTamanio.getBody());
	    assertEquals(0, testTamanio.getBody().size());
	    
	    ResponseEntity<String> resultPost6 = restTemplate.postForEntity(buildServerUrl("docente/"+d1.getId()+"/curso"), c1, String.class);
		assertThat(resultPost6.getStatusCode(), equalTo(HttpStatus.OK));	

		testTamanio = restTemplate.exchange(buildServerUrl("curso/"+c1.getId()+"/docentes"), HttpMethod.GET, null,new ParameterizedTypeReference<List<Docente>>() {});
	    assertNotNull(testTamanio.getBody());
	    assertEquals(0, testTamanio.getBody().size());
	    
	    ResponseEntity<String> resultPost7 = restTemplate.postForEntity(buildServerUrl("docente/"+d2.getId()+"/curso"), c1, String.class);
		assertThat(resultPost7.getStatusCode(), equalTo(HttpStatus.OK));	
		
		testTamanio = restTemplate.exchange(buildServerUrl("curso/"+c1.getId()+"/docentes"), HttpMethod.GET, null,new ParameterizedTypeReference<List<Docente>>() {});
	    assertNotNull(testTamanio.getBody());
	    assertEquals(1, testTamanio.getBody().size());
	    
	    ResponseEntity<String> resultPost8 = restTemplate.postForEntity(buildServerUrl("docente/"+d3.getId()+"/curso"), c1, String.class);
		assertThat(resultPost8.getStatusCode(), equalTo(HttpStatus.OK));	

		testTamanio = restTemplate.exchange(buildServerUrl("curso/"+c1.getId()+"/docentes"), HttpMethod.GET, null,new ParameterizedTypeReference<List<Docente>>() {});
	    assertNotNull(testTamanio.getBody());
	    assertEquals(1, testTamanio.getBody().size());
	}
}

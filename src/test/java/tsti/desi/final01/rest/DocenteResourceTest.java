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

import tsti.desi.final01.dominio.Docente;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DocenteResourceTest {
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
	public void testCrearBuscarDocente() {
		Docente d1= new Docente();
		d1.setNombre("martin");
		d1.setCreditos(5);
		 
	    ResponseEntity<Docente> resultPost = restTemplate.postForEntity(buildServerUrl("docente"), d1, Docente.class);
		d1 = resultPost.getBody();
	    System.out.println("*********************************************+"+resultPost);
	    
	  ResponseEntity<List<Docente>> testListaDocentes = restTemplate.exchange(buildServerUrl("docente"), HttpMethod.GET, null,new ParameterizedTypeReference<List<Docente>>() {});
	  System.out.println(".........+"+testListaDocentes.getBody());

		assertNotNull(testListaDocentes.getBody());
		Integer idEsperado = 1;
		assertEquals(idEsperado, testListaDocentes.getBody().get(0).getId());
	}
}

package tsti.desi.final01.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tsti.desi.final01.dominio.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Integer> {

}

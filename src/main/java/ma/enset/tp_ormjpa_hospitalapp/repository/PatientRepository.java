package ma.enset.tp_ormjpa_hospitalapp.repository;

import ma.enset.tp_ormjpa_hospitalapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByNomContains(String mc);
    @Query("select p from Patient p where p.nom like :x")
    List<Patient> search(@Param("x") String mc);
}

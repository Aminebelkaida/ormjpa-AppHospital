package ma.enset.tp_ormjpa_hospitalapp.repository;

import ma.enset.tp_ormjpa_hospitalapp.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    Medecin findByNom(String nom);

}

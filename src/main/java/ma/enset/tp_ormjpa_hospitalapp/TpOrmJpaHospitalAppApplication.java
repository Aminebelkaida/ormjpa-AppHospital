package ma.enset.tp_ormjpa_hospitalapp;

import ma.enset.tp_ormjpa_hospitalapp.entities.Patient;
import ma.enset.tp_ormjpa_hospitalapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TpOrmJpaHospitalAppApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {

        SpringApplication.run(TpOrmJpaHospitalAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Ahmed",new Date(),true, 120));
        patientRepository.save(new Patient(null,"Aya",new Date(),false, 100));
        patientRepository.save(new Patient(null,"Zayn",new Date(),true, 170));
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(p->{
            System.out.println(p.toString());
        });
        Patient patient=patientRepository.findById(Long.valueOf(1)).get();
        System.out.println("**************");
        System.out.println(patient.getId());
        System.out.println(patient.getNom());
        System.out.println(patient.getDateNaissance());
        System.out.println(patient.getScore());
        System.out.println("**************");
        System.out.println("----Afficher les noms des patients qui contient Z -----");
        List<Patient> patientList = patientRepository.findByNomContains("Z");
        patientList.forEach(p->{
            System.out.println(p);
        });
        System.out.println("----Afficher les noms des patients qui contient H  -----");
        List<Patient> patientList2 = patientRepository.search("%H%");
        patientList2.forEach(p-> {
            System.out.println(p);
        });

    }
}

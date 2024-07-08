package ma.enset.tp_ormjpa_hospitalapp;

import ma.enset.tp_ormjpa_hospitalapp.entities.*;
import ma.enset.tp_ormjpa_hospitalapp.repository.ConsultationRepository;
import ma.enset.tp_ormjpa_hospitalapp.repository.MedecinRepository;
import ma.enset.tp_ormjpa_hospitalapp.repository.PatientRepository;
import ma.enset.tp_ormjpa_hospitalapp.repository.RendezVousRepository;
import ma.enset.tp_ormjpa_hospitalapp.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/*@SpringBootApplication
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
}*/
//En utilisant L'annotation @Bean
@SpringBootApplication
public class TpOrmJpaHospitalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(TpOrmJpaHospitalAppApplication.class, args);

    }

    // En utilisant la couche service
    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository){
        return args ->{
            //patientRepository.save(new Patient(null, "Saad", new Date(),false, null))
            //Vaut mieux utiliser constructeur sans param
            Stream.of("Saad", "Houda", "Mouad")
                    .forEach(name->{
                        Patient patient=new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        //patientRepository.save(patient);
                        hospitalService.savePatient(patient);

                    });
            Stream.of("Ahmed", "Hassan", "Meriem")
                    .forEach(name->{
                        Medecin medecin=new Medecin();
                        medecin.setNom(name);
                        medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        medecin.setEmail(name+"@gmail.com");
                        //medecinRepository.save(medecin);
                          hospitalService.saveMedecin(medecin);

                    });

            //Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient=patientRepository.findByNom("Saad");

            Medecin medecin=medecinRepository.findByNom("Hassan");

            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            RendezVous saveDRDV = hospitalService.saveRDV(rendezVous);

            //RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);

            RendezVous rendezVous1=rendezVousRepository.findAll().get(0);

            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation.....");
            hospitalService.saveConsultation(consultation);



        };

    }

}


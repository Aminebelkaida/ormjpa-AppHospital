package ma.enset.tp_ormjpa_hospitalapp.service;

import jakarta.transaction.Transactional;
import ma.enset.tp_ormjpa_hospitalapp.entities.Consultation;
import ma.enset.tp_ormjpa_hospitalapp.entities.Medecin;
import ma.enset.tp_ormjpa_hospitalapp.entities.Patient;
import ma.enset.tp_ormjpa_hospitalapp.entities.RendezVous;
import ma.enset.tp_ormjpa_hospitalapp.repository.ConsultationRepository;
import ma.enset.tp_ormjpa_hospitalapp.repository.MedecinRepository;
import ma.enset.tp_ormjpa_hospitalapp.repository.PatientRepository;
import ma.enset.tp_ormjpa_hospitalapp.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}

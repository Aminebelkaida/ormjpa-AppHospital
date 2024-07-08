package ma.enset.tp_ormjpa_hospitalapp.service;

import ma.enset.tp_ormjpa_hospitalapp.entities.Consultation;
import ma.enset.tp_ormjpa_hospitalapp.entities.Medecin;
import ma.enset.tp_ormjpa_hospitalapp.entities.Patient;
import ma.enset.tp_ormjpa_hospitalapp.entities.RendezVous;

public interface IHospitalService {

    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);


}

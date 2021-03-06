package com.douwe.notes.web.beans;

import com.douwe.notes.entities.AnneeAcademique;
import com.douwe.notes.entities.Departement;
import com.douwe.notes.entities.Etudiant;
import com.douwe.notes.entities.Genre;
import com.douwe.notes.entities.Inscription;
import com.douwe.notes.entities.Niveau;
import com.douwe.notes.entities.Option;
import com.douwe.notes.service.IAnneeAcademiqueService;
import com.douwe.notes.service.IDepartementService;
import com.douwe.notes.service.IEtudiantService;
import com.douwe.notes.service.IInscriptionService;
import com.douwe.notes.service.INiveauService;
import com.douwe.notes.service.IOptionService;
import com.douwe.notes.service.ServiceException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Vincent Douwe <douwevincent@yahoo.fr>
 */
@Named(value = "etudiantBean")
@RequestScoped
public class EtudiantBean {

    private Departement departement;

    private AnneeAcademique annee;

    private Niveau niveau;

    private Option option;

    @EJB
    private IEtudiantService etudiantService;

    @EJB
    private INiveauService niveauService;

    @EJB
    private IAnneeAcademiqueService anneeAcademiqueService;

    @EJB
    private IOptionService optionService;

    @EJB
    private IDepartementService departementService;

    @EJB
    private IInscriptionService inscriptionService;

    private List<Etudiant> etudiants = new LinkedList<Etudiant>();

    private List<Niveau> niveaus;
    private List<Option> options;
    private List<AnneeAcademique> anneeAcademiques;
    private List<Departement> departements;
    private Etudiant etudiant = new Etudiant();
    private Inscription inscription;
    private AnneeAcademique anneeAcademique;
    private Genre genre;
    private Map<String, Long> countries = new HashMap<String, Long>();
    private Map<String, Long> cities = null;
    private List<String> genres = new LinkedList<String>();
    Long idD = -1L, idN = -1L, idO = -1L, idA = -1L;
    
    /**
     * Creates a new instance of EtudiantBean
     */
    public EtudiantBean() {
        etudiant = new Etudiant();
    }

    public void filtrer() throws ServiceException {
        etudiants = new LinkedList<Etudiant>();
        etudiants = etudiantService.findByCritiria((idD == null) ? -1 : idD,
                (idA == null) ? -1 : idA,
                (idN == null) ? -1 : idN,
                (idO == null) ? -1 : idO);     
       
    }

    public void saveOrUpdateEtudiant(ActionEvent actionEvent) throws ServiceException {
        if (etudiant != null && etudiant.getId() != null) {
            etudiantService.saveOrUpdateEtudiant(etudiant);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation reussie", "l'étudiant " + etudiant.getNom() + " a été mis à jour "));
            etudiant = new Etudiant();
        }
    }


    public void deleteEtudiant(ActionEvent actionEvent) throws ServiceException {
        if (etudiant != null && etudiant.getId() != null) {
            etudiantService.deleteEtudiant(etudiant.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation reussie", "l'étudiant " + etudiant.getNom() + " a été déactivé"));
            etudiant = new Etudiant();
        }
    }

    public void verifierEtUpdate(ActionEvent actionEvent) throws ServiceException {
        if (etudiant != null && etudiant.getId() != null) {
            RequestContext.getCurrentInstance().execute("PF('dlgUpdate').show()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Attention", "selectionner un etudiant avant de modifier "));
        }
    }

    public void verifierEtSupprimer(ActionEvent actionEvent) throws ServiceException {
        if (etudiant != null && etudiant.getId() != null) {
            RequestContext.getCurrentInstance().execute("PF('confirmation').show()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Attention", "selectionner un etudiant avant de supprimer "));
        }
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;

    }

    public AnneeAcademique getAnnee() {
        return annee;
    }

    public void setAnnee(AnneeAcademique annee) {
        this.annee = annee;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public IEtudiantService getEtudiantService() {
        return etudiantService;
    }

    public void setEtudiantService(IEtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    public List<Etudiant> getEtudiants() throws ServiceException {             
       // filtrer();
        return etudiants;
    }

    public INiveauService getNiveauService() {
        return niveauService;
    }

    public void setNiveauService(INiveauService niveauService) {
        this.niveauService = niveauService;
    }

    public IAnneeAcademiqueService getAnneeAcademiqueService() {
        return anneeAcademiqueService;
    }

    public void setAnneeAcademiqueService(IAnneeAcademiqueService anneeAcademiqueService) {
        this.anneeAcademiqueService = anneeAcademiqueService;
    }

    public IOptionService getOptionService() {
        return optionService;
    }

    public void setOptionService(IOptionService optionService) {
        this.optionService = optionService;
    }

    public IDepartementService getDepartementService() {
        return departementService;
    }

    public void setDepartementService(IDepartementService departementService) {
        this.departementService = departementService;
    }

    public List<Niveau> getNiveaus() throws ServiceException {
        niveaus = niveauService.getAllNiveaux();
        return niveaus;
    }

    public void setNiveaus(List<Niveau> niveaus) {
        this.niveaus = niveaus;
    }

    public List<Option> getOptions() throws ServiceException {
        options = optionService.getAllOptions();
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<AnneeAcademique> getAnneeAcademiques() throws ServiceException {
        anneeAcademiques = anneeAcademiqueService.getAllAnnee();
        return anneeAcademiques;
    }

    public void setAnneeAcademiques(List<AnneeAcademique> anneeAcademiques) {
        this.anneeAcademiques = anneeAcademiques;
    }

    public List<Departement> getDepartements() throws ServiceException {
        departements = departementService.getAllDepartements();
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public Long getIdD() {
        return idD;
    }

    public void setIdD(Long idD) {
        this.idD = idD;
    }

    public Long getIdN() {
        return idN;
    }

    public void setIdN(Long idN) {
        this.idN = idN;
    }

    public Long getIdO() {
        return idO;
    }

    public void setIdO(Long idO) {
        this.idO = idO;
    }

    public Long getIdA() {
        return idA;
    }

    public void setIdA(Long idA) {
        this.idA = idA;
    }

    public Etudiant getEtudiant() throws ServiceException {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    public List<String> getGenres() {
        genres.add("feminin");
        genres.add("masculin");
        return genres;
    }
    public void util(){}
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public IInscriptionService getInscriptionService() {
        return inscriptionService;
    }

    public void setInscriptionService(IInscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    public void handleCountryChange() throws ServiceException {
        if (idD != null && idD != 0L) {
            Departement d = departementService.findDepartementById(idD);
            options = departementService.getAllOptions(d);
            cities = new HashMap<String, Long>();
            for (Option opt : options) {
                cities.put(opt.getCode(), opt.getId());
            }
        } else {
            cities = null;
        }
    }

    public Map<String, Long> getCountries() throws ServiceException {
        departements = departementService.getAllDepartements();
        for (Departement d : departements) {
            countries.put(d.getCode(), d.getId());
        }
        return countries;
    }

    public void setCountries(Map<String, Long> countries) {
        this.countries = countries;
    }

    public Map<String, Long> getCities() throws ServiceException {
        if (cities == null) {
            cities = new HashMap<String, Long>();
            options = optionService.getAllOptions();
            for (Option opt : options) {
                cities.put(opt.getCode(), opt.getId());
            }
        }

        return cities;
    }

    public void setCities(Map<String, Long> cities) {
        this.cities = cities;
    }
}

package services;

import dao.AdvisorDAO;
import dao.ClientDAO;
import models.Advisor;
import models.Client;

import java.util.List;
import java.util.Optional;

public class AdvisorService {
    private final AdvisorDAO advisorDAO = new AdvisorDAO();

    public Advisor addAdvisor(String firstName, String lastName, String email) throws Exception {
        List<Advisor> advisors = advisorDAO.findAll();
        boolean exists = advisors.stream()
                .anyMatch(a -> a.getEmail().equalsIgnoreCase(email));
        if (exists) throw new Exception("Email already exists!");

        Advisor advisor = new Advisor();
        advisor.setFirstName(firstName);
        advisor.setLastName(lastName);
        advisor.setEmail(email);

        return advisorDAO.save(advisor);
    }
    public boolean deleteAdvisorById(int id) throws Exception {
        Optional<Advisor> advisorOpt = advisorDAO.findById(id);
        if (!advisorOpt.isPresent()) {
            return false;
        }
        return advisorDAO.deleteById(id);
    }
    public Optional<Advisor> searchAdvisorById(int id) throws Exception {
        return advisorDAO.findById(id);
    }

    public List<Client> getClientsOfAdvisor(int advisorId) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findByAdvisorId(advisorId);
    }
}

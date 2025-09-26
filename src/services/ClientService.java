package services;

import models.Client;
import models.Advisor;
import dao.ClientDAO;
import dao.AdvisorDAO;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ClientService {
    private final ClientDAO clientDAO = new ClientDAO();
    private final AdvisorDAO advisorDAO = new AdvisorDAO();

    public Client addClient(String firstName, String lastName, String email, int advisorId) throws Exception {
        Advisor advisor = advisorDAO.findById(advisorId)
                .orElseThrow(() -> new Exception("Advisor not found"));
        boolean exists = clientDAO.findAll().stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
        if (exists) throw new Exception("Email already exists!");
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setAdvisor(advisor);
        return clientDAO.save(client);
    }

    public boolean deleteClientById(int id) throws Exception {
        Optional<Client> clientOpt = clientDAO.findById(id);
        if (!clientOpt.isPresent()) return false;
        return clientDAO.deleteById(id);
    }

    public List<Client> searchClientByLastNameAndSort(String lastName) throws Exception {
        return clientDAO.findAll().stream()
                .filter(c -> c.getLastName().equalsIgnoreCase(lastName))
                .sorted(Comparator.comparing(Client::getFirstName))
                .collect(Collectors.toList());
    }

    public Optional<Client> searchClientById(int id) throws Exception {
        return clientDAO.findById(id);
    }

    public List<Client> showClientsOfAdvisor(int advisorId) throws Exception {
        return clientDAO.findByAdvisorId(advisorId);
    }
}
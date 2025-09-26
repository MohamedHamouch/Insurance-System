package controllers;

import services.ClientService;
import models.Client;
import java.util.List;
import java.util.Optional;

public class ClientController {
    private final ClientService clientService = new ClientService();

    public void addClient(String firstName, String lastName, String email, int advisorId) {
        try {
            Client client = clientService.addClient(firstName, lastName, email, advisorId);
            System.out.println("Client added successfully! ID: " + client.getId());
        } catch (Exception e) {
            System.out.println("Failed to add client: " + e.getMessage());
        }
    }

    public void deleteClientById(int id) {
        try {
            boolean success = clientService.deleteClientById(id);
            System.out.println(success ? "Client deleted." : "No client found with that ID.");
        } catch (Exception e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }

    public void searchClientByLastNameAndSort(String lastName) {
        try {
            List<Client> clients = clientService.searchClientByLastNameAndSort(lastName);
            if (clients.isEmpty()) {
                System.out.println("No clients found with last name: " + lastName);
            } else {
                clients.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error searching clients: " + e.getMessage());
        }
    }

    public void searchClientById(int id) {
        try {
            Optional<Client> clientOpt = clientService.searchClientById(id);
            if (clientOpt.isPresent()) {
                System.out.println(clientOpt.get());
            } else {
                System.out.println("No client found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error searching client: " + e.getMessage());
        }
    }

    public void showClientsOfAdvisor(int advisorId) {
        try {
            List<Client> clients = clientService.showClientsOfAdvisor(advisorId);
            if (clients.isEmpty()) {
                System.out.println("No clients found for advisor ID: " + advisorId);
            } else {
                clients.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching clients: " + e.getMessage());
        }
    }
}
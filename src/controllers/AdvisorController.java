package controllers;

import models.Advisor;
import models.Client;
import services.AdvisorService;

import java.util.List;
import java.util.Optional;

public class AdvisorController {
    private final AdvisorService advisorService = new AdvisorService();

    public void addAdvisor(String firstName, String lastName, String email) {
        try {
            Advisor advisor = advisorService.addAdvisor(firstName, lastName, email);
            System.out.println("Advisor added successfully! ID: " + advisor.getId());
        } catch (Exception e) {
            System.out.println("Failed to add advisor: " + e.getMessage());
        }
    }

    public void deleteAdvisorById(int id) {
        try {
            boolean success = advisorService.deleteAdvisorById(id);
            if (success) {
                System.out.println("Advisor deleted successfully.");
            } else {
                System.out.println("No advisor found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error deleting advisor: " + e.getMessage());
        }
    }
    public void searchAdvisorById(int id) {
        try {
            Optional<Advisor> advisorOpt = advisorService.searchAdvisorById(id);
            if (advisorOpt.isPresent()) {
                Advisor advisor = advisorOpt.get();
                System.out.println("Advisor found:");
                System.out.println(advisor);
            } else {
                System.out.println("No advisor found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error searching advisor: " + e.getMessage());
        }
    }
    public void showClientsOfAdvisor(int advisorId) {
        try {
            List<Client> clients = advisorService.getClientsOfAdvisor(advisorId);
            if (clients.isEmpty()) {
                System.out.println("No clients found for advisor ID: " + advisorId);
            } else {
                System.out.println("Clients of advisor ID " + advisorId + ":");
                clients.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching clients: " + e.getMessage());
        }
    }
}

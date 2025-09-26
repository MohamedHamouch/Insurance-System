package views;

import helpers.InputHelper;
import controllers.AdvisorController;

public class AdvisorView {
    private final AdvisorController advisorController = new AdvisorController();

    public void addAdvisor() {
        String firstName = InputHelper.getString("Enter first name: ");
        String lastName = InputHelper.getString("Enter last name: ");
        String email = InputHelper.getEmail("Enter email: ");
        advisorController.addAdvisor(firstName, lastName, email);
    }

    public void deleteAdvisorById() {
        int id = InputHelper.getInt("Enter advisor ID to delete: ");
        advisorController.deleteAdvisorById(id);
    }

    public void searchAdvisorById() {
        int id = InputHelper.getInt("Enter advisor ID to search: ");
        advisorController.searchAdvisorById(id);
    }

    public void showClientsOfAdvisor() {
        int advisorId = InputHelper.getInt("Enter advisor ID: ");
        advisorController.showClientsOfAdvisor(advisorId);
    }
}
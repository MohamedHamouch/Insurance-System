package views;

import controllers.ClientController;
import helpers.InputHelper;

public class ClientView {
    private final ClientController clientController = new ClientController();

    public void addClient() {
        String firstName = InputHelper.getString("Enter first name: ");
        String lastName = InputHelper.getString("Enter last name: ");
        String email = InputHelper.getEmail("Enter email: ");
        int advisorId = InputHelper.getInt("Enter advisor ID: ");
        clientController.addClient(firstName, lastName, email, advisorId);
    }

    public void deleteClientById() {
        int id = InputHelper.getInt("Enter client ID to delete: ");
        clientController.deleteClientById(id);
    }

    public void searchClientByLastNameAndSort() {
        String lastName = InputHelper.getString("Enter last name to search: ");
        clientController.searchClientByLastNameAndSort(lastName);
    }

    public void searchClientById() {
        int id = InputHelper.getInt("Enter client ID to search: ");
        clientController.searchClientById(id);
    }

    public void showClientsOfAdvisor() {
        int advisorId = InputHelper.getInt("Enter advisor ID: ");
        clientController.showClientsOfAdvisor(advisorId);
    }
}
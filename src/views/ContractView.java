package views;

import helpers.InputHelper;
import controllers.ContractController;

public class ContractView {
    private final ContractController contractController = new ContractController();

    public void addContract() {
        int clientId = InputHelper.getInt("Enter client ID: ");
        String contractType = InputHelper.getString("Enter contract type (CAR/HOME/HEALTH): ");
        String startDate = InputHelper.getDateInput("Enter start date (YYYY-MM-DD): ");
        String endDate = InputHelper.getDateInput("Enter end date (YYYY-MM-DD): ");
        contractController.addContract(clientId, contractType, startDate, endDate);
    }

    public void showContractInfoById() {
        int contractId = InputHelper.getInt("Enter contract ID: ");
        contractController.showContractInfoById(contractId);
    }

    public void deleteContractById() {
        int contractId = InputHelper.getInt("Enter contract ID to delete: ");
        contractController.deleteContractById(contractId);
    }

    public void showContractsOfClient() {
        int clientId = InputHelper.getInt("Enter client ID: ");
        contractController.showContractsOfClient(clientId);
    }
}
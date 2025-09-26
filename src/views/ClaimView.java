package views;

import helpers.InputHelper;
import controllers.ClaimController;

public class ClaimView {
    private final ClaimController claimController = new ClaimController();

    public void addClaim() {
        int contractId = InputHelper.getInt("Enter contract ID: ");
        String claimType = InputHelper.getString("Enter claim type (CAR/HOME/HEALTH): ");
        String description = InputHelper.getString("Enter description: ");
        double cost = InputHelper.getDouble("Enter cost: ");
        claimController.addClaim(contractId, claimType, description, cost);
    }

    public void deleteClaimById() {
        int id = InputHelper.getInt("Enter claim ID to delete: ");
        claimController.deleteClaimById(id);
    }

    public void calculateTotalCostForClient() {
        int clientId = InputHelper.getInt("Enter client ID: ");
        claimController.calculateTotalCostForClient(clientId);
    }

    public void searchClaimById() {
        int id = InputHelper.getInt("Enter claim ID to search: ");
        claimController.searchClaimById(id);
    }

    public void showClaimsOfContract() {
        int contractId = InputHelper.getInt("Enter contract ID: ");
        claimController.showClaimsOfContract(contractId);
    }

    public void showClaimsSortedByCostDesc() {
        claimController.showClaimsSortedByCostDesc();
    }

    public void showClaimsByClientId() {
        int clientId = InputHelper.getInt("Enter client ID: ");
        claimController.showClaimsByClientId(clientId);
    }

    public void showClaimsBeforeDate() {
        String date = InputHelper.getString("Enter date (YYYY-MM-DD): ");
        claimController.showClaimsBeforeDate(date);
    }

    public void showClaimsWithCostGreaterThan() {
        double amount = InputHelper.getDouble("Enter minimum cost amount: ");
        claimController.showClaimsWithCostGreaterThan(amount);
    }
}
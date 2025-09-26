package controllers;

import services.ClaimService;
import models.Claim;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ClaimController {
    private final ClaimService claimService = new ClaimService();

    public void addClaim(int contractId, String claimType, String description, double cost) {
        try {
            Claim claim = claimService.addClaim(contractId, claimType, description, cost);
            System.out.println("Claim added successfully! ID: " + claim.getId());
        } catch (Exception e) {
            System.out.println("Failed to add claim: " + e.getMessage());
        }
    }

    public void deleteClaimById(int id) {
        try {
            boolean success = claimService.deleteClaimById(id);
            System.out.println(success ? "Claim deleted." : "No claim found with that ID.");
        } catch (Exception e) {
            System.out.println("Error deleting claim: " + e.getMessage());
        }
    }

    public void calculateTotalCostForClient(int clientId) {
        try {
            BigDecimal total = claimService.calculateTotalCostForClient(clientId);
            System.out.println("Total cost of claims for client ID " + clientId + ": " + total);
        } catch (Exception e) {
            System.out.println("Error calculating total cost: " + e.getMessage());
        }
    }

    public void searchClaimById(int id) {
        try {
            Optional<Claim> claimOpt = claimService.searchClaimById(id);
            if (claimOpt.isPresent()) {
                System.out.println("Claim found: " + claimOpt.get());
            } else {
                System.out.println("No claim found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error searching claim: " + e.getMessage());
        }
    }

    public void showClaimsOfContract(int contractId) {
        try {
            List<Claim> claims = claimService.showClaimsOfContract(contractId);
            if (claims.isEmpty()) {
                System.out.println("No claims found for contract ID: " + contractId);
            } else {
                claims.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching claims: " + e.getMessage());
        }
    }

    public void showClaimsSortedByCostDesc() {
        try {
            List<Claim> claims = claimService.showClaimsSortedByCostDesc();
            if (claims.isEmpty()) {
                System.out.println("No claims found.");
            } else {
                claims.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching claims: " + e.getMessage());
        }
    }

    public void showClaimsByClientId(int clientId) {
        try {
            List<Claim> claims = claimService.showClaimsByClientId(clientId);
            if (claims.isEmpty()) {
                System.out.println("No claims found for client ID: " + clientId);
            } else {
                claims.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching claims: " + e.getMessage());
        }
    }

    public void showClaimsBeforeDate(String dateStr) {
        try {
            List<Claim> claims = claimService.showClaimsBeforeDate(dateStr);
            if (claims.isEmpty()) {
                System.out.println("No claims found before date: " + dateStr);
            } else {
                claims.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching claims: " + e.getMessage());
        }
    }

    public void showClaimsWithCostGreaterThan(double amount) {
        try {
            List<Claim> claims = claimService.showClaimsWithCostGreaterThan(amount);
            if (claims.isEmpty()) {
                System.out.println("No claims found with cost greater than: " + amount);
            } else {
                claims.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching claims: " + e.getMessage());
        }
    }
}
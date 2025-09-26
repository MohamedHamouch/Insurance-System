package controllers;

import services.ContractService;
import models.Contract;
import java.util.List;
import java.util.Optional;

public class ContractController {
    private final ContractService contractService = new ContractService();

    public void addContract(int clientId, String contractType, String startDate, String endDate) {
        try {
            Contract contract = contractService.addContract(clientId, contractType, startDate, endDate);
            System.out.println("Contract added successfully! ID: " + contract.getId());
        } catch (Exception e) {
            System.out.println("Failed to add contract: " + e.getMessage());
        }
    }

    public void showContractInfoById(int contractId) {
        try {
            Optional<Contract> contractOpt = contractService.showContractInfoById(contractId);
            if (contractOpt.isPresent()) {
                System.out.println(contractOpt.get());
            } else {
                System.out.println("No contract found with ID: " + contractId);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving contract info: " + e.getMessage());
        }
    }

    public void deleteContractById(int contractId) {
        try {
            boolean success = contractService.deleteContractById(contractId);
            System.out.println(success ? "Contract deleted." : "No contract found with that ID.");
        } catch (Exception e) {
            System.out.println("Error deleting contract: " + e.getMessage());
        }
    }

    public void showContractsOfClient(int clientId) {
        try {
            List<Contract> contracts = contractService.showContractsOfClient(clientId);
            if (contracts.isEmpty()) {
                System.out.println("No contracts found for client ID: " + clientId);
            } else {
                contracts.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching contracts: " + e.getMessage());
        }
    }
}
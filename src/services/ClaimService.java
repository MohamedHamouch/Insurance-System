package services;

import models.Claim;
import models.Contract;
import dao.ClaimDAO;
import dao.ContractDAO;
import enums.ClaimType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

public class ClaimService {
    private final ClaimDAO claimDAO = new ClaimDAO();
    private final ContractDAO contractDAO = new ContractDAO();

    public Claim addClaim(int contractId, String claimTypeStr, String description, double cost) throws Exception {
        Contract contract = contractDAO.findById(contractId)
                .orElseThrow(() -> new Exception("Contract not found"));

        ClaimType claimType;
        try {
            claimType = ClaimType.valueOf(claimTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Invalid claim type: " + claimTypeStr);
        }

        Claim claim = new Claim();
        claim.setContract(contract);
        claim.setClaimType(claimType);
        claim.setDescription(description);
        claim.setCost(BigDecimal.valueOf(cost));
        claim.setDateTime(LocalDateTime.now());

        return claimDAO.save(claim);
    }

    public boolean deleteClaimById(int id) throws Exception {
        Optional<Claim> claimOpt = claimDAO.findById(id);
        if (!claimOpt.isPresent()) return false;
        return claimDAO.deleteById(id);
    }

    public BigDecimal calculateTotalCostForClient(int clientId) throws Exception {
        return claimDAO.findAll().stream()
                .filter(claim -> claim.getContract().getClient().getId().equals(clientId))
                .map(Claim::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Optional<Claim> searchClaimById(int id) throws Exception {
        return claimDAO.findById(id);
    }

    public List<Claim> showClaimsOfContract(int contractId) throws Exception {
        return claimDAO.findByContractId(contractId);
    }

    public List<Claim> showClaimsSortedByCostDesc() throws Exception {
        return claimDAO.findAll().stream()
                .sorted(Comparator.comparing(Claim::getCost).reversed())
                .collect(Collectors.toList());
    }

    public List<Claim> showClaimsByClientId(int clientId) throws Exception {
        return claimDAO.findAll().stream()
                .filter(claim -> claim.getContract().getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    public List<Claim> showClaimsBeforeDate(String dateStr) throws Exception {
        LocalDate targetDate = LocalDate.parse(dateStr);
        return claimDAO.findAll().stream()
                .filter(claim -> claim.getDateTime().toLocalDate().isBefore(targetDate))
                .collect(Collectors.toList());
    }

    public List<Claim> showClaimsWithCostGreaterThan(double amount) throws Exception {
        BigDecimal targetAmount = BigDecimal.valueOf(amount);
        return claimDAO.findAll().stream()
                .filter(claim -> claim.getCost().compareTo(targetAmount) > 0)
                .collect(Collectors.toList());
    }
}
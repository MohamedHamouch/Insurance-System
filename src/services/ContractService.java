package services;

import models.Contract;
import models.Client;
import dao.ContractDAO;
import dao.ClientDAO;
import enums.ContractType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ContractService {
    private final ContractDAO contractDAO = new ContractDAO();
    private final ClientDAO clientDAO = new ClientDAO();

    public Contract addContract(int clientId, String contractTypeStr, String startDateStr, String endDateStr) throws Exception {
        Client client = clientDAO.findById(clientId)
                .orElseThrow(() -> new Exception("Client not found"));
        ContractType contractType;
        try {
            contractType = ContractType.valueOf(contractTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Invalid contract type: " + contractTypeStr);
        }
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        Contract contract = new Contract();
        contract.setClient(client);
        contract.setContractType(contractType);
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);

        return contractDAO.save(contract);
    }

    public Optional<Contract> showContractInfoById(int contractId) throws Exception {
        return contractDAO.findById(contractId);
    }

    public boolean deleteContractById(int contractId) throws Exception {
        Optional<Contract> contractOpt = contractDAO.findById(contractId);
        if (!contractOpt.isPresent()) return false;
        return contractDAO.deleteById(contractId);
    }

    public List<Contract> showContractsOfClient(int clientId) throws Exception {
        return contractDAO.findByClientId(clientId);
    }
}
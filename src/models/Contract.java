package models;

import enums.ContractType;

import java.time.LocalDate;

public class Contract {
    private Integer id;
    private ContractType contractType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Client client;

    public Contract() {
    }

    public Contract(Integer id, ContractType contractType, LocalDate startDate, LocalDate endDate, Client client) {
        this.id = id;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Type: " + contractType +
                ", Start: " + startDate +
                ", End: " + endDate +
                ", Client: " + client.getFirstName() + " " + client.getLastName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

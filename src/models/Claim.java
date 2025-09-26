package models;

import enums.ClaimType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Claim {
    private Integer id;
    private ClaimType claimType;
    private LocalDateTime dateTime;
    private BigDecimal cost;
    private String description;
    private Contract contract;

    public Claim() {}

    public Claim(Integer id, ClaimType claimType, LocalDateTime dateTime, BigDecimal cost, String description, Contract contract) {
        this.id = id;
        this.claimType = claimType;
        this.dateTime = dateTime;
        this.cost = cost;
        this.description = description;
        this.contract = contract;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public ClaimType getClaimType() { return claimType; }
    public void setClaimType(ClaimType claimType) { this.claimType = claimType; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }
}

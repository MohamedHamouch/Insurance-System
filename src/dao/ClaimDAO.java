package dao;

import db.DatabaseConnection;
import models.Claim;
import models.Contract;
import enums.ClaimType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClaimDAO {

    public Claim save(Claim claim) throws SQLException {
        String sql = "INSERT INTO claims (claimType, dateTime, cost, description, contractId) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, claim.getClaimType().name());
            stmt.setTimestamp(2, Timestamp.valueOf(claim.getDateTime()));
            stmt.setBigDecimal(3, claim.getCost());
            stmt.setString(4, claim.getDescription());
            stmt.setInt(5, claim.getContract().getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating claim failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    claim.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating claim failed, no ID obtained.");
                }
            }
        }
        return claim;
    }

    public Optional<Claim> findById(int id) throws SQLException {
        String sql = "SELECT * FROM claims WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Claim claim = new Claim();
                    claim.setId(rs.getInt("id"));
                    claim.setClaimType(ClaimType.valueOf(rs.getString("claimType")));
                    claim.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
                    claim.setCost(rs.getBigDecimal("cost"));
                    claim.setDescription(rs.getString("description"));

                    int contractId = rs.getInt("contractId");
                    ContractDAO contractDAO = new ContractDAO();
                    contractDAO.findById(contractId).ifPresent(claim::setContract);

                    return Optional.of(claim);
                }
            }
        }
        return Optional.empty();
    }

    public List<Claim> findAll() throws SQLException {
        List<Claim> claims = new ArrayList<>();
        String sql = "SELECT * FROM claims";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ContractDAO contractDAO = new ContractDAO();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setId(rs.getInt("id"));
                claim.setClaimType(ClaimType.valueOf(rs.getString("claimType")));
                claim.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
                claim.setCost(rs.getBigDecimal("cost"));
                claim.setDescription(rs.getString("description"));

                int contractId = rs.getInt("contractId");
                contractDAO.findById(contractId).ifPresent(claim::setContract);

                claims.add(claim);
            }
        }
        return claims;
    }

    public List<Claim> findByContractId(int contractId) throws SQLException {
        List<Claim> claims = new ArrayList<>();
        String sql = "SELECT * FROM claims WHERE contractId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, contractId);
            ContractDAO contractDAO = new ContractDAO();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Claim claim = new Claim();
                    claim.setId(rs.getInt("id"));
                    claim.setClaimType(ClaimType.valueOf(rs.getString("claimType")));
                    claim.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
                    claim.setCost(rs.getBigDecimal("cost"));
                    claim.setDescription(rs.getString("description"));

                    contractDAO.findById(contractId).ifPresent(claim::setContract);

                    claims.add(claim);
                }
            }
        }
        return claims;
    }

    public Claim update(Claim claim) throws SQLException {
        String sql = "UPDATE claims SET claimType = ?, dateTime = ?, cost = ?, description = ?, contractId = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, claim.getClaimType().name());
            stmt.setTimestamp(2, Timestamp.valueOf(claim.getDateTime()));
            stmt.setBigDecimal(3, claim.getCost());
            stmt.setString(4, claim.getDescription());
            stmt.setInt(5, claim.getContract().getId());
            stmt.setInt(6, claim.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating claim failed, no rows affected.");
            }
        }
        return claim;
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM claims WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
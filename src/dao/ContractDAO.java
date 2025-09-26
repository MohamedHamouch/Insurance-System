package dao;

import db.DatabaseConnection;
import models.Contract;
import models.Client;
import enums.ContractType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContractDAO {

    public Contract save(Contract contract) throws SQLException {
        String sql = "INSERT INTO contracts (contractType, startDate, endDate, clientId) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, contract.getContractType().name());
            stmt.setDate(2, Date.valueOf(contract.getStartDate()));
            stmt.setDate(3, Date.valueOf(contract.getEndDate()));
            stmt.setInt(4, contract.getClient().getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating contract failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contract.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating contract failed, no ID obtained.");
                }
            }
        }
        return contract;
    }

    public Optional<Contract> findById(int id) throws SQLException {
        String sql = "SELECT * FROM contracts WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Contract contract = new Contract();
                    contract.setId(rs.getInt("id"));
                    contract.setContractType(ContractType.valueOf(rs.getString("contractType")));
                    contract.setStartDate(rs.getDate("startDate").toLocalDate());
                    contract.setEndDate(rs.getDate("endDate").toLocalDate());

                    int clientId = rs.getInt("clientId");
                    ClientDAO clientDAO = new ClientDAO();
                    clientDAO.findById(clientId).ifPresent(contract::setClient);

                    return Optional.of(contract);
                }
            }
        }
        return Optional.empty();
    }

    public List<Contract> findAll() throws SQLException {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM contracts";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ClientDAO clientDAO = new ClientDAO();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setContractType(ContractType.valueOf(rs.getString("contractType")));
                contract.setStartDate(rs.getDate("startDate").toLocalDate());
                contract.setEndDate(rs.getDate("endDate").toLocalDate());

                int clientId = rs.getInt("clientId");
                clientDAO.findById(clientId).ifPresent(contract::setClient);

                contracts.add(contract);
            }
        }
        return contracts;
    }

    public List<Contract> findByClientId(int clientId) throws SQLException {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM contracts WHERE clientId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            ClientDAO clientDAO = new ClientDAO();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Contract contract = new Contract();
                    contract.setId(rs.getInt("id"));
                    contract.setContractType(ContractType.valueOf(rs.getString("contractType")));
                    contract.setStartDate(rs.getDate("startDate").toLocalDate());
                    contract.setEndDate(rs.getDate("endDate").toLocalDate());

                    clientDAO.findById(clientId).ifPresent(contract::setClient);

                    contracts.add(contract);
                }
            }
        }
        return contracts;
    }

    public Contract update(Contract contract) throws SQLException {
        String sql = "UPDATE contracts SET contractType = ?, startDate = ?, endDate = ?, clientId = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, contract.getContractType().name());
            stmt.setDate(2, Date.valueOf(contract.getStartDate()));
            stmt.setDate(3, Date.valueOf(contract.getEndDate()));
            stmt.setInt(4, contract.getClient().getId());
            stmt.setInt(5, contract.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating contract failed, no rows affected.");
            }
        }
        return contract;
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM contracts WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
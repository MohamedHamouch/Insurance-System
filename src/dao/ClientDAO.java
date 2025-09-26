package dao;

import db.DatabaseConnection;
import models.Client;
import models.Advisor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO {

    public Client save(Client client) throws SQLException {
        String sql = "INSERT INTO clients (firstName, lastName, email, advisorId) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setString(3, client.getEmail());
            stmt.setInt(4, client.getAdvisor().getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
        }
        return client;
    }

    public Optional<Client> findById(int id) throws SQLException {
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setFirstName(rs.getString("firstName"));
                    client.setLastName(rs.getString("lastName"));
                    client.setEmail(rs.getString("email"));

                    int advisorId = rs.getInt("advisorId");
                    AdvisorDAO advisorDAO = new AdvisorDAO();
                    advisorDAO.findById(advisorId).ifPresent(client::setAdvisor);
                    return Optional.of(client);
                }
            }
        }
        return Optional.empty();
    }

    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            AdvisorDAO advisorDAO = new AdvisorDAO();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setEmail(rs.getString("email"));

                int advisorId = rs.getInt("advisorId");
                advisorDAO.findById(advisorId).ifPresent(client::setAdvisor);
                clients.add(client);
            }
        }
        return clients;
    }

    public List<Client> findByAdvisorId(int advisorId) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE advisorId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, advisorId);
            AdvisorDAO advisorDAO = new AdvisorDAO();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setFirstName(rs.getString("firstName"));
                    client.setLastName(rs.getString("lastName"));
                    client.setEmail(rs.getString("email"));

                    advisorDAO.findById(advisorId).ifPresent(client::setAdvisor);
                    clients.add(client);
                }
            }
        }
        return clients;
    }

    public Client update(Client client) throws SQLException {
        String sql = "UPDATE clients SET firstName = ?, lastName = ?, email = ?, advisorId = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setString(3, client.getEmail());
            stmt.setInt(4, client.getAdvisor().getId());
            stmt.setInt(5, client.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating client failed, no rows affected.");
            }
        }
        return client;
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
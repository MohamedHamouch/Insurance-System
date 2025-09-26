package dao;

import db.DatabaseConnection;
import models.Advisor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvisorDAO {

    public Advisor save(Advisor advisor) throws SQLException {
        String sql = "INSERT INTO advisors (firstName, lastName, email) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, advisor.getFirstName());
            stmt.setString(2, advisor.getLastName());
            stmt.setString(3, advisor.getEmail());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating advisor failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    advisor.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating advisor failed, no ID obtained.");
                }
            }
        }
        return advisor;
    }

    public Optional<Advisor> findById(int id) throws SQLException {
        String sql = "SELECT * FROM advisors WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Advisor advisor = new Advisor();
                    advisor.setId(rs.getInt("id"));
                    advisor.setFirstName(rs.getString("firstName"));
                    advisor.setLastName(rs.getString("lastName"));
                    advisor.setEmail(rs.getString("email"));
                    return Optional.of(advisor);
                }
            }
        }
        return Optional.empty();
    }

    public List<Advisor> findAll() throws SQLException {
        List<Advisor> advisors = new ArrayList<>();
        String sql = "SELECT * FROM advisors";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Advisor advisor = new Advisor();
                advisor.setId(rs.getInt("id"));
                advisor.setFirstName(rs.getString("firstName"));
                advisor.setLastName(rs.getString("lastName"));
                advisor.setEmail(rs.getString("email"));
                advisors.add(advisor);
            }
        }
        return advisors;
    }

    public Advisor update(Advisor advisor) throws SQLException {
        String sql = "UPDATE advisors SET firstName = ?, lastName = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, advisor.getFirstName());
            stmt.setString(2, advisor.getLastName());
            stmt.setString(3, advisor.getEmail());
            stmt.setInt(4, advisor.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating advisor failed, no rows affected.");
            }
        }
        return advisor;
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM advisors WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
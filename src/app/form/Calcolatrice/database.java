package app.form.Calcolatrice;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class database {
    private Connection conn;

    public database(String host, String DBname, String user, String password) {
        String connectionUrl = "jdbc:mysql://" + host + ":3306/" + DBname;

        try {
            conn = getConnection(connectionUrl, user, password);
            if (conn != null)
                System.out.println("Connessione avvenuta");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        try {
            String query = "SELECT * FROM utente WHERE nome = ? AND password = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrazione(String username, String password) {
        try {
            String query = "INSERT INTO utente (nome, password) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void salvaRisultato(String username, String risultato) {
        try {
            String query = "INSERT INTO risultati (username, risultato) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, username);
                statement.setString(2, risultato);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {

                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        System.out.println("Risultato salvato nel database con id: " + id);
                    }
                } else {
                    System.out.println("Errore durante il salvataggio del risultato nel database");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

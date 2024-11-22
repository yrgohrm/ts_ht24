package se.yrgo;

import static org.assertj.core.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.*;

class SimpleTest {
    @Test
    void testMySqlConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:tc:mysql:8.0.29:///test",
                "root", "test")) {

            createTable(conn);
            insertData(conn);
            queryData(conn);

        } catch (SQLException ex) {
            fail(ex);
        }
    }

    private void createTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE test (id INT AUTO_INCREMENT PRIMARY KEY, data INT)");
        }
    }

    private void insertData(Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO test VALUES (DEFAULT, ?)")) {
            for (int i = 0; i < 10; i++) {
                stmt.setInt(1, i);
                stmt.executeUpdate();
            }
        }
    }

    private void queryData(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("SELECT * FROM test")) {
            while (results.next()) {
                int id = results.getInt(1);
                int data = results.getInt(2);
                assertThat(data).isLessThanOrEqualTo(id);
            }
        }
    }

}

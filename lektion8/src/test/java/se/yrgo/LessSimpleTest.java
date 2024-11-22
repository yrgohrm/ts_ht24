package se.yrgo;

import static org.assertj.core.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;

@Testcontainers
class LessSimpleTest {
    @Container
    private static MySQLContainer<?> mySqlContainer = new MySQLContainer<>("mysql:8.0.29");

    @Test
    @Order(1)
    void testCreateTable() {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            assertThat(stmt.execute("CREATE TABLE test (id INT AUTO_INCREMENT PRIMARY KEY, data INT)")).isFalse();
        } catch (SQLException ex) {
            fail(ex);
        }
    }

    @Test
    @Order(2)
    void testInsert() {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO test VALUES (DEFAULT, ?)")) {
            for (int i = 0; i < 10; i++) {
                stmt.setInt(1, i);
                assertThat(stmt.executeUpdate()).isEqualTo(1);
            }
        } catch (SQLException ex) {
            fail(ex);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(mySqlContainer.getJdbcUrl(),
                mySqlContainer.getUsername(),
                mySqlContainer.getPassword());
    }
}

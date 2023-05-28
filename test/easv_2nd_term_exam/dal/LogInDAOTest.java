package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Admin;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.dal.connector.DBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This is the test class for LogInDAO.
 */
public class LogInDAOTest {
    private DBConnector dbConnector;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    private LogInDAO logInDAO;

    /**
     * Set up the test environment before each test case.
     * This includes creating mock objects and setting up the LogInDAO instance.
     */
    @BeforeEach
    public void setup() throws SQLException {
        dbConnector = mock(DBConnector.class);
        conn = mock(Connection.class);
        stmt = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);

        when(dbConnector.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        logInDAO = new LogInDAO();
        logInDAO.setDbConnector(dbConnector);
    }

    /**
     * Test case for the userLogIn method.
     * This test case checks that the userLogIn method works correctly with the provided username and password.
     */
    @Test
    public void testUserLogIn() throws SQLException {
        String testUsername = "testUser";
        String testPassword = "testPass";

        when(rs.next()).thenReturn(true);
        when(rs.getInt("ID")).thenReturn(1);
        when(rs.getString("Username")).thenReturn(testUsername);
        when(rs.getString("Password")).thenReturn(BCrypt.hashpw(testPassword, BCrypt.gensalt()));
        when(rs.getString("Name")).thenReturn("Test");
        when(rs.getString("Email")).thenReturn("test@example.com");
        when(rs.getString("Role")).thenReturn("ADMIN");
        when(rs.getBoolean("IsDeleted")).thenReturn(false);

        User result = logInDAO.userLogIn(testUsername, testPassword);

        // Asserts
        assertNotNull(result);
        assertTrue(result instanceof Admin);
        assertEquals("Test", result.getName());

        // Printing the results
        System.out.println("User: " + result.getName());
        System.out.println("Is admin: " + (result instanceof Admin));
    }
}
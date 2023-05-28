package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.dal.connector.DBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportDAOTest {
    private DBConnector dbConnector;
    private Connection conn;
    private PreparedStatement ps1, ps2, ps3;
    private ResultSet rs1, rs2, rs3;
    private ReportDAO reportDAO;


    @BeforeEach
    public void setup() throws SQLException {
        // Mocking
        dbConnector = mock(DBConnector.class);
        conn = mock(Connection.class);
        ps1 = mock(PreparedStatement.class);
        ps2 = mock(PreparedStatement.class);
        ps3 = mock(PreparedStatement.class);
        rs1 = mock(ResultSet.class);
        rs2 = mock(ResultSet.class);
        rs3 = mock(ResultSet.class);

        // Stubbing methods
        when(dbConnector.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(ps1).thenReturn(ps2).thenReturn(ps3);
        when(ps1.executeQuery()).thenReturn(rs1);
        when(ps2.executeQuery()).thenReturn(rs2);
        when(ps3.executeQuery()).thenReturn(rs3);

        // Simulate the result set data
        when(rs1.next()).thenReturn(true, false);
        // When getDate("createdDate") method is called on the rs1 object, return a non-null Date.
        LocalDate createdDate = LocalDate.of(2020, 1, 1);
        when(rs1.getDate("createdDate")).thenReturn(Date.valueOf(createdDate));
        // When getDate("expiryDate") method is called on the rs1 object, return a non-null Date.
        LocalDate expiryDate = LocalDate.of(2023, 1, 1);
        when(rs1.getDate("expiryDate")).thenReturn(Date.valueOf(expiryDate));
        when(rs1.getInt("customerId")).thenReturn(1);
        when(rs1.getString("customerName")).thenReturn("Test Customer");

        // Initialize the object to test
        reportDAO = new ReportDAO();
        reportDAO.setDbConnector(dbConnector);
    }

    @Test
    public void getAllTechnicianReportsTest() {
        List<Report> result = reportDAO.getAllTechnicianReports(1);

        assertNotNull(result);
        assertEquals(1, result.size());

        Report report = result.get(0);
        assertEquals(1, report.getCustomerId());
        assertEquals("Test Customer", report.getCustomerName());
    }
}

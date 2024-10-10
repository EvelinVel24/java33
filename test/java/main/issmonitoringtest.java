package issmonitoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class ISSMonitoringTest {

    private ISSMonitoring issMonitoring;

    @BeforeEach
    void setUp() throws Exception {
        issMonitoring = new ISSMonitoring();
        issMonitoring.conexionApi();
    }

    @Test
    void testConexionApi() throws Exception {
        assertNotNull(issMonitoring.conexionApi(), "API response should not be null");
    }

    @Test
    void testStatusConexion() {
        assertEquals("success", issMonitoring.statusConexion(), "Status should be 'success'");
    }

    @Test
    void testIdConexion() {
        assertNotNull(issMonitoring.idConexion(), "Connection ID should not be null");
    }

    @Test
    void testInfoLat() {
        String latitude = issMonitoring.infoLat();
        assertNotNull(latitude, "Latitude should not be null");
        assertTrue(latitude.matches("-?\\d+(\\.\\d+)?"), "Latitude should be a valid number");
    }

    @Test
    void testInfoLong() {
        String longitude = issMonitoring.infoLong();
        assertNotNull(longitude, "Longitude should not be null");
        assertTrue(longitude.matches("-?\\d+(\\.\\d+)?"), "Longitude should be a valid number");
    }
}

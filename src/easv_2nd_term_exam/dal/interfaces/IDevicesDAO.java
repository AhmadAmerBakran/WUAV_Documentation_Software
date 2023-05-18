package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Device;
import java.sql.SQLException;
import java.util.List;

public interface IDevicesDAO {
    Device createDevice(Device device) throws SQLException;
    Device getDevice(int id) throws SQLException;
    List<Device> getAllDevices() throws SQLException;
    void updateDevice(Device device) throws SQLException;
    void deleteDevice(int id) throws SQLException;
    List<Device> getDevicesByInstallationId(int installationId) throws SQLException;
    List<Device> createDevices(List<Device> devices) throws SQLException;

}

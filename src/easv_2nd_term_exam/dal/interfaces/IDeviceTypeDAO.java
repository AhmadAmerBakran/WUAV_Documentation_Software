package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.DeviceType;
import easv_2nd_term_exam.be.InstallationType;

import java.sql.SQLException;
import java.util.List;

public interface IDeviceTypeDAO {
    DeviceType createDeviceType(DeviceType deviceType) throws SQLException;
    DeviceType getDeviceType(int id) throws SQLException;
    List<DeviceType> getAllDeviceTypes() throws SQLException;
    void updateDeviceType(DeviceType deviceType) throws SQLException;
    void deleteDeviceType(int id) throws SQLException;
}
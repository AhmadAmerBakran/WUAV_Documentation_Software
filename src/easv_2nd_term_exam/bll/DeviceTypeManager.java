package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.dal.DeviceTypeDAO;
import easv_2nd_term_exam.be.DeviceType;
import easv_2nd_term_exam.dal.interfaces.IDeviceTypeDAO;

import java.sql.SQLException;
import java.util.List;

public class DeviceTypeManager {

    private IDeviceTypeDAO deviceTypeDAO;

    public DeviceTypeManager() {
        deviceTypeDAO = new DeviceTypeDAO();
    }

    public DeviceType createDeviceType(DeviceType deviceType) {
        try {
            return deviceTypeDAO.createDeviceType(deviceType);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public DeviceType getDeviceTypeById(int id) {
        try {
            return deviceTypeDAO.getDeviceType(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<DeviceType> getAllDeviceTypes() {
        try {
            return deviceTypeDAO.getAllDeviceTypes();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateDeviceType(DeviceType deviceType) {
        try {
            deviceTypeDAO.updateDeviceType(deviceType);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDeviceType(int id) {
        try {
            deviceTypeDAO.deleteDeviceType(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

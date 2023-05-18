package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.dal.DevicesDAO;
import easv_2nd_term_exam.be.Device;
import easv_2nd_term_exam.dal.interfaces.IDevicesDAO;

import java.sql.SQLException;
import java.util.List;

public class DevicesManager {

    private IDevicesDAO devicesDAO;

    public DevicesManager() {
        devicesDAO = new DevicesDAO();
    }

    public Device createDevice(Device device) {
        try {
            return devicesDAO.createDevice(device);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Device getDeviceById(int id) {
        try {
            return devicesDAO.getDevice(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Device> getAllDevices() {
        try {
            return devicesDAO.getAllDevices();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateDevice(Device device) {
        try {
            devicesDAO.updateDevice(device);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDevice(int id) {
        try {
            devicesDAO.deleteDevice(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Device> getDevicesByInstallationId(int installationId) {
        try {
            return devicesDAO.getDevicesByInstallationId(installationId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Device> createDevices(List<Device> devices) {
        try {
            return devicesDAO.createDevices(devices);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

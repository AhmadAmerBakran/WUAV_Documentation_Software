package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.Device;
import easv_2nd_term_exam.bll.DevicesManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DevicesModel {

    private DevicesManager devicesManager;
    private ObservableList<Device> devices;

    public DevicesModel() {
        devicesManager = new DevicesManager();
        devices = FXCollections.observableArrayList();
        loadDevices();
    }

    public Device createDevice(Device device) {
        Device createdDevice = devicesManager.createDevice(device);
        loadDevices();
        return createdDevice;
    }

    public Device getDeviceById(int id) {
        return devicesManager.getDeviceById(id);
    }

    public void updateDevice(Device device) {
        devicesManager.updateDevice(device);
        loadDevices();
    }

    public void deleteDevice(int id) {
        devicesManager.deleteDevice(id);
        loadDevices();
    }

    public List<Device> getDevicesByInstallationId(int installationId) {
        return devicesManager.getDevicesByInstallationId(installationId);
    }

    public List<Device> createDevices(List<Device> devices) {
        List<Device> createdDevices = devicesManager.createDevices(devices);
        loadDevices();
        return createdDevices;
    }

    private void loadDevices() {
        devices.clear();
        devices.addAll(devicesManager.getAllDevices());
    }

    public ObservableList<Device> getDevices() {
        return devices;
    }
}

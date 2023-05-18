package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.DeviceType;
import easv_2nd_term_exam.bll.DeviceTypeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceTypeModel {

    private DeviceTypeManager deviceTypeManager;
    private ObservableList<DeviceType> deviceTypes;

    public DeviceTypeModel() {
        deviceTypeManager = new DeviceTypeManager();
        deviceTypes = FXCollections.observableArrayList();
        loadDeviceTypes();
    }

    public void createDeviceType(DeviceType deviceType) {
        deviceTypeManager.createDeviceType(deviceType);
        loadDeviceTypes();
    }

    public void updateDeviceType(DeviceType deviceType) {
        deviceTypeManager.updateDeviceType(deviceType);
        loadDeviceTypes();
    }

    public void deleteDeviceType(int id) {
        deviceTypeManager.deleteDeviceType(id);
        loadDeviceTypes();
    }

    private void loadDeviceTypes() {
        deviceTypes.clear();
        deviceTypes.addAll(deviceTypeManager.getAllDeviceTypes());
    }

    public ObservableList<DeviceType> getDeviceTypes() {
        return deviceTypes;
    }
}

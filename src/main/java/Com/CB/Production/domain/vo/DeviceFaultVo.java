package Com.CB.Production.domain.vo;

import Com.CB.Production.domain.DeviceFault;

public class DeviceFaultVo extends DeviceFault {
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}

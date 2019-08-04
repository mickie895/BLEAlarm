package mickie895.github.io.android.blealarm.BleRecyclerView;

import android.bluetooth.BluetoothDevice;

public class BlePeripheral {
    private BluetoothDevice device;

    private String name = null;
    private String address = null;

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public String getAddress(){
        if (address == null) {
            return device.getAddress();
        }
        else{
            return address;
        }
    }

    public String getName(){
        if (name == null) {
            return device.getName();
        }else{
            return name;
        }
    }

    public static BlePeripheral CreateSampleDevice(String Name,String Address){
        BlePeripheral peripheral = new BlePeripheral();
        peripheral.name = Name;
        peripheral.address = Address;
        return peripheral;
    }
}

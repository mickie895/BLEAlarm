package mickie895.github.io.android.blealarm;

import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import org.bluetooth.bledemo.BleWrapper;
import org.bluetooth.bledemo.BleWrapperUiCallbacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication extends Application {

    private ArrayList<BleWrapperUiCallbacks> callbackList = new ArrayList<>();

    private BleWrapperUiCallbacks callbackTrigger = new BleWrapperUiCallbacks() {
        @Override
        public void uiDeviceFound(BluetoothDevice device, int rssi, byte[] record) {
            for (BleWrapperUiCallbacks trigger: callbackList
                 ) {
                trigger.uiDeviceFound(device, rssi, record);
            }
        }

        @Override
        public void uiDeviceConnected(BluetoothGatt gatt, BluetoothDevice device) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiDeviceConnected(gatt, device);
            }
        }

        @Override
        public void uiDeviceDisconnected(BluetoothGatt gatt, BluetoothDevice device) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiDeviceDisconnected(gatt, device);
            }
        }

        @Override
        public void uiAvailableServices(BluetoothGatt gatt, BluetoothDevice device, List<BluetoothGattService> services) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiAvailableServices(gatt, device, services);
            }
        }

        @Override
        public void uiCharacteristicForService(BluetoothGatt gatt, BluetoothDevice device, BluetoothGattService service, List<BluetoothGattCharacteristic> chars) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiCharacteristicForService(gatt, device, service,chars);
            }
        }

        @Override
        public void uiCharacteristicsDetails(BluetoothGatt gatt, BluetoothDevice device, BluetoothGattService service, BluetoothGattCharacteristic characteristic) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiCharacteristicsDetails(gatt, device, service,characteristic);
            }
        }

        @Override
        public void uiNewValueForCharacteristic(BluetoothGatt gatt, BluetoothDevice device, BluetoothGattService service, BluetoothGattCharacteristic ch, String strValue, int intValue, byte[] rawValue, String timestamp) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiNewValueForCharacteristic(gatt, device, service,ch, strValue, intValue, rawValue, timestamp);
            }
        }

        @Override
        public void uiGotNotification(BluetoothGatt gatt, BluetoothDevice device, BluetoothGattService service, BluetoothGattCharacteristic characteristic) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiGotNotification(gatt, device, service,characteristic);
            }
        }

        @Override
        public void uiSuccessfulWrite(BluetoothGatt gatt, BluetoothDevice device, BluetoothGattService service, BluetoothGattCharacteristic ch, String description) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiSuccessfulWrite(gatt, device, service,ch, description);
            }
        }

        @Override
        public void uiFailedWrite(BluetoothGatt gatt, BluetoothDevice device, BluetoothGattService service, BluetoothGattCharacteristic ch, String description) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiFailedWrite(gatt, device, service,ch, description);
            }
        }

        @Override
        public void uiNewRssiAvailable(BluetoothGatt gatt, BluetoothDevice device, int rssi) {
            for (BleWrapperUiCallbacks trigger: callbackList
            ) {
                trigger.uiNewRssiAvailable(gatt, device, rssi);
            }
        }
    };

    private BleWrapper wrapper = null;

    public void AddCallback(BleWrapperUiCallbacks callback){
        if (!callbackList.contains(callback)){
            callbackList.add(callback);
        }
    }
    public void RemoveCallback(BleWrapperUiCallbacks callback){
        callbackList.remove(callback);
    }

    public void BleStartScan(){
        if (getWrapper().isBtEnabled()){
            wrapper.startScanning();
        }
    }

    public void BleStopScan(){
        wrapper.stopScanning();
    }

    private BleWrapper getWrapper() {
        if (wrapper == null){
            wrapper = new BleWrapper(this,callbackTrigger);
            wrapper.initialize();
        }
        return wrapper;
    }
}

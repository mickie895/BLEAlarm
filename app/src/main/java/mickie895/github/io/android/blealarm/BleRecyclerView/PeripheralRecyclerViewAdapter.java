package mickie895.github.io.android.blealarm.BleRecyclerView;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mickie895.github.io.android.blealarm.R;

public class PeripheralRecyclerViewAdapter extends RecyclerView.Adapter {

    private ArrayList<BluetoothDevice> mDevices;
    private ArrayList<byte[]> mRecords;
    private ArrayList<Integer> mRSSIs;


    public void addDevice(BluetoothDevice device, int rssi, byte[] scanRecord) {
        if(!mDevices.contains(device)) {
            mDevices.add(device);
            mRSSIs.add(rssi);
            mRecords.add(scanRecord);
        }
        notifyDataSetChanged();
    }

    public BluetoothDevice getDevice(int index) {
        return mDevices.get(index);
    }

    public int getRssi(int index) {
        return mRSSIs.get(index);
    }

    public PeripheralRecyclerViewAdapter() {
        mDevices  = new ArrayList<>();
        mRecords = new ArrayList<>();
        mRSSIs = new ArrayList<>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.peripheral_state, viewGroup, false);
        return new PeripheralRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PeripheralRecyclerViewHolder){
            BluetoothDevice device = mDevices.get(i);
            int rssi = mRSSIs.get(i);
            String rssiString = (rssi == 0) ? "N/A" : rssi + " db";
            String name = device.getName();
            String address = device.getAddress();
            if(name == null || name.length() <= 0) name = "Unknown Device";

            ((PeripheralRecyclerViewHolder)viewHolder).SetTexts(name,address,rssiString);
        }
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }
}

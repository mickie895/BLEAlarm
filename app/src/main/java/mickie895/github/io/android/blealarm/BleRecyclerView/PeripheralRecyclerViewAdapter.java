package mickie895.github.io.android.blealarm.BleRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mickie895.github.io.android.blealarm.R;

public class PeripheralRecyclerViewAdapter extends RecyclerView.Adapter {

    public void setPeripheralList(List<BlePeripheral> peripheralList) {
        PeripheralList = peripheralList;
    }

    private List<BlePeripheral> PeripheralList;

    public PeripheralRecyclerViewAdapter(List<BlePeripheral> peripheralList) {
        PeripheralList = peripheralList;
    }

    public PeripheralRecyclerViewAdapter() {
        PeripheralList = new ArrayList<BlePeripheral>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.peripheral_state, viewGroup, false);
        PeripheralRecyclerViewHolder vh = new PeripheralRecyclerViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PeripheralRecyclerViewHolder){
            BlePeripheral ble = PeripheralList.get(i);
            ((PeripheralRecyclerViewHolder)viewHolder).SetTexts(ble.getName(),ble.getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return PeripheralList.size();
    }
}

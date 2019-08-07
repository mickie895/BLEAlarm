package mickie895.github.io.android.blealarm.BleRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mickie895.github.io.android.blealarm.R;

public class PeripheralRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView nameView;
    private TextView addressView;
    private TextView rssiView;

    PeripheralRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.PeripheralName);
        addressView = itemView.findViewById(R.id.PeripheralAdress);
        rssiView = itemView.findViewById(R.id.PeripheralRssi);
    }

    public void SetTexts(String name,String address){
        nameView.setText(name);
        addressView.setText(address);
    }

    void SetTexts(String name, String address, String rssi) {
        nameView.setText(name);
        addressView.setText(address);
        rssiView.setText(rssi);
    }

}

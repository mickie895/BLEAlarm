package mickie895.github.io.android.blealarm.BleRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mickie895.github.io.android.blealarm.R;

public class PeripheralRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView nameView;
    public TextView addressView;

    public PeripheralRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = (TextView)itemView.findViewById(R.id.PeripheralName);
        addressView = (TextView)itemView.findViewById(R.id.PeripheralAdress);
    }

    public void SetTexts(String name,String address){
        nameView.setText(name);
        addressView.setText(address);
    }

}

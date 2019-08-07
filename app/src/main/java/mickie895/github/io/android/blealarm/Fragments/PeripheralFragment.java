package mickie895.github.io.android.blealarm.Fragments;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.bluetooth.bledemo.BleWrapperUiCallbacks;

import java.util.List;
import java.util.Objects;

import mickie895.github.io.android.blealarm.BleRecyclerView.PeripheralRecyclerViewAdapter;
import mickie895.github.io.android.blealarm.MainApplication;
import mickie895.github.io.android.blealarm.R;

public class PeripheralFragment extends Fragment {
    private static final long SCANNING_TIMEOUT = 10 * 1000; /* 10 seconds */
    private static final int ENABLE_BT_REQUEST_ID = 1;

    private boolean bleScanning = false;
    private TextView mainTextLabel;

    private MainApplication app;

    private BleWrapperUiCallbacks callback = new BleWrapperUiCallbacks.Null(){
        @Override
        public void uiDeviceFound(BluetoothDevice device, int rssi, byte[] record) {
            getAdapter().addDevice(device, rssi, record);
        }
    };

    public PeripheralRecyclerViewAdapter getAdapter() {
        if (adapter == null){
            adapter = new PeripheralRecyclerViewAdapter();
        }
        return adapter;
    }

    private PeripheralRecyclerViewAdapter adapter = null;

    public PeripheralFragment() {
        // Required empty public constructor
    }

    public static PeripheralFragment newInstance() {
        PeripheralFragment fragment = new PeripheralFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        app = (MainApplication) getContext().getApplicationContext();

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_peripheral, container, false);
        RecyclerView rv = v.findViewById(R.id.pheripheral_list);

        rv.setHasFixedSize(false);

        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        rv.setAdapter(getAdapter());

        Button b = v.findViewById(R.id.bleScannButton);
        b.setOnClickListener(ScanButtonListener);

        mainTextLabel = v.findViewById(R.id.mainLabel);

        app.AddCallback(callback);

        return v;
    }

    @Override
    public void onDestroyView() {
        app.RemoveCallback(callback);
        super.onDestroyView();
    }

    public View.OnClickListener ScanButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!bleScanning){
                app.BleStartScan();
                bleScanning = true;
                handler.postDelayed(timeoutRunnable, SCANNING_TIMEOUT);
            }
        }
    };

    final Handler handler = new Handler();
    final Runnable timeoutRunnable = new Runnable() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    app.BleStopScan();
                    bleScanning = false;
                    Toast.makeText(getActivity(),"Bluetooth Low Energy Scan OFF.",Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

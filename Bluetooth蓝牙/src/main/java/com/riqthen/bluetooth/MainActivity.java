package com.riqthen.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 11;
    private static final int REQUEST_ENABLE = 22;
    private static final int REQUEST_DISCOVERABLE = 33;
    private ListView listView;
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // TODO 检查权限，6.0以上和6.0以下的区别
        checkSDKVersion();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deviceNames);
        listView.setAdapter(adapter);
    }

    private void checkSDKVersion() {
        //6.0以上需要此权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //6.0及以上  M的值为23
            int check = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            if (check != PackageManager.PERMISSION_GRANTED) {   //如果权限未授予，则请求权限
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO 开启蓝牙
    public void open(View v) {
//        bluetoothAdapter.enable();    //直接打开蓝牙，而不需要经过允许   不推荐
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, REQUEST_ENABLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == resultCode) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "蓝牙已开启", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "蓝牙未开启", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_DISCOVERABLE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "允许被扫描", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "不允许被扫描", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //TODO 关闭蓝牙
    public void close(View v) {
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            Toast.makeText(this, "蓝牙已关闭", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "蓝牙未开启", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO 搜索蓝牙设备
    public void search(View v) {
        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300), REQUEST_DISCOVERABLE);
    }

    // 显示可连接的设备
    public void getConfiguration(View v) {
        if (bluetoothAdapter.isEnabled()) {     //如果蓝牙打开，则获取设备信息
            bluetoothAdapter.startDiscovery();  //使用广播的方式获取设备数据
        } else {    //如果蓝牙未打开
            Toast.makeText(this, "请打开蓝牙", Toast.LENGTH_SHORT).show();
        }

    }


    //TODO 注册广播并设置action
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver, intentFilter);  //注册广播
    }

    //TODO 解绑广播
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);  //解绑广播
    }

    private List<BluetoothDevice> devices = new ArrayList<>();
    private List<String> deviceNames = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                Toast.makeText(context, "扫描完毕", Toast.LENGTH_SHORT).show();
            } else {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                devices.add(device);
                deviceNames.add(TextUtils.isEmpty(device.getName()) ? "未知设备" : device.getName());
                adapter.notifyDataSetChanged();
            }
        }
    };
}

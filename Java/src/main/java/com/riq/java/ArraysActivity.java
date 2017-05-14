package com.riq.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;

/**
 * 数组的Arrays方法
 * sort() fill() binarySearch
 */
public class ArraysActivity extends AppCompatActivity {
    int[] num = {8, 9, 2, 7, 1, 0, 6, 3, 5, 4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrays);
        // TODO 从小到大排序数组
        Arrays.sort(num);  // num变为 {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
        Log.e("===", Arrays.toString(num));
        // TODO 将555填充到数组的2到4个元素内
        Arrays.fill(num, 2, 4, 555);    // num再变为 {0, 1, 555, 555, 4, 5, 6, 7, 8, 9}
        Log.e("===", Arrays.toString(num));
        // TODO 查找新num数组中序号为5的元素的值
        Arrays.binarySearch(num, 5);    // 5
    }
}

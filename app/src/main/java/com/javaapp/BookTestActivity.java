package com.javaapp;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class BookTestActivity extends AppCompatActivity implements LabTestSelectionCallback {

    private TextView textTitle, testPrice;
    private int selected;
    private TextView totalPrice;
    private Boolean isSelected = true;
    private Map<Integer, TestListData> selectedLabTestsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_test);

        removePhoneKeypad();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Test Booking");

        totalPrice = findViewById(R.id.totalprice);

        selectedLabTestsMap = new HashMap<>();
        RecyclerView rvTestsList = findViewById(R.id.lv_testdata);
        rvTestsList.setLayoutManager(new LinearLayoutManager(this));
        rvTestsList.setHasFixedSize(true);
        rvTestsList.setAdapter(new LabTestAdapater(this, prepareListData(), this));
    }

    private List<TestListData> prepareListData() {
        List<TestListData> testListData = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            TestListData listData = new TestListData();
            listData.setAmount(300);
            listData.setName("Blood Test");
            listData.setId(i);
            testListData.add(listData);
        }
        return testListData;
    }

    public void removePhoneKeypad() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            System.out.println("getCurrentFocus() in frag");
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            IBinder binder = getCurrentFocus().getWindowToken();
            inputManager.hideSoftInputFromWindow(binder,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onLabTestItemSelected(TestListData testListData) {
        if (testListData != null) {
            if (selectedLabTestsMap.containsKey(testListData.getId())) {
                selectedLabTestsMap.remove(testListData.getId());
            } else {
                selectedLabTestsMap.put(testListData.getId(), testListData);
            }
            long price = 0L;
            for (TestListData listData : selectedLabTestsMap.values()) {
                price = price + listData.getAmount();
            }
            totalPrice.setText(String.valueOf(price));
        }
    }
}

package com.riqthen.base.asset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;

import com.riqthen.base.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class AssetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        loadAsset();
    }

    private void loadAsset() {
        try {
            InputStream is = getAssets().open("car.xml");
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "UTF-8");
            int type = parser.getEventType();
            List<Car> cars = new ArrayList<>();
            Car car = null;
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("Car")) {
                            car = new Car();
                            car.id = Integer.parseInt(parser.getAttributeValue(0));
                        } else if (parser.getName().equals("pingpai")) {
                            //获取后面的文本
                            car.pingpai = parser.nextText();
                        } else if (parser.getName().equals("color")) {
                            car.color = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("Car")) {
                            cars.add(car);
                        }
                        break;
                }
                parser.next();
                type = parser.getEventType();
            }
            Log.e("TAG", cars.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }


    class Car {
        String pingpai;
        String color;
        int id;

        @Override
        public String toString() {
            return "Car{" +
                    "pingpai='" + pingpai + '\'' +
                    ", color='" + color + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
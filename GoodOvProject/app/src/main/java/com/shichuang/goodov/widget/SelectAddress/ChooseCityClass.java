package com.shichuang.goodov.widget.SelectAddress;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/24.
 */

public class ChooseCityClass implements ChooseCityInterface {
    //省份
    private String provinceCode;
    //市区
    private String cityCode;
    //区
    private String areaCode;
    //省市区选择器
    private ArrayList<City> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<City> mList = new ArrayList<>();
    private Context mContext;
    private TextView textView;

    public ChooseCityClass(Context mContext, TextView textView) {
        this.mContext = mContext;
        this.textView = textView;
    }

    @Override
    public void initCitySelectData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String strCity = getJson("city.json", mContext);
                JsonParser parser = new JsonParser();
                JsonArray jsonArray1 = parser.parse(strCity).getAsJsonArray();

                Gson gson = new Gson();
                for (JsonElement city : jsonArray1) {
                    City city2 = gson.fromJson(city, City.class);
                    mList.add(city2);
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    private String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = context.getClass().getClassLoader().getResourceAsStream("assets/" + fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = mList;

        for (int i = 0; i < mList.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < mList.get(i).getCitys().size(); c++) {//遍历该省份的所有城市
                String CityName = mList.get(i).getCitys().get(c).getCityName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (mList.get(i).getCitys().get(c).getAreas() == null
                        || mList.get(i).getCitys().get(c).getAreas().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < mList.get(i).getCitys().get(c).getAreas().size(); d++) {//该城市对应地区所有数据
                        String AreaName = mList.get(i).getCitys().get(c).getAreas().get(d).getCityName();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                setData();
            }
        }
    };

    private void setData() {
        initJsonData();
    }

    /**
     * 显示数据
     */
    @Override
    public void show() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String address = options1Items.get(options1).getCityName() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                textView.setText(address);
                provinceCode = options1Items.get(options1).getCityCode();
                cityCode = options1Items.get(options1).getCitys().get(options2).getCityCode();
                areaCode = options1Items.get(options1).getCitys().get(options2).getAreas().get(options3).getCityCode();
            }
        })
                .setTitleText("选择城市")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setCancelColor(Color.parseColor("#b99042"))
                .setSubmitColor(Color.parseColor("#b99042"))
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }
}

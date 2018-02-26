package com.shichuang.goodov.widget.SelectAddress;


import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

public class City implements CityInterface, IPickerViewData {

    public String name;
    public String code;
    public List<City1> citys;

    public List<City1> getCitys() {
        return citys;
    }

    public void setCitys(List<City1> citys) {
        this.citys = citys;
    }

    @Override
    public String getCityName() {
        return name;
    }

    @Override
    public String getCityCode() {
        return code;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class City1 implements CityInterface {
        public String name;
        public String code;
        public String provincecode;
        public List<Area> areas;

        public List<Area> getAreas() {
            return areas;
        }

        public void setAreas(List<Area> areas) {
            this.areas = areas;
        }

        @Override
        public String getCityName() {
            return name;
        }

        @Override
        public String getCityCode() {
            return code;
        }

        public static class Area implements CityInterface {
            public String citycode;
            public String name;
            public String code;

            @Override
            public String getCityName() {
                return name;
            }

            @Override
            public String getCityCode() {
                return code;
            }
        }
    }
}

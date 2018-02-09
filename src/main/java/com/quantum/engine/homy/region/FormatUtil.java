package com.quantum.engine.homy.region;

import com.google.gson.Gson;
import com.quantum.engine.homy.model.BasRegion;

import java.util.ArrayList;
import java.util.List;


public class FormatUtil {
	
    public static List<Bean> parseRegion(List<BasRegion> regionList) {
    	Gson gson = new Gson();
    	
        List<Bean> provinceList = new ArrayList<>();
        List<Bean.CityBean> cityList = new ArrayList<>();
        List<Bean.CityBean.countyBean> countyList = new ArrayList<>();

        for (int i = 0; i < regionList.size(); i++) {
        	BasRegion city = regionList.get(i);
            //省
            if (city.getGrade() == 2) {
                Bean bean = new Bean();
                bean.setName(city.getName());
                bean.setId(city.getId());
                bean.setPid(city.getPid());
                provinceList.add(bean);
            }
            //市
            if (city.getGrade() == 3) {
                Bean.CityBean bean = new Bean.CityBean();
                bean.setName(city.getName());
                bean.setId(city.getId());
                bean.setPid(city.getPid());
                cityList.add(bean);
            }
            //县
            if (city.getGrade() == 4) {
                Bean.CityBean.countyBean bean = new Bean.CityBean.countyBean();
                bean.setName(city.getName());
                bean.setId(city.getId());
                bean.setPid(city.getPid());
                countyList.add(bean);
            }
        }

        for (int i = 0; i < provinceList.size(); i++) {
            //省
            Bean bean = provinceList.get(i);
            List<Bean.CityBean> cityBeanList = new ArrayList<>();
            //city
            for (int j = 0; j < cityList.size(); j++) {
                Bean.CityBean cityBean = cityList.get(j);
                if (cityBean.getPid() == bean.getId()) {
                    //如果市的pid==省id
                    List<Bean.CityBean.countyBean> countyBeanList = new ArrayList<>();
                    for (int k = 0; k < countyList.size(); k++) {
                        //如果县的pid==市id
                        Bean.CityBean.countyBean countyBean = countyList.get(k);
                        if (countyBean.getPid() == cityBean.getId()) {
                            countyBeanList.add(countyBean);
                        }
                    }
                    cityBean.setArea(countyBeanList);
                    cityBeanList.add(cityBean);
                }
            }
            bean.setCity(cityBeanList);
        }
        //String toJson = gson.toJson(provinceList);
        return provinceList;
    }

}

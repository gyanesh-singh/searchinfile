package com.gyanesh.autocompletion.model;

/**
 * Created by gyanesh.singh on 08/07/17.
 * This is acting as model object and can used to represent
 * list data as well. Created o support row for a table in case of DB
 */
public class City {

    private String cityName;

    public String getCityName () {
        return cityName;
    }

    public void setCityName ( String cityName ) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        return getCityName ().equals ( city.getCityName () );
    }

    @Override
    public int hashCode () {
        return getCityName ().hashCode ();
    }

    @Override
    public String toString () {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
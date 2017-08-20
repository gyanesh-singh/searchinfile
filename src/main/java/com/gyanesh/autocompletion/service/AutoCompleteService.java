package com.gyanesh.autocompletion.service;

import com.gyanesh.autocompletion.dao.CityDAOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Created by gyanesh.singh on 08/09/17.
 * This is the actual implementation to the service.
 **/
@Service
public class AutoCompleteService implements AutoCompleteServiceI {

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CityDAOI cityDaoI;

    /**
     * This method will avail service exposed by DAO layer
     *
     * @param cityName
     * @param count
     * @return
     */
    @Override
    public List <String> getCityList ( String cityName, int count ) {
        return cityDaoI.getCities ( cityName, count );
    }
}
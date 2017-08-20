package com.gyanesh.autocompletion.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gyanesh.singh on 08/11/17.
 * This is acting as DAO. Method level authentication and
 * authorization can also be put to restrict access. However in current
 * scenario this end point is doing just one operation and entry was restricted.
 */
@Repository
public class CityDAO implements CityDAOI {
    private final Logger logger = LoggerFactory.getLogger ( this.getClass () );

    /**
     * This method is accepting request and passing to required API for further processing
     *
     * @param cityStartName
     * @param count
     * @return List<String>
     */
    public List <String> getCities ( String cityStartName, int count ) {
        //logger.info ( "getCities:Input values:"+cityStartName+":"+count  );
        return readFile ( cityStartName, count );
    }

    /**
     * This method takes starting character of city name. In this we are doing case sensitive search
     * and count is used to control how many to fetch from CSV data source
     *
     * @param cityStartName
     * @param count
     * @return List<String>
     */
    private List <String> readFile ( String cityStartName, int count ) {

        logger.info ( "readFile:Input values:" + cityStartName + ":" + count );
        Resource resource = new ClassPathResource ("all_india_pin_code_limit.txt");
        try (Stream <String> stream = new BufferedReader (
                    new InputStreamReader ( resource.getInputStream ()) ).lines ().parallel ()) {
            //Read from stream and check which all entries are matching with and the return
            //Unique values and with required number of data
            return stream.filter ( line -> line != null && line.startsWith ( cityStartName ) ).distinct ().
                    limit ( count ).sorted ().collect ( Collectors.toList () );

        } catch (Exception ex) {
            logger.error ( "Error occurred while processing data in file" + ex.getLocalizedMessage () );
        }
        //If not found then return empty list
        return new ArrayList <String> ();
    }
}

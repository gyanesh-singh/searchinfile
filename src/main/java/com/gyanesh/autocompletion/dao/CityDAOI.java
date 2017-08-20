package com.gyanesh.autocompletion.dao;

import java.util.List;

/**
 * Created by gyanesh.singh on 08/09/17.
 * This is service interface to serve the service request. All API's are exposed
 * by interface only
 */
public interface CityDAOI {
    public List <String> getCities ( String inputText, int count );
}

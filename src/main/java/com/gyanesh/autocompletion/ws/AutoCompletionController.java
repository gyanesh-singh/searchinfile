package com.gyanesh.autocompletion.ws;

import com.gyanesh.autocompletion.service.AutoCompleteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Created by gyanesh.singh on 08/11/17.
 * This is the actual implementation to the service.
 **/

@RestController
public class AutoCompletionController {

    //Commented to provide logging support by AOP
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AutoCompleteServiceI service;

    @RolesAllowed("ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/suggest_cities",
            produces = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
    @Cacheable(cacheNames = "cities", key = "{#cityName,#count}")
    public String searchCities ( @RequestParam(value = "start") String cityName, @RequestParam(value = "atmost") Integer count ) {
        if (cityName.isEmpty () || count == null || count == 0 || count.toString ().isEmpty ()) {
            //logger.error ( "Negative inputs: Returning with empty response."  );
            return new String ( "" );
        }
        List <String> ls = service.getCityList ( cityName, count );
        StringBuilder sb = new StringBuilder ();

        //To see if last element to not to append new line character
        int lastIndex = ls.size () - 1;
        int counter = 0;
        for (String tmp : ls) {
            if (counter != lastIndex)
                sb.append ( tmp + "\n" );
            else
                sb.append ( tmp );
            counter++;
        }
        return sb.toString ();
    }


}
package com.gyanesh.autocompletion.ws;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gyanesh.singh on 08/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutoCompletionControllerTest {

    private static final String USER_NAME="test";
    //We can hide these credentials in the form or encryption
    private static final String USER_PASSWORD="test1234";

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp () throws Exception {
        //Do all initialization here
    }

    @After
    public void tearDown () throws Exception {
        //DO all clean up activity here
    }

    @Test
    public void searchCities () {
        //First check positive case
        String body = this.restTemplate.withBasicAuth ( USER_NAME, USER_PASSWORD ).
                getForObject ( "/suggest_cities?start=A&atmost=5", String.class );
        assertThat ( body ).isEqualTo ( "AdilGyanesh\n" +
                "Adilabad\n" +
                "Alair\n" +
                "Asifabad\n" +
                "Atmakur" );
    }

    @Test
    public void searchCitiesWithoutStart () {
        //Check boundry conditions (negative test cases) - start character not passed
        String body = this.restTemplate.withBasicAuth ( USER_NAME, USER_PASSWORD  ).
                getForObject ( "/suggest_cities?start=&atmost=5", String.class );
        assertThat ( body ).isNull ();
    }

    @Test
    public void searchCitiesWithoutAtmost () {

        //Check boundry conditions (negative test cases) - atmost character not passed
        String body = this.restTemplate.withBasicAuth ( USER_NAME, USER_PASSWORD  ).
                getForObject ( "/suggest_cities?start=A&atmost=", String.class );
        assertThat ( body ).isNull ();
    }

    @Test
    public void searchCitiesNoParameter () {
        //Check boundry conditions (negative test cases) - neither parameter passed
        String body = this.restTemplate.withBasicAuth ( USER_NAME, USER_PASSWORD  ).
                getForObject ( "/suggest_cities?start=&atmost=", String.class );
        assertThat ( body ).isNull ();

    }

    @Test
    public void searchCitiesWithoutAuthentication () {
        //Check boundry conditions (negative test cases) - without authentication cse
        String body = this.restTemplate.
                getForObject ( "/suggest_cities?start=A&atmost=5", String.class );
        assertThat ( body ).contains ( "HTTP Status 401" );
    }

}
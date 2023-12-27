package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GCUD1 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    @Test
    public void getBookings(){
        //validate response
        RestAssured.baseURI="https://restful-booker.herokuapp.com/booking";
        //create a requestSpecification
        requestSpecification=RestAssured.given();
        //calling method
        response=requestSpecification.get();
        // print response
        System.out.println(response.prettyPrint());
        validatableResponse=response.then();
        //get status code
        validatableResponse.statusCode(200);
    }
    @Test
    public void getBookingByID(){
        //validate response
        RestAssured.baseURI="https://restful-booker.herokuapp.com/booking/32";
        //create a requestSpecification
        requestSpecification=RestAssured.given();
        //calling method
        response=requestSpecification.get();
        // print response
        System.out.println(response.prettyPrint());
        validatableResponse=response.then();
        //get status code
        validatableResponse.statusCode(200);
    }
    @Test
    public  void createBooking(){
        String jsondata="{\n" +
                "    \"firstname\" : \"Rennee\",\n" +
                "    \"lastname\" : \"Kapur\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        // GIVEN

            RestAssured.baseURI="https://restful-booker.herokuapp.com/booking";
        //create request specification
        requestSpecification = RestAssured.given();

        // // Setting content type to specify format in which request payload will be sent.
        requestSpecification.contentType(ContentType.JSON);

        //// Adding body as string
        requestSpecification.body(jsondata);

        //// Calling POST method
        response = requestSpecification.post();

        // // Let's print response body.
        System.out.println(response.prettyPrint());

        validatableResponse = response.then();
        // Check status code
        validatableResponse.statusCode(200);

        // Check response body - name attribute
        validatableResponse.body("booking.firstname", equalTo("Rennee"));
        validatableResponse.body("booking.lastname", equalTo("Kapur"));

    }
    @Test
    public void updateBooking() {

        String jsonData = "{\n" +
                "    \"firstname\" : \"Rennee\",\n" +
                "    \"lastname\" : \"Oberoi\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        given()
                .baseUri("https://restful-booker.herokuapp.com/booking/4851")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .put()
                .then()
                .body("booking.lastname", equalTo("Oberoi"));

    }


}



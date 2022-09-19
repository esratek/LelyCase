package casestudy;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserTests {



    @Test

    public void getUsers(){

         String endpoint = "https://gorest.co.in/public/v1/users";
         var response = given().when().get(endpoint).then().
                 log().
                 body().
                 assertThat().
                 statusCode(200).
                 body("data.size()",equalTo(10)).
                 body("data.id", everyItem(notNullValue())).
                 body("data.id",everyItem(greaterThan(999))).
                 body("data.id",everyItem(lessThan(10000)));

    }

    @Test

    public void getUserID(){

       String endpoint = "https://gorest.co.in/public/v1/users";

       var response = given().
                         queryParam("id" ,3191).
                      when().
                            get(endpoint).
                      then();
        response.log().body();

    }

    @Test

    public void createUser() throws IOException {

        URL url = null;
        try {
            url = new URL("https://gorest.co.in/public/v1/users");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer 1db9c9b6c959682be7c96f74ca532c3cb0bd331f46b86a92602f8d319481b6f5");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"name\":" +
                " \"Naz Tekin\",\n " +
                " \"email\": \"testtesttesttesttest@gmail.com\",\n " +
                "\"gender\": \"female\",\n  " +
                " \"status\": \"active\"}\n ";


        try(OutputStream os = http.getOutputStream()) {
            byte[] input = data.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(http.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

        try {
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage() );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        http.disconnect();
        }

    @Test

    public void createUserAlreadyExist() throws IOException {

        URL url = null;
        try {
            url = new URL("https://gorest.co.in/public/v1/users");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer 1db9c9b6c959682be7c96f74ca532c3cb0bd331f46b86a92602f8d319481b6f5");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"name\":" +
                " \"Naz Tekin\",\n " +
                " \"email\": \"testtesttesttesttest@gmail.com\",\n " +
                "\"gender\": \"female\",\n  " +
                " \"status\": \"active\"}\n ";


        try(OutputStream os = http.getOutputStream()) {
            byte[] input = data.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(http.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }catch(Exception e){

            if(e.getMessage().contains("Server returned HTTP response code: 422")){
                System.out.println("has already been taken");
            }else{

                throw e;
            }

        }

        try {
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage() );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("has already been taken");

        }

        http.disconnect();


    }


    }






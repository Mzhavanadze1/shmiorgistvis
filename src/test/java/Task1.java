import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;

public class Task1 {

    @Test
    void testApi() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.jsonPath().getList("data").size());
        List<LinkedHashMap<String, String>> users = response.jsonPath().getList("data");
        for (LinkedHashMap<String, String> user : users) {
            String email = user.get("email");
            System.out.println(email);
            Assert.assertTrue(email.contains("@"));

        }
        Assert.assertTrue(response.header("Content-type").contains("application/json"));

    }




}









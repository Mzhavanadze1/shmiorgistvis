package Steps;

import Models.SMSModule.GetConsent.GetSMSRequestModel;
import Models.SMSModule.PostConsent.PostSmsRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ConsentCalls {

    public Response getConsentCalls(GetSMSRequestModel getSMSRequestModel) {
        return given()
                .headers("content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSMSRequestModel.getTelNumber());
    }

    public Response postConsentCalls(PostSmsRequestModel postSmsRequestModel) {
        return given()
                .headers("content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(postSmsRequestModel)
                .post("http://10.195.105.66:7000/api/Consent");
    }
}
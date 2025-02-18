import DataController.SMSModule;
import Models.SMSModule.GetConsent.GetSMSRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static DataController.SMSModule.getSMSRequestModels;
import static io.restassured.RestAssured.given;


public class SMSModuleTask {
    @Test
    public void testSMSModel() throws SQLException, ClassNotFoundException {
        List<GetSMSRequestModel> getSMSRequestModel = getSMSRequestModels(SMSModule.queryOfSMSModuleTask);
        ;
        for (int i = 0; i < getSMSRequestModel.size(); i++) {

            Response response = given()
                    .headers("content-type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .when()
                    .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSMSRequestModel.get(i).getTelNumber());
            System.out.println(response.asPrettyString());

            Assert.assertEquals(response.jsonPath().getString("data.consentStatusId"), getSMSRequestModel.get(i).getConsent());


        }

    }


}

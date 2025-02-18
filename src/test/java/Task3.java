import Models.SMSModule.GetConsent.GetSMSRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static DataController.SMSModule.getSMSRequestModels;
import static DataController.SMSModule.queryOfSMSModuleTask;
import static io.restassured.RestAssured.given;

public class Task3 {
    @DataProvider(name = "SMSModuleTest")
    public Object[][] SMSModuleTest() throws SQLException, ClassNotFoundException {
        List<GetSMSRequestModel> getSMSRequestModels = getSMSRequestModels(queryOfSMSModuleTask);
        Object[][] data = new Object[getSMSRequestModels.size()][1];
        for (int i = 0; i < getSMSRequestModels.size(); i++) {
            data[i][0] = getSMSRequestModels.get(i);
        }
        return data;


    }

    @Test(dataProvider = "SMSModuleTest")
    public void testSMSModelWithDataProvider(GetSMSRequestModel getSMSRequestModel) throws SQLException, ClassNotFoundException {

        Response response = given()
                .headers("content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + getSMSRequestModel.getTelNumber());
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.jsonPath().getString("data.consentStatusId"), getSMSRequestModel.getConsent());

    }



    @DataProvider(name = "SMSModuleTestIndividual")
    public Object[][] SMSModuleTestIndividual() throws SQLException, ClassNotFoundException {
        List<GetSMSRequestModel> getSMSRequestModels = getSMSRequestModels(queryOfSMSModuleTask);
        Object[][] data = new Object[getSMSRequestModels.size()][3];
        for (int i = 0; i < getSMSRequestModels.size(); i++) {
            data[i][0] = getSMSRequestModels.get(i).getTelNumber();
            data[i][1] = getSMSRequestModels.get(i).getPersonId();
            data[i][2] = getSMSRequestModels.get(i).getConsent();

        }

        return data;

    }


    @Test(dataProvider = "SMSModuleTestIndividual")
    public void testSMSModelWithDataProviderInd(String telNum, String personId, String consent) throws SQLException, ClassNotFoundException {

        Response response = given()
                .headers("content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://10.195.105.66:7000/api/Consent?TelNumber=" + telNum);
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.jsonPath().getString("data.consentStatusId"), consent);


    }
}

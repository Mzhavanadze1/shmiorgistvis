import DataController.SMSModule;
import Models.SMSModule.GetConsent.GetSMSRequestModel;
import Models.SMSModule.PostConsent.PostSmsRequestModel;
import Steps.ConsentSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static DataController.SMSModule.*;
import static io.restassured.RestAssured.given;

public class Task4 {
    @DataProvider(name = "PostSMSModuleTest")
    public Object[][] PostSMSModuleTest() throws SQLException, ClassNotFoundException {
        List<PostSmsRequestModel> postSmsRequestModels = SMSModule.postSmsRequestModels(queryOfSMSModuleTaskPost);
        Object[][] data;
        data = SMSModule.postSmsRequest(postSmsRequestModels);


        return data;
    }

    ConsentSteps consentSteps = new ConsentSteps();

    @Test(dataProvider = "PostSMSModuleTest")
    public void SMSModulePostMethod(PostSmsRequestModel postSmsRequestModel) throws SQLException, ClassNotFoundException {
        consentSteps.postConsent(postSmsRequestModel);
    }
}
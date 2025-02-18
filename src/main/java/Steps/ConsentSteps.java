package Steps;

import Models.SMSModule.GetConsent.GetSMSRequestModel;
import Models.SMSModule.GetConsent.GetSMSResponseModel;
import Models.SMSModule.PostConsent.PostSmsRequestModel;
import io.restassured.response.Response;
import org.testng.Assert;

public class ConsentSteps {
    public GetSMSResponseModel getConsent(GetSMSRequestModel getSMSRequestModel){

        GetSMSResponseModel getSMSResponseModel=new GetSMSResponseModel();
        ConsentCalls consentCalls=new ConsentCalls();
        Response response= consentCalls.getConsentCalls(getSMSRequestModel);
        int statusCode=response.getStatusCode();
        if (statusCode == 200){
            getSMSResponseModel= response.as(GetSMSResponseModel.class);
            Assert.assertEquals(statusCode,200);
        }
        else {
            Assert.assertNotEquals(statusCode,200);
        }
        return getSMSResponseModel;
    }

    public void postConsent(PostSmsRequestModel postSmsRequestModel){

        ConsentCalls consentCalls=new ConsentCalls();
        Response response= consentCalls.postConsentCalls(postSmsRequestModel);
        int statusCode=response.getStatusCode();
        if (statusCode == 200){
            Assert.assertEquals(statusCode,200);
        }
        else {
            Assert.assertNotEquals(statusCode,200);
        }

    }

    public void compareConsent(GetSMSResponseModel getSMSResponseModel, PostSmsRequestModel postSmsRequestModel){
        Assert.assertEquals(getSMSResponseModel.getData().getConsentStatus(), postSmsRequestModel.getStatus());
    }
}

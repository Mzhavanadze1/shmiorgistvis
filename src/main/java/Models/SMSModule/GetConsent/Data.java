package Models.SMSModule.GetConsent;

public class Data {
    public int consentStatusId;
    public String consentStatus;
    public String dateUpdated;

    public int getConsentStatusId() {
        return consentStatusId;
    }

    public String getConsentStatus() {
        return consentStatus;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setConsentStatusId(int consentStatusId) {
        this.consentStatusId = consentStatusId;
    }

    public void setConsentStatus(String consentStatus) {
        this.consentStatus = consentStatus;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }


}

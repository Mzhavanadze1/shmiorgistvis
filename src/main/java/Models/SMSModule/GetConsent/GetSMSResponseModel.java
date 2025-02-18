package Models.SMSModule.GetConsent;

public class GetSMSResponseModel {
    public Data data;
    public Object message;
    public Object detailsMessage;
    public int externalState;
    public int state;
    public Object errorCode;

    public void setData(Data data) {
        this.data = data;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setDetailsMessage(Object detailsMessage) {
        this.detailsMessage = detailsMessage;
    }

    public void setExternalState(int externalState) {
        this.externalState = externalState;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public void setValidationErrors(Object validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Data getData() {
        return data;
    }

    public Object getMessage() {
        return message;
    }

    public Object getDetailsMessage() {
        return detailsMessage;
    }

    public int getExternalState() {
        return externalState;
    }

    public int getState() {
        return state;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public Object getValidationErrors() {
        return validationErrors;
    }

    public Object validationErrors;
}

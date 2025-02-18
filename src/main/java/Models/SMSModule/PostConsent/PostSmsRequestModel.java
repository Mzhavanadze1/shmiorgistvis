package Models.SMSModule.PostConsent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostSmsRequestModel {
    public String PersonId;
    public String TelNumber;
    public String status;
    public String channelId;


    public String getPersonId() {
        return PersonId;
    }


    public void setPersonId(String personId) {
       this.PersonId = personId;
    }


    public String getTelNumber() {
        return TelNumber;
    }


    public void setTelNumber(String telNumber) {
         this.TelNumber = telNumber;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getChannelId() {
        return channelId;
    }


    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


}

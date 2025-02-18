package DataController;

import DataBaseAccessSQL.DataBaseAccessSQL;
import Models.SMSModule.GetConsent.GetSMSRequestModel;
import Models.SMSModule.PostConsent.PostSmsRequestModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SMSModule {
    public static String queryOfSMSModuleTask = """
            use SMSModuleDB
            
            IF OBJECT_ID('tempbd..#temp') is not null
            DROP
              TABLE #temp
            
            
            select
              a.LenTelNum,
              max(a.PersonId) as PersonId into #temp
            from
              (
                select
                  len(a.TelNumber) as LenTelNum,
                  a.PersonId
                from
                  dbo.AdSMSConsent as a
                where
                  a.PersonId is not null
              ) as a
            group by
              a.LenTelNum
            
            select
              a.*,
              b.TelNumber,
              b.Consent
            from
              #temp as a
              inner join dbo.AdSMSConsent as b on a.PersonId = b.PersonId
              and a.LenTelNum = len(b.TelNumber)
            order by
              a.LenTelNum
            
            """;

    public static String queryOfSMSModuleTaskPost = """
            IF object_ID('tempbd..#tmp') is not null
                                        drop
                                          table #tempAL
                                        select
                                          *,
                                          ROW_NUMBER() over(
                                            order by
                                              a.personId
                                          ) as rowNumber into #tempAL
                                        from
                                          (
                                            SELECT
                                              top 3 a.personId,
                                              cast(a.TelNumber as varchar) as TelNumber,
                                              iiF (a.Consent = 1, 3, 1) as Consent,
                                              a.Channel
                                            FROM
                                              [SMSModuleDB].[dbo].[AdSMSConsent] as a
                                            where
                                              a.PersonId is not null
                                              and a.TelNumber is not null
                                              and a.Channel is not null
                                            union
                                            select
                                              top 1 a.PersonId,
                                              a.Contact,
                                              '3' as consent,
                                              '152021' as channel
                                            from
                                              CredoBnk.person.Contact as a
                                              left join [SMSModuleDB].[dbo].[AdSMSConsent] as b on a.PersonId = b.PersonId\s
                                              left join [SMSModuleDB].[dbo].[AdSMSConsent] as c on a.Contact = c.TelNumber\s
                                            where
                                              b.PersonId is null
                                              and c.PersonId is null
                                          ) as a
                                        update
                                          a
                                        set
                                          a.PersonId = null
                                        from
                                          #tempAL as a where a.rowNumber=1
                                        update
                                          a
                                        set
                                          a.TelNumber = null
                                        from
                                          #tempAL as a where a.rowNumber=2
                                        update
                                          a
                                        set
                                          a.Channel = null
                                        from
                                          #tempAL as a where a.rowNumber=3
                                        select
                                          *
                                        from
                                          #tempAL as a
            
            """;

    public static List<GetSMSRequestModel> getSMSRequestModels(String query) throws SQLException, ClassNotFoundException {
        List<GetSMSRequestModel> getSMSRequestModels = new ArrayList<>();
        Connection databBaseAccessSQL = DataBaseAccessSQL.connectSQL();
        ResultSet resultSet;

        PreparedStatement preparedStatement = databBaseAccessSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            GetSMSRequestModel getSMSRequestModel = new GetSMSRequestModel();
            getSMSRequestModel.setPersonId(resultSet.getString("PersonId"));
            getSMSRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            getSMSRequestModel.setConsent(resultSet.getString("Consent"));
            getSMSRequestModels.add(getSMSRequestModel);


        }


        return getSMSRequestModels;
    }

    public static List<PostSmsRequestModel> postSmsRequestModels(String query) throws SQLException, ClassNotFoundException {
        List<PostSmsRequestModel> postSmsRequestModels = new ArrayList<>();
        Connection dataBaseAccessSQL = DataBaseAccessSQL.connectSQL();
        ResultSet resultSet;

        PreparedStatement preparedStatement = dataBaseAccessSQL.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            PostSmsRequestModel postSMSRequestModel = new PostSmsRequestModel();
            postSMSRequestModel.setPersonId(resultSet.getString("PersonId"));
            postSMSRequestModel.setTelNumber(resultSet.getString("TelNumber"));
            postSMSRequestModel.setStatus(resultSet.getString("Consent"));
            postSMSRequestModel.setChannelId(resultSet.getString("Channel"));
            postSmsRequestModels.add(postSMSRequestModel);


        }
        return postSmsRequestModels;
    }
    public static Object[][] postSmsRequest(List<PostSmsRequestModel> postSmsRequestModels) throws SQLException{
        Object[][] data = new Object[postSmsRequestModels.size()][1];
        for (int i = 0; i < postSmsRequestModels.size(); i++) {
            data[1][0]=postSmsRequestModels.get(i);
        }
        return data;
    }


    }






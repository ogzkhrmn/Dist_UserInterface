package com.bank.accounting.moneytransfer;

import com.bank.accounting.model.AccountRequest;
import com.google.gson.Gson;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

@ManagedBean
@RequestScoped
public class EftController implements Serializable {

    private AccountRequest accountRequest = new AccountRequest();

    private Gson gson = new Gson();

    public String sendEft() {
        accountRequest.setType("EFT");
        try {
            callTcmb();
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    public String sendHavale() {
        accountRequest.setType("HAVALE");
        try {
            callTcmb();
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    public AccountRequest getAccountRequest() {
        return accountRequest;
    }

    public void setAccountRequest(AccountRequest accountRequest) {
        this.accountRequest = accountRequest;
    }

    private void callTcmb() throws Exception {
        URL url = new URL("http://tomataccount:8080/accountService/services/money/sendMoney");
        getResponse(url, gson.toJson(accountRequest));
    }

    private void getResponse(URL url, String s) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        wr.write(s);
        wr.flush();
        wr.close();
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception();
        }

    }
}

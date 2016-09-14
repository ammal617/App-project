package com.frsvarsmakten.app;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by julmustyo on 2014-05-06.
 */
public class post {
    public post(){

    }
    JSONObject jobj = null;
    //Connect to URL s, with body form params. Type decides between GET(0) and POST(1).
    public JSONObject connectPost(String s, List<NameValuePair> params, int type) {

        try {
            URL url = new URL(s);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(CertSSL.context.getSocketFactory());
//            conn.setReadTimeout(10000);
//            conn.setConnectTimeout(15000);
              conn.setRequestMethod("POST");
              conn.setDoInput(true);
              conn.setDoOutput(true);
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("number", "1287863"));
//            params.add(new BasicNameValuePair("firstname", "pelle"));
//            params.add(new BasicNameValuePair("lastname", "elle"));

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();

            conn.connect();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
            //String hej = br.readLine();
            //System.out.println(br.readLine());
            jobj = new JSONObject(br.readLine());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jobj;
    }
    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder sentToServer = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                sentToServer.append("&");

            sentToServer.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            sentToServer.append("=");
            sentToServer.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        //System.out.println(sentToServer.toString());
        return sentToServer.toString();
    }
}

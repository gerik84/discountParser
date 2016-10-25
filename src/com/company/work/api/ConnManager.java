package com.company.work.api;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Pavel G on 14.10.2016.
 */
public class ConnManager {

    public static ConnManager newInstance() {
        return new ConnManager();
    }

    private enum METHOD {
        GET, POST
    }

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0";

    private Proxy m_proxy;
    private Response m_listener;
    private ResponseIO m_listenerIo;
    private HttpURLConnection m_con;
    private METHOD m_method = METHOD.GET;
    private URL m_url;
    private String mUserAgent = USER_AGENT;
    private int m_connection_timeout = 2000;


    private ConnManager() {
        //singleton
    }

    public ConnManager timeout(int time) {
        m_connection_timeout = time;
        return this;
    }

    public ConnManager proxy(String ip, int port) {
        m_proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        return this;
    }

    public ConnManager proxy(Proxy proxy) {
        m_proxy = proxy;
        return this;
    }

    public ConnManager listener(Response listener) {
        m_listener = listener;
        return this;
    }

    public ConnManager listener(ResponseIO listener) {
        m_listenerIo = listener;
        return this;
    }

    public ConnManager method(METHOD method) {
        m_method = method;
        return this;
    }

    public ConnManager url(String url) throws MalformedURLException {
        m_url = new URL(url);
        return this;
    }

    public ConnManager userAgent(String url) {
        mUserAgent = url;
        return this;
    }

    public void send() throws IOException {

        InputStream stream = sendSync();
        if (m_listenerIo != null) {
            m_listenerIo.onSuccess(m_con.getResponseCode(), stream);
        } else if (m_listener != null) {
            m_listener.onSuccess(m_con.getResponseCode(), iSteamToString(stream));
        }
    }

    public InputStream sendSync() throws IOException {

        System.out.println("Request URL: " + m_url);

        if (m_url == null)
            throw new NullPointerException("URL NOT SET");


        m_con = openConnection(m_proxy);
        if (m_con instanceof HttpsURLConnection)
            trustEveryone();

        m_con.setConnectTimeout(m_connection_timeout);
        m_con.setRequestMethod(m_method.toString());

        m_con.setRequestProperty("Accept", "*/*");
        // m_con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        // m_con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        m_con.setRequestProperty("Cookie", "_ga=GA1.2.1160797675.1475656592; location_id=1814; _gat=1");
        m_con.setRequestProperty("Host", "5ka.ru");
        m_con.setRequestProperty("Referer", "https://5ka.ru/special_offers/?records_per_page=15&page=1");
        m_con.setRequestProperty("User-Agent", mUserAgent);
        return m_con.getInputStream();
    }

    private <T extends HttpURLConnection> HttpURLConnection openConnection(Proxy proxy) throws IOException {
        URLConnection connection;
        if (proxy != null)
            connection = m_url.openConnection(proxy);
        else
            connection = m_url.openConnection();

        return isHttps(connection) ? (HttpsURLConnection) connection : (HttpURLConnection) connection;
    }

    public interface Response {
        void onSuccess(int code, String response);
    }

    public interface ResponseIO {
        void onSuccess(int code, InputStream is);
    }

    private boolean isHttps(URLConnection connection) {
        return connection instanceof HttpsURLConnection;
    }

    public static String iSteamToString(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(is, "utf-8"));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private void trustEveryone() {
        if (!(m_con instanceof HttpsURLConnection))
            throw new ClassCastException("connection not class HttpsURLConnection");

        try {
            ((HttpsURLConnection) m_con).setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            ((HttpsURLConnection) m_con).setSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

//    private void trustEveryone() {
//        try {
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }});
//            SSLContext context = SSLContext.getInstance("TLS");
//            context.init(null, new X509TrustManager[]{new X509TrustManager(){
//                public void checkClientTrusted(X509Certificate[] chain,
//                                               String authType) throws CertificateException {}
//                public void checkServerTrusted(X509Certificate[] chain,
//                                               String authType) throws CertificateException {}
//                public X509Certificate[] getAcceptedIssuers() {
//                    return new X509Certificate[0];
//                }}}, new SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(
//                    context.getSocketFactory());
//        } catch (Exception e) { // should never happen
//            e.printStackTrace();
//        }
//    }

}

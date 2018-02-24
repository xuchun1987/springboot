package boot.https.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static KeyStore keyStore =null;
    private static KeyStore trustStore=null;

    static{
        InputStream ksIn=null;
        InputStream tsIn=null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            trustStore = KeyStore.getInstance("JKS");
            ksIn=HttpClientUtil.class.getClassLoader().getResourceAsStream("cert/sandoauth_client.p12");
            tsIn=HttpClientUtil.class.getClassLoader().getResourceAsStream("cert/sandoauth_client.jks");
			 /*ksIn=new FileInputStream(new File(KEY_STORE_CLIENT_PATH));
			 tsIn=new FileInputStream(new File(KEY_STORE_TRUST_PATH));*/
            keyStore.load(ksIn, "654321".toCharArray());
            trustStore.load(tsIn, "654321".toCharArray());
        } catch (KeyStoreException e) {
            logger.error("error:",e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("error:",e);
        } catch (CertificateException e) {
            logger.error("error:",e);
        } catch (IOException e) {
            logger.error("error:",e);
        }finally {
            try {
                ksIn.close();
            } catch (Exception e) {
                logger.error("error:",e);
            }
            try {
                tsIn.close();
            } catch (Exception e) {
                logger.error("error:",e);
            }
        }
    }
    public static String post(String url,Map<String,String> params){
        CloseableHttpClient client =null;
        CloseableHttpResponse res=null;
        try {
            SSLContext sslcontext  = SSLContexts.custom().loadKeyMaterial(keyStore,"654321".toCharArray())
                    .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
                    null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

            HttpPost httpPost = new HttpPost(url);
            client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            RequestConfig requestConfig = RequestConfig.copy(reqConfig()).build();
            httpPost.setConfig(requestConfig);

            if(params!=null&&!params.isEmpty()){
                List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
                for(Iterator<String> it = params.keySet().iterator();it.hasNext();){
                    String key=it.next();
                    paramPairs.add(new BasicNameValuePair(key, params.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramPairs, "UTF-8");
                httpPost.setEntity(entity);
            }
            res = client.execute(httpPost);
            HttpEntity respEntity = res.getEntity();
            if(respEntity!=null){
                logger.info("----------------HttpClientUtil-post--------------");
                logger.info("----------------请求url:"+url);
                logger.info("----------------请求param:"+WebUtil.parseFastJson(params));
                logger.info("----------------请求返回状态码："+res.getStatusLine().getStatusCode());
                String result=EntityUtils.toString(respEntity, "UTF-8");
                logger.info("----------------请求返回内容："+result);
                return result;
            }
        } catch (IOException e) {
            logger.error("error:",e);
        } catch (UnrecoverableKeyException e) {
            logger.error("error:",e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("error:",e);
        } catch (KeyStoreException e) {
            logger.error("error:",e);
        } catch (KeyManagementException e) {
            logger.error("error:",e);
        } finally {

            try {
                if(res!=null){
                    res.close();
                }
                if(client!=null){
                    client.close();
                }
            } catch (IOException e) {
                logger.error("error:",e);
            }
        }
        return null;

    }

    public static RequestConfig reqConfig(){
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(6000)
                .setConnectTimeout(6000)
                .setConnectionRequestTimeout(6000)
                .build();
        return defaultRequestConfig;
    }

}

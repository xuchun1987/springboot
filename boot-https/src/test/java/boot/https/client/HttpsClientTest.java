package boot.https.client;

import boot.https.util.AsyncHttpClientUtil;
import boot.https.util.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;

public class HttpsClientTest {

    @Test
    public void httpsdo1(){
        String url="https://localhost:8443/index";
        AsyncHttpClientUtil.post(url,new HashMap<String,Object>());
    }

    @Test
    public void httpsdo2(){
        String url="https://localhost:8443/index";
        HttpClientUtil.post(url,null);
    }
}

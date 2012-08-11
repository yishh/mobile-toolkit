package mobi.app.toolkit.apple.impl;

import com.google.common.collect.ImmutableMap;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpProviderConfig;
import com.ning.http.client.Response;
import com.ning.http.client.providers.netty.NettyAsyncHttpProviderConfig;
import mobi.app.toolkit.apple.IapReceipt;
import mobi.app.toolkit.apple.IapTools;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * User: thor
 * Date: 12-8-11
 * Time: 3:46 AM
 */
public class DefaultIapTools implements IapTools {
    Logger logger = LoggerFactory.getLogger(DefaultIapTools.class);
    final boolean production;
    private int requestTimeout = 5000;
    AsyncHttpClient asyncHttpClient;
    ObjectMapper mapper;
    public static final String BUY_URL = "https://buy.itunes.apple.com/verifyReceipt";
    public static final String SANDBOX_URL = "https://sandbox.itunes.apple.com/verifyReceipt";

    public DefaultIapTools(boolean production) {
        this.production = production;
        AsyncHttpProviderConfig c = new NettyAsyncHttpProviderConfig();
        AsyncHttpClientConfig.Builder b = new AsyncHttpClientConfig.Builder();
        b.setAllowPoolingConnection(true);
        b.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
        b.setAsyncHttpClientProviderConfig(c);
        mapper = new ObjectMapper();
        asyncHttpClient = new AsyncHttpClient(b.build());
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public boolean isProduction() {
        return production;
    }

    @Override
    public IapReceipt validate(String receipt) {
        try {
            String json = mapper.writeValueAsString(ImmutableMap.of(
                    "receipt-data", receipt
            ));
            String url = BUY_URL;
            if (!production)
                url = SANDBOX_URL;
            Future<Response> f = asyncHttpClient.preparePost(url).setBody(json).execute();
            Response r = f.get(requestTimeout, TimeUnit.MILLISECONDS);
            if (r.getStatusCode() == 200) {
                String body = r.getResponseBody("utf-8");
                logger.debug("iap validate response: \n {}", body);
                return mapper.readValue(body, IapReceipt.class);
            }
        } catch (JsonMappingException e) {
            logger.error("iap validate error: ", e);
        } catch (JsonGenerationException e) {
            logger.error("iap validate error: ", e);
        } catch (IOException e) {
            logger.error("iap validate error: ", e);
        } catch (InterruptedException e) {
            logger.error("iap validate error: ", e);
        } catch (ExecutionException e) {
            logger.error("iap validate error: ", e);
        } catch (TimeoutException e) {
            logger.error("iap validate error: ", e);
        }
        return null;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }
}

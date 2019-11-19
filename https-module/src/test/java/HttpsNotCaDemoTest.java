
import com.pay.bean.req.ReqHeader;
import com.pay.bean.req.model.ReqModel;
import com.pay.util.OkHttpClientUtil;
import com.pay.util.XmlBeanConvetorUtil;
import okhttp3.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *  @Author: XueYongKang
 *  @Description:    https示例(不校验服务端的身份， 即:不验证ca)
 *  @Data: 2019/11/19 15:58
 */
public class HttpsNotCaDemoTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpsNotCaDemoTest.class);



    private static OkHttpClient client;

    static {
        try {
            client = OkHttpClientUtil.getHttpsClient();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("init OkHttpClient error!", e);
        }
    }

    /**
     * get-同步
     */
//    @Test
    public void getTestOne() throws IOException {
        // 地址必须要写上http/https
//        String httpUrl = "https://127.0.0.1:9527/get/test?name=JustryDeng";
        String httpUrl = "https://127.0.0.1:7070/pay/guangdaPay?key=%E6%88%91%E6%98%AF%E4%B8%BB%E4%BD%93";
        // 默认即为get请求
        Request request = new Request.Builder()
                // 注意: header添加的是 唯一的 键值对
                //      .addHeader添加的是 同key,多value的键值
                //      header相当于Map,addHeader相当于Multimap
                //.header("key1", "value1")
                //.addHeader("key2", "value2")
                .url(httpUrl)
                .build();
        Response response = client.newCall(request).execute();
        // 响应头
        // response有多个提取想用header的方法，下面列举的只是其一
        // response.headers();
        // 响应体
        ResponseBody responseBody = response.body();
        // 将响应体转换为 字符串
        String responseStr = responseBody == null ? null : responseBody.string();
        /*
         * 响应体的 string() 方法对于小文档来说十分方便、高效。
         * 但是如果响应体太大（超过1MB），应避免使用 string()方法 ，
         * 因为他会将把整个文档加载到内存中。对于超过1MB的响应body，
         * 应使用流的方式来处理body。
         */
        // 将响应体转换为 流
        // responseBody.byteStream();
        logger.info("响应内容为 -> {}", responseStr);
    }

    /**
     * get-异步
     *
     * 注:在一个工作线程中下载文件，当响应可读时回调Callback接口。读取
     *    响应时会阻塞当前线程。OkHttp现阶段不提供异步api来接收响应体。
     */
//    @Test
    public void getTestTwo() throws InterruptedException {
        // 地址必须要写上http/https
        String httpUrl = "https://127.0.0.1:9527/get/test?name=邓沙利文";
        // 默认即为get请求
        Request request = new Request.Builder()
                // 注意: header添加的是 唯一的 键值对
                //      .addHeader添加的是 同key,多value的键值
                //      header相当于Map,addHeader相当于Multimap
                //.header("key1", "value1")
                //.addHeader("key2", "value2")
                .url(httpUrl)
                .build();
        //异步
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("请求失败！请求相关信息为 -> {}!", call.request(), e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body() == null ? null : response.body().string();
                logger.info("请求成功！响应Response为 -> {}!, 响应体数据为 -> {}！", response, responseStr);
            }
        });
        // 为了观察异步线程的日志输出， 这里不妨让当前线程休眠几秒
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * post-同步-提交请求体数据为json等类型的数据
     */
//    @Test
    public void postTestOne() throws Exception {
//        MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
        MediaType MEDIA_TYPE_JSON = MediaType.parse("text/xml; charset=utf-8");
//        MediaType MEDIA_TYPE_JSON = MediaType.parse("text/html; charset=utf-8");
        // 地址必须要写上http/https
//        String url = "https://127.0.0.1:9527/post/test/one";
        String url = "https://127.0.0.1:7070/pay/guangdaPay?key=%E6%88%91%E6%98%AF%E4%B8%BB%E4%BD%93";
//        String url = "http://127.0.0.1:7070/pay/guangdaPay?key=%E6%88%91%E6%98%AF%E4%B8%BB%E4%BD%93";

//        User user = User.builder().name("张三").age(12).gender("男").motto("瓜兮兮~").build();
//        String jsonStr = jacksonMapper.writeValueAsString(user);
        // 请求体数据类型任意，只要保证MediaType与其对应就行
        // 如: MediaType.parse("application/json; charset=utf-8");
        //     MediaType.parse("text/html; charset=utf-8");
        //     MediaType.parse("text/xml; charset=utf-8");
        //     ……
//        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, jsonStr);
        ReqModel reqModel = new ReqModel();
        reqModel.setBody("i am body");
//        reqModel.setHead(new ReqHeader("1","1","1","1","1","1"));
        reqModel.setHead(new ReqHeader());

        String xml = XmlBeanConvetorUtil.convertToXml(reqModel);
//        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON,xml );
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON,xml );
//        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON,"" );

        Request request = new Request.Builder()
                // 注意: header添加的是 唯一的 键值对
                //      .addHeader添加的是 同key,多value的键值
                //      header相当于Map,addHeader相当于Multimap
                //.header("key1", "value1")
                //.addHeader("key2", "value2")
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(request.toString());

        // 响应头
        // response有多个提取想用header的方法，下面列举的只是其一
        // response.headers();
        ResponseBody responseBody = response.body();
        // 将响应体转换为 字符串
        String responseStr = responseBody == null ? null : responseBody.string();
        // 将响应体转换为 流
        // responseBody.byteStream();
        System.out.println(responseStr);
        logger.info("响应内容为 -> {}", responseStr);
    }


}
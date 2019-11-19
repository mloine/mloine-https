package com.mloine.https.controller;

import com.pay.bean.req.model.ReqModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 *  @Author: XueYongKang
 *  @Description:   待支付测试接口
 *  @Data: 2019/11/19 11:27
 */


@Api(value = "/pay",description = "待支付模块")
@RestController
@RequestMapping("/pay")
public class PayController {

    @ApiOperation(value = "光大待支付功能测试接口")
    @PostMapping(value = "/guangdaPay")
    public String guangdaPay(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam @ApiParam(value = "主题内容") String key){

        InputStream ins = null;
        try {
            ins = request.getInputStream();
            byte[] rebyte = readStream(ins);
            String remess = new String(rebyte);
            System.out.println("XML报文内容为：" + remess);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        ReqModel reqModel = new ReqModel();
        reqModel.setBody(key);
        return reqModel.toString();
    }

    /**
     * @功能 读取流
     * @param inStream
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }


}

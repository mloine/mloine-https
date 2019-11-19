package com.pay.bean.req.model;

import com.pay.bean.req.ReqHeader;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 *  @Author: XueYongKang
 *  @Description:    测试模型
 *  <?xml version="1.0" encoding="ISO-8859-1" ?>
 * <In>
 *     <Head>
 *         <ChnlNo>渠道号</ChnlNo>
 *         <FTranCode>交易码</FTranCode>
 *         <InstID>机构号</InstID>
 *         <TrmSeqNum>终端流水号</TrmSeqNum>
 *         <TranDateTime>交易日期时间</TranDateTime>
 *         <ErrCode>错误码</ErrCode>
 *     </Head>
 *     <Body>
 *         业务字段
 *     </Body>
 * </In>
 *
 *  @Data: 2019/11/18 17:52
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "head",
        "body"
})
@XmlRootElement(name = "In")
public class ReqModel  implements Serializable  {

    private static final long serialVersionUID = -6625524094225393474L;

    @XmlElement(required = true,name="Head")
    private ReqHeader head = new ReqHeader();

    @XmlElement(required = true,name="Body")
    private  String body = "";

    @Override
    public String toString() {
        return "ReqModel{" +
                "head=" + head +
                ", body='" + body + '\'' +
                '}';
    }
}

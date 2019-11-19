package com.pay.bean.res;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  @Author: XueYongKang
 *  @Description:   统一响应报文头部
 *          <Head>
 *  *         <ChnlNo>渠道号</ChnlNo>
 *  *         <FTranCode>交易码</FTranCode>
 *  *         <InstID>机构号</InstID>
 *  *         <TrmSeqNum>终端流水号</TrmSeqNum>
 *  *         <TranDateTime>交易日期时间</TranDateTime>
 *  *         <ErrCode>错误码</ErrCode>
 *  *     </Head>
 *  @Data: 2019/11/19 13:05
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Head")
@Getter
@Setter
public class ResHeader {

    /**
     * 渠道号
     */
    @XmlElement(required = true,name="ChnlNo")
    private String ChnlNo = "";

    /**
     * 交易码
     */
    @XmlElement(required = true,name="FTranCode")
    private String FTranCode = "";

    /**
     * 机构号
     */
    @XmlElement(required = true,name="InstID")
    private String InstID = "";

    /**
     * 终端流水号
     */
    @XmlElement(required = true,name="TrmSeqNum")
    private String TrmSeqNum = "";

    /**
     * 交易日期时间:YYYYMMDDhhmmss
     */
    @XmlElement(required = true,name="TranDateTime")
    private String TranDateTime = "";

    /**
     * 错误码
     */
    @XmlElement(required = true,name="ErrCode")
    private String ErrCode = "";

    public ResHeader() {
    }

    /**
     *
     * @param chnlNo 渠道号
     * @param FTranCode 交易码
     * @param instID 机构号
     * @param trmSeqNum 终端流水号
     * @param tranDateTime 交易日期时间:YYYYMMDDhhmmss
     * @param errCode 错误码
     */
    public ResHeader(String chnlNo, String FTranCode, String instID, String trmSeqNum, String tranDateTime, String errCode) {
        this.ChnlNo = chnlNo;
        this.FTranCode = FTranCode;
        this.InstID = instID;
        this.TrmSeqNum = trmSeqNum;
        this.TranDateTime = tranDateTime;
        this.ErrCode = errCode;
    }

    @Override
    public String toString() {
        return "ReqHeader{" +
                "ChnlNo='" + ChnlNo + '\'' +
                ", FTranCode='" + FTranCode + '\'' +
                ", InstID='" + InstID + '\'' +
                ", TrmSeqNum='" + TrmSeqNum + '\'' +
                ", TranDateTime='" + TranDateTime + '\'' +
                ", ErrCode='" + ErrCode + '\'' +
                '}';
    }
}

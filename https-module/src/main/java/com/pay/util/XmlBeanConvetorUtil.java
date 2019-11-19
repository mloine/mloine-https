package com.pay.util;

import com.pay.bean.req.ReqHeader;
import com.pay.bean.req.model.ReqModel;
import com.pay.bean.res.model.ResModel;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 *  @Author: XueYongKang
 *  @Description:    dto与xml格式转化工具
 *  @Data: 2019/11/18 17:40
 */
public class XmlBeanConvetorUtil {

//    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_ENCODING = "ISO-8859-1";

    public static String format(String unformattedXml) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(unformattedXml));
            final Document document = db.parse(is);
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * pojo转换成xml 默认编码UTF-8
     *
     * @param obj 待转化的对象
     * @return xml格式字符串
     * @throws Exception JAXBException
     */
    public static String convertToXml(Object obj) throws Exception {
        return convertToXml(obj, DEFAULT_ENCODING);
    }

    /**
     * pojo转换成xml
     *
     * @param obj 待转化的对象
     * @param encoding 编码
     * @return xml格式字符串
     * @throws Exception JAXBException
     */
    public static String convertToXml(Object obj, String encoding) throws Exception {
        String result = null;

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();

        // 指定是否使用换行和缩排对已编组 XML 数据进行格式化的属性名称。
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        //去除是否指定独立xml
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        //手动填充
//        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
        writer.append("<?xml version=\"1.0\" encoding=\""+DEFAULT_ENCODING+"\"?>" + "\n");
        marshaller.marshal(obj, writer);

        result = writer.toString();

        return result;
    }

    /**
     * xml转换成JavaBean
     *
     * @param xml xml格式字符串
     * @param t 待转化的对象
     * @return 转化后的对象
     * @throws Exception JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToJavaBean(String xml, Class<T> t) throws Exception {
        T obj = null;
        JAXBContext context = JAXBContext.newInstance(t);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        obj = (T) unmarshaller.unmarshal(new StringReader(xml));
        return obj;
    }

    public static void main(String[] args) throws Exception {
        ReqModel reqModel = new ReqModel();
        reqModel.setBody("i am body");
//        reqModel.setHead(new ReqHeader("1","1","1","1","1","1"));
        reqModel.setHead(new ReqHeader());

        String xml = XmlBeanConvetorUtil.convertToXml(reqModel);

        System.out.println(xml);
        System.out.println("-----------------------------分割线------------------------------");

        ResModel reqModel1 = XmlBeanConvetorUtil.convertToJavaBean(xml, ResModel.class);
        System.out.println(reqModel1);
    }
}

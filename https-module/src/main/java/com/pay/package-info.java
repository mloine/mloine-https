package com.pay;

/**
 *  @Author: XueYongKang
 *  @Description:
 *
 *          1.工具包中的pk文件 为本地测试用秘钥文件。生产 测试 环境需要另外生成
 *          keytool 为jdk自带的工具 用于生成keystore及证书，进而提供出供HTTPS访问的接口
 *          keystore主要用来存放密钥与证书，它也可以用来生成证书。
 *
 *          服务器端：私钥
 *          例子：
 *             alias 别名(mloine_ca)
 *             keypass 密码(mloine@123*Ds)
 *             keyalg 秘钥证书类型(RSA)
 *             keysize 秘钥长度(2048)
 *          keytool -genkey -alias mloine_ca -keypass mloine@123*Ds -keyalg RSA -keysize 2048 -validity 360 -storetype PKCS12 -keystore E:/pkStore/mloinestore.p12 -storepass mloine@123*Ds -dname "CN=xueyongkang,OU=aspire,O=aspire,L=shanghai,S=shanghai,C=cn" -ext SAN=IP:127.0.0.1,DNS:localhost
 *
 *          客户端:公钥  这一步需要输入之前生成mloinestore.p12所设置的密码
 *          keytool -export -keystore E:/pkStore/mloinestore.p12 -alias mloine_ca -file E:/pkStore/mloine.crt -rfc
 *  @Data: 2019/11/19 17:36
 */
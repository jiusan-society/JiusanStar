<h2 align="center">Enable HTTPS in Payara / GlassFish Server</h2>

### 参考资料：

  * http://blog.payara.fish/securing-payara-server-with-custom-ssl-certificate
  * http://blog.payara.fish/administering-payara-server-with-the-cli
  
### 实验环境：
* macOS Sierra 10.12.6
* Oracle JDK 1.8.0_73
* Payara 4.1.2.173 Full

### 实验过程：

首先需要说明的是，无论是自制的证书还是由 CA 颁发的证书，在 Payara 上的设置步骤都是一样的。这里将使用 **自制证书** 来逐步描述配置的环节

**前置作业**

1. 使用 openssl 生成公钥和秘钥（Windows 下使用的是 Microsoft Management Console），在这里我生成 3MEdu.cer 和 3MEdu.key （如果是 CA 颁发的证书，则普遍提供的是 bundle file，格式有多种，比如 PKCS12，生成的文件形如 3MEdu.p12）

   `openssl req -x509 -newkey rsa:4096 -keyout 3MEdu.key -out 3MEdu.crt -days 365`

2. 将这两个文件（或那个 3MEdu.p12）放置到 <Payara_Install_Dir>/glassfish/domains/domain1/config 文件夹下

3. 进入后续步骤前推荐先关闭 payara

**后续操作**（本实验中均在 <Payara_Install_Dir>/glassfish/domains/domain1/config 下执行命令行操作）

1. 推荐更改 payara 的 master password，默认为 changeit，答案找寻自 http://blog.payara.fish/administering-payara-server-with-the-cli 此处的密码后续要用到

   `asadmin change-master-password`

2. 将证书写入 keystore.jks

   * 首先，将前面提到的 3MEdu.cer 和 3MEdu.key 打包成为 PKCS12 bundle file，这里我取的名字为 3MEdu.p12
   
     `openssl pkcs12 -export -in 3MEdu.crt -inkey 3MEdu.key -out 3MEdu.p12 -name 3medu_cert`

   * 然后，使用 keytool 将其写入 keystore.jks 中
   
     `keytool -importkeystore -destkeystore keystore.jks -srckeystore 3MEdu.p12 -srcstoretype PKCS12 -alias 3medu_cert`

3. 将证书加入可信列表当中，即写入 cacerts.jks

   `keytool -importkeystore -destkeystore cacerts.jks -srckeystore 3MEdu.p12 -srcstoretype PKCS12 -alias mydomain_certificate`

4. 在 Admin GUI 中开启 HTTP Listener Security（本实验省略在命令行中使用 asadmin 的方式）

   * Configurations -> server-config -> HTTP Service -> HTTP Listeners
   
   * http-listener-x General 标签页开启 Security，SSL 页面填写 Certificate NickName（此处 http-listener-1，即用于监听 8080 端口的监听器，修改失败，提示已有 ssl element，由于时间原因暂时放弃，TODO。admin-listener 与 http-listener-2 均正常）
   
5. Restart Payara 以使更改生效，并查看效果

**突然变卦——使用 CA 颁布的证书（说好的本实验只用自制证书呢？）**

在我的国际版阿里云香港服务器上进行实验

试验环境：Ubuntu 16.04，Oracle JDK 1.8.0_144，Payara 4.1.2.172 Full

简述步骤：通过 certbot 获取 Let's Encrypt 提供的免费证书，获取的证书和秘钥分别为 cert.pem 与 privkey.pem，使用 openssl 将两者 bundle 成 PKCS12 格式，依次写入 keystore.jks，cacerts.jks，然后于 Admin GUI 中配置 http listener 信息，最后重启 Payara

所以，首尾呼应，无论是自制证书还是 CA 颁发证书，两者仅在取得方式上有所区别，其他步骤基本一致

### 总结：

#### 核心步骤：

1. 取得证书与秘钥（自制或由 CA 颁布）
2. 将证书写入到 Payara 的 keystore.jks 中
3. 将证书写入到 Payara 的 cacerts.jks 中，即添加到可信列表中
4. 在 Admin GUI 中配置 HTTP Listener 信息

#### 注意事项：

* master password 默认为 changeit，具体参见

  * http://blog.payara.fish/administering-payara-server-with-the-cli

* 证书的密码必须和上述的 master password 一致，否则将报 “Cannot recover key” 错误，Tomcat 官方已证实这个 bug，具体参见   

  * https://stackoverflow.com/questions/15967650/caused-by-java-security-unrecoverablekeyexception-cannot-recover-key
  * https://tomcat.apache.org/tomcat-6.0-doc/ssl-howto.html#Prepare_the_Certificate_Keystore

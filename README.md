**overview**

  This toolkit is for networking mobile application develop .
  
  We often need to use apple's push notification service and in app pay service ,
  and some application may use amazon's aws service.
  
  I found there is no simple and easy way to use this service, so i create this toolkit,It just wrap the really sdk for this service , such as wrap [JAVAPNS](http://code.google.com/p/javapns) for apns,but make it more simple and more easy .

  Enjoy it!


## feature

 1. APNS

 Apple push notification service

<pre><code class="java">

    
       //setup apns by your keystore, key password and production ,false will use sandbox env
        ApnsTools apnsTools = new DefaultApnsTools("aps.p12", "password", false);
        apnsTools.alert("message", "device token");
        
        //async apns , should set async thread number
        AsyncApnsTools asyncApnsTools = new DefaultAsyncApnsTools("aps.p12", "password", false, 12);
        asyncApnsTools.alert("message", "device token");

        // you can setup a monitor for async apns tool.such as log monitor for print push result log. 2 is the log interval. TimeUnit is second
        AsyncNotificationMonitor monitor = new LogNotificationMonitor(2, asyncApnsTools);

</code></pre>


Suggest config this in spring:


		<bean id="apnsTools" class="mobi.app.toolkit.apple.impl.DefaultApnsTools">
       		<constructor-arg index="0" value="${aps.key}"/>
       		<constructor-arg index="1" value="${aps.password}"/>
       		<constructor-arg index="2" value="${aps.production}"/>
   		</bean>
   		<bean id="asyncApnsTools" class="mobi.app.toolkit.apple.impl.DefaultAsyncApnsTools">
        	<constructor-arg index="0" value="${aps.key}"/>
        	<constructor-arg index="1" value="${aps.password}"/>
        	<constructor-arg index="2" value="${aps.production}"/>
        	<constructor-arg index="3" value="${aps.threadNumber}"/>
    	</bean>
    	<bean id="logMonitor" class="mobi.app.toolkit.apple.impl.LogNotificationMonitor">
        	<constructor-arg index="0" value="2"/>
        	<constructor-arg index="1" ref="asyncApnsTools"/>
    	</bean>
    	
    	

 2. IAP

 Apple in app pay
 
 <pre><code>
 	   //Setup iap tools,  false will use sandbox env 
        IapTools iapTools = new DefaultIapTools(false);
        IapReceipt receipt = iapTools.validate("your pay receipt");
        System.out.print(receipt.getStatus());
 </code></pre>
 
 
 Suggest config this in spring:
 
 	 <bean id="iapTools" class="mobi.app.toolkit.apple.impl.DefaultIapTools">
        <constructor-arg value="${iap.production}"/>
    </bean>
    

 3. AWS S3

 Amazon s3
 
  <pre><code> 
        AwsS3Tools s3Tools = new DefaultS3Tools("your accessKey", "your accessSecret");
        String url  = s3Tools.upload("bucket", "key", new byte[]{}, "image/png");
        System.out.print(url); 
  </code></pre>
  
Suggest config this in spring:
 
 	<bean id="amazonS3Client" class="mobi.app.toolkit.aws.impl.DefaultS3Tools">
        <constructor-arg index="0" value="${s3.accessKey}"/>
        <constructor-arg index="1" value="${s3.accessSecret}"/>
    </bean> 
    

 4. AWS Mail

 Amazon mail
 
<pre><code> 
    AwsMailTools mailTools = new DefaultMailTools("your accessKey", "your accessSecret", "your admin mail");
    mailTools.sendMail("to address", "title", "body");
</code></pre>

Suggest config this in spring:

    <bean id="awsMailTools" class="mobi.app.toolkit.aws.impl.DefaultMailTools">
        <constructor-arg index="0" value="${aws.accessKey}"/>
        <constructor-arg index="1" value="${aws.accessSecret}"/>
        <constructor-arg index="2" value="${aws.adminMail}"/>
    </bean>


## install

 You can use this by maven.  At first should add repository:

         <repository>
             <id>51APP 3RD</id>
             <name>51APP 3RD</name>
             <url>http://51app.mobi:8081/nexus/content/repositories/thirdparty/</url>
         </repository>

         <repository>
             <id>51APP RELEASE</id>
             <name>51APP RELEASE</name>
             <url>http://51app.mobi:8081/nexus/content/repositories/releases/</url>
         </repository>


  then add dependency:

          <dependency>
              <groupId>mobi.51app</groupId>
              <artifactId>mobile-toolkit</artifactId>
              <version>1.0</version>
          </dependency>


## dependency

Because this toolkit just a simple wrap, so it needs some dependency . 
        <dependency>
             <groupId>com.google.guava</groupId>
             <artifactId>guava</artifactId>
             <version>10.0.1</version>
        </dependency>

        // logback
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.6.5</version>
        </dependency>

        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.0.6</version>
        </dependency>

        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.0.6</version>
        </dependency>
     
        // for apns
        <dependency>
            <groupId>javapns</groupId>
            <artifactId>javapns</artifactId>
            <version>2.2</version>
        </dependency>

        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.16</version>
        </dependency>

        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-jdk16</artifactId>
            <version>1.46</version>
        </dependency>

       //for in app pay
       <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>1.9.5</version>
        </dependency>
        
        <dependency>
            <groupId>com.ning</groupId>
            <artifactId>async-http-client</artifactId>
            <version>1.6.5</version>
        </dependency>

       // for aws mail ans aws s3
        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-java-sdk</artifactId>
            <version>1.3.11</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>3.0.5.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>3.0.5.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>3.0.5.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>javax.mail</groupId>
            <artifactId>mailapi</artifactId>
            <version>1.4.3</version>
        </dependency>

        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>2.2</version>
        </dependency>

       





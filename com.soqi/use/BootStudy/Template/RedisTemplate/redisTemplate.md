### 1.添加Redis依赖：在您的pom.xml文件中添加Spring Data Redis依赖项：

````
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
````
### 2.配置Redis连接信息：在application.properties或application.yml文件中配置Redis连接信息，包括主机名、端口、密码等：
````
properties
Copy code
spring.redis.host=your_redis_host
spring.redis.port=your_redis_port
spring.redis.password=your_redis_password
````
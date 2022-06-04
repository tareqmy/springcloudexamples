# Getting Started

### todo

- security -> apikey, oauth2, openid, amazon cognito, github/google auth
- cloud -> eureka/zookeeper/consul, discovery/registry-server, config-server,
  gateway(load balancing and rate limiting), ribbon, openfeign, aws xray/zipkin/sleuth
- message/events -> rabbitmq, kafka, cloud-bus, cloud-stream
- auto reload config change through eventbus
- elasticsearch, kibana, logstash
- move the service data into postgresql and data jpa
- second level cache caffeine, hazecast, redis
- openapi3

### Spring Security notes

authentication can be done by several mechanisms
also the application can have multiple different authentication mechanisms
authentication filter chain intercepts all authentication requests

there is an authenticationManager that manages all available providers
one implementation for this is providerManager
authentication providers -> authenticate() and supports()
manager finds the appropriate provider and delegates authentication 
userDetailsService takes username finds in the store and returns userDetails object

when auth is successful, authentication object is saved in the securityContext
another filter preserves the authentication object in the session and for consecutive requests
fetches the saved authentication and pushes to the securityContext.
the securityContext is preserved in securityContextHolder

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#using-boot-devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


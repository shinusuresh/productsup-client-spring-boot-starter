# Spring boot library for Products up platform integration

[![Build status](https://github.com/shinusuresh/productsup-client-spring-boot-starter/actions/workflows/build.yml/badge.svg)](https://github.com/shinusuresh/productsup-client-spring-boot-starter/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=shinusuresh_productsup-client-spring-boot-starter&metric=alert_status)](https://sonarcloud.io/dashboard?id=shinusuresh_productsup-client-spring-boot-starter)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=shinusuresh_productsup-client-spring-boot-starter&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=shinusuresh_productsup-client-spring-boot-starter)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=shinusuresh_productsup-client-spring-boot-starter&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=shinusuresh_productsup-client-spring-boot-starter)


ProductsUp is a feed management solution to centralize your entire products data flow with a variety of external social media platforms - https://www.productsup.com/

## Main purpose

This library provides an integration with ProductsUp platform API endpoints to interact with the platform operations [ProductsUP API's](https://api-docs.productsup.io/#introduction-into-our-apis)

## Getting started

The library is published on Maven Central. To add the library into your spring project

### Maven 

```
<dependency>
  <groupId>com.github.shinusuresh</groupId>
  <artifactId>productsup-client-spring-boot-starter</artifactId>
  <version>0.1.11</version>
</dependency>
```

### Gradle

```
implementation 'io.github.shinusuresh:productsup-client-spring-boot-starter:0.1.11' 
```

## How to use

### ConfigurationUR Platform API token with ProductsUP>


``` 
#Add the following properties
productsup.token=<YO
#If you are using stream
productsup.stream.enabled=true
productsup.authorization-token=Bearer <Your Stream PAT>
```

### Code

#### Using Platform API client
```java
private final PlatformApiClient platformApiClient;
var sites = this.platformApiClient.getSites();
```

#### Using Stream API client
```java
private final StreamApiClient streamApiClient;
var streams = this.streamApiClient.listStreams();
```

> For error scenarios `WebClientResponseException` will be thrown. Handle the exception to get the erorr data

```java
var exception = assertThrows(WebClientResponseException.class, () -> streamApiClient.createStream(data));
var errors = exception.getResponseBodyAs(StreamErrors.class);
```

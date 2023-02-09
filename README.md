Firebase Notification Library for Spring Boot
-------
This library provides utilities that make it easy to push notification into a spring boot project

Feature List:

* [Push Notification](#push-notification)
* [Push List Notification](#push-list-notification)
* [Subcribe Topic](#subcribe-topic)
* [Unsubcribe Topic](#unsubcribe-topic)
* [Push Notification by Topic](#push-notification-by-topic)
    
Quick start
-------
* Just add the dependency to an existing Spring Boot project
```xml
<dependency>
    <groupId>com.atviettelsolutions</groupId>
    <artifactId>vts-kit-firebase-notification</artifactId>
    <version>1.0.0</version>
</dependency>
```


* Then, add the following properties to your `application-*.yml` file.
```yaml
firebase:
  googleCredentials: <serviceAccountKey file>
  appName: <APP-NAME>
```



* Finally, declare `MinioService` object
```java
@Autowired
private NotificationService notificationService;
```

Usage
-------

##### Push notification
```java
BatchResponse bathResponse = notificationService.sendNotificationToDevice(Notice notice);
```

##### Push list notification
```java
BatchResponse bathResponse = notificationService.sendNotificationToListDevice(Notices notices);
```

##### Subcribe topic
```java
TopicManagementResponse topicManagementResponse = notificationService.subscribeToTopic(SubcribeTopicRequest subcribeTopicRequest);
```

##### Unsubcribe topic
```java
TopicManagementResponse topicManagementResponse = notificationService.unSubscribeToTopic(SubcribeTopicRequest subcribeTopicRequest);

```
##### Push notification by topic
```java
String response = notificationService.sendNotificationByTopic(SendByTopicRequest sendByTopicRequest);
```



Build
-------
* Build with Unittest
```shell script
mvn clean install
```

* Build without Unittest
```shell script
mvn clean install -DskipTests
```

Contribute
-------
Please refer [Contributors Guide](CONTRIBUTING.md)

License
-------
This code is under the [MIT License](https://opensource.org/licenses/MIT).

See the [LICENSE](LICENSE) file for required notices and attributions.

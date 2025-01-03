# OS Util

[![](https://jitpack.io/v/hidirektor/os-util.svg)](https://jitpack.io/#hidirektor/os-util)

OS Util is a utility library developed in Java for managing operating system preferences and configurations.

## Features
- Retrieve all stored preferences
- Update or save key-value pairs
- Store JSON data as preferences

## Installation

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.hidirektor</groupId>
    <artifactId>os-util</artifactId>
    <version>v1.0.0</version>
</dependency>

<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20231013</version>
</dependency>
```

### Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.hidirektor:os-util:v1.0.0'
    implementation 'org.json:json:20231013'
}
```

## Usage

### Retrieving All Preferences
```java
List<String> allData = OSUtil.getAllData("nodeName");
for (String data : allData) {
    System.out.println(data);
}
```

### Updating Preferences
```java
OSUtil.updatePrefData("nodeName", "key", "value");
```

### Saving JSON String into Preferences
```java
OSUtil.saveJsonIntoPref("nodeName", "key", "{\"name\":\"example\"}");
```

### Saving JSONObject into Preferences
```java
JSONObject jsonObject = new JSONObject();
jsonObject.put("name", "example");
OSUtil.saveJsonIntoPref("nodeName", "key", jsonObject);
```

## License
This project is licensed under the [MIT License](LICENSE).
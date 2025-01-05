# OS Util

[![](https://jitpack.io/v/hidirektor/os-util.svg)](https://jitpack.io/#hidirektor/os-util)

OS Util is a utility library developed in Java for managing operating system preferences and configurations.

## Features
- Retrieve all stored preferences
- Update or save key-value pairs
- Store JSON data as preferences
- Configure system properties for optimized performance
- Create desktop shortcuts and add programs to startup
- Open external applications, files, or directories

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

### Creating Nested Preferences
```java
Preferences prefs = Preferences.userRoot().node("Canicula/releases");
prefs.put("key", "value");
```
This example creates a nested structure with a parent folder named 'Canicula' and a subfolder named 'releases'.

### Configuring System Properties
```java
DesktopUtil.configureSystemProperties();
```
This method configures system properties for JavaFX and logging based on the operating system.

- **Windows**: Enables software rendering, high-performance animations, and verbose logs.
- **macOS**: Configures PDF rendering verbosity and hides UI elements.
- Sets the logging level to 'WARNING'.

### Creating Desktop Shortcut
```java
DesktopUtil.createDesktopShortcut(
    "MyApp",
    "C:/Program Files/MyApp/MyApp.exe",
    "C:/Program Files/MyApp/icon.ico",
    "C:/Program Files/MyApp"
);
```
This method creates a desktop shortcut for an application with the specified name, target path, icon, and working directory.

### Adding Program to Startup
```java
DesktopUtil.addToStartup(
    "MyApp",
    "C:/Program Files/MyApp/MyApp.exe",
    "C:/Program Files/MyApp/icon.ico",
    "C:/Program Files/MyApp"
);
```
This method adds a shortcut for the application to the Windows startup folder, enabling it to launch at system startup.

### Opening External Application
```java
DesktopUtil.startExternalApplication("C:/Program Files/MyApp/MyApp.exe");
```
This method opens a specified file or directory using the default application associated with it.

### Retrieving Preference Data
```java
String data = OSUtil.getPrefData("nodeName", "key");
System.out.println("Value: " + data);
```
This method retrieves the value stored in the given key under the specified node. Returns an empty string if the key doesn't exist.

### Updating Local Version
```java
OSUtil.updateLocalVersion("nodeName", "versionKey", "1.0.1");
```
This method updates the stored version data only if it differs from the provided new data.

## License
This project is licensed under the [MIT License](LICENSE).
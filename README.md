Java Email Check
==================================

## Installation

```
$ git clone https://github.com/OMARoun/emailcheck.git
$ mvn clean install
```

## Configuration

#### Maven dependency

```xml
<dependency>
  <groupId>ma.omaroun.snippet</groupId>
  <artifactId>emailcheck</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Use
#### Check one email 
```java
Email.CheckResult result = Email.check("lesgenies@gmail.com");
```
#### Check if email exist
```java
if (result.isExist()) {
    /*...*/            
}

```
#### Display the result
```java
System.out.println(result);
```
##### Your output will be like this
```
lesgenies@gmail.com : GMAIL [EXIST]
```
### Check a list of emails if exist
```java
Email.check(Arrays.asList(
        "lesanges@gmail.com",
        "lesanges.popolili@gmail.com",
        "lesanges@outlook.com"));
```

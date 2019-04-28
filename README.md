# 封装一些java常用类库

> javadoc 会在整理出2.0版本后上传

## 使用方式

### gradle方式

> 第一步

    allprojects {
      repositories {
        ...
        maven { url 'https://www.jitpack.io' }
      }
	  }
    
> 第二步
  
    dependencies {
	        implementation 'com.github.HerbLee:h_lib_java:1.0.1'
	  }
    
    
### maven 方式
  
> 第一步
 
    <repositories>
      <repository>
          <id>jitpack.io</id>
          <url>https://www.jitpack.io</url>
      </repository>
    </repositories>
    
> 第二步


    <dependency>
        <groupId>com.github.HerbLee</groupId>
        <artifactId>h_lib_java</artifactId>
        <version>1.0.1</version>
    </dependency>
    
    

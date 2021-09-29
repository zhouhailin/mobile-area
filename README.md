# 号码归属地使用

[![Jdk Version](https://img.shields.io/badge/JDK-1.8-green.svg)](https://img.shields.io/badge/JDK-1.8-green.svg)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/link.thingscloud/mobile-area/badge.svg)](https://maven-badges.herokuapp.com/maven-central/link.thingscloud/mobile-area/)

## Maven引入

    <dependency>
        <groupId>link.thingscloud</groupId>
        <artifactId>mobile-area</artifactId>
        <version>2021.09.30</version>
    </dependency>

## 归属地更新记录

    更新记录见版本日期
    
## 文本内容说明

### 全国行政区划信息


## 支持外部资源加载

格式: 号段,区号,省会,地市

```
1300001,0519,江苏,常州
```

## 使用

    // 获取号码信息
    MobileUtil.getMobile("15121001234");
    MobileUtil.getMobile("0015121001234"));
    
    // 获取号码对应的省、市, 其中市会存在多个市同一个区号
    MobileUtil.getArea("0015121001234"));

    // 判断手机号码运营商 ：移动，联通，电信，卫通，电信虚拟运营商，联通虚拟运营商，移动虚拟运营商
    Type getType("1521001234")
    
## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) Copyright (C) Apache Software Foundation

# 号码归属地使用

[![Jdk Version](https://img.shields.io/badge/JDK-1.8-green.svg)](https://img.shields.io/badge/JDK-1.8-green.svg)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/link.thingscloud/mobile-area/badge.svg)](https://maven-badges.herokuapp.com/maven-central/link.thingscloud/mobile-area/)

## Maven引入

    <dependency>
        <groupId>link.thingscloud</groupId>
        <artifactId>mobile-area</artifactId>
        <version>2022.10.06</version>
    </dependency>

## 更新记录

    2022.10.06 : **487554** 条记录
    2022.07.20 : **480451** 条记录
    2022.05.24 : **479243** 条记录
    2021.11.30 : **476215** 条记录
    2021.10.30 : **476212** 条记录
    2021.10.04 : **473926** 条记录
    2021.09.30 : **473906** 条记录
    
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

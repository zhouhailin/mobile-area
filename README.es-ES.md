

# Uso de la consulta de ubicación de número

[![Jdk Version](https://img.shields.io/badge/JDK-1.8-green.svg)](https://img.shields.io/badge/JDK-1.8-green.svg)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/link.thingscloud/mobile-area/badge.svg)](https://maven-badges.herokuapp.com/maven-central/link.thingscloud/mobile-area/)

## Dependencia Maven

    <dependency>
        <groupId>link.thingscloud</groupId>
        <artifactId>mobile-area</artifactId>
        <version>2025.07.07</version>
    </dependency>

## Registro de actualizaciones

    2025.07.07 : **517258** registros
    2025.01.15 : **517258** registros
    2024.05.15 : **507456** registros
    2024.04.30 : **498273** registros
    2023.06.01 : **497765** registros
    2022.10.06 : **487554** registros
    2022.07.20 : **480451** registros
    2022.05.24 : **479243** registros
    2021.11.30 : **476215** registros
    2021.10.30 : **476212** registros
    2021.10.04 : **473926** registros
    2021.09.30 : **473906** registros

## Uso

    // Obtener información del número
    MobileUtil.getMobile("15121001234");
    MobileUtil.getMobile("0015121001234"));
    
    // Obtener la provincia y ciudad asociadas al número (pueden existir múltiples ciudades con el mismo código de área)
    MobileUtil.getArea("0015121001234"));

    // Determinar la operadora del número móvil: China Mobile, China Unicom, China Telecom, China Satcom, operador virtual de telecom, operador virtual de unicom, operador virtual de mobile
    Type getType("1521001234")

## Licencia

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) Copyright (C) Apache Software Foundation

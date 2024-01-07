/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package link.thingscloud.mobile.area.domain;

/**
 * @author zhouhailin
 * @since 1.0.0
 */
public enum Type {
    /**
     * 未知
     */
    NONE("未知"),
    /**
     * 中国移动通信
     */
    CMCC("移动"),
    /**
     * 中国联通通讯
     */
    CUCC("联通"),
    /**
     * 中国电信
     */
    CTCC("电信"),

    /**
     * 中国广电
     */
    CBN("广电"),

    /**
     * 中国卫通 - 中国卫星通信
     */
    CSCC("卫通"),
    /**
     * 中国移动通信
     */
    V_CMCC("移动"),
    /**
     * 中国联通通讯
     */
    V_CUCC("联通"),
    /**
     * 中国电信
     */
    V_CTCC("电信");


    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

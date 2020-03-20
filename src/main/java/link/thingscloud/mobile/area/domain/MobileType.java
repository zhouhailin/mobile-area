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
 * 1 移动
 * 2 联通
 * 3 电信
 * 4 电信虚拟运营商
 * 5 联通虚拟运营商
 * 6 移动虚拟运营商
 *
 * @author : zhouhailin
 * @version $Id: $Id
 */
public enum MobileType {
    /**
     * 未知
     */
    NONE,
    /**
     * 中国移动通信
     */
    CMCC,
    /**
     * 中国联通通讯
     */
    CUCC,
    /**
     * 中国电信
     */
    CTCC,
    /**
     * 中国移动通信
     */
    V_CMCC,
    /**
     * 中国联通通讯
     */
    V_CUCC,
    /**
     * 中国电信
     */
    V_CTCC
}

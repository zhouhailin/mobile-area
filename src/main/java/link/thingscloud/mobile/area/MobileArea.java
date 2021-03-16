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

package link.thingscloud.mobile.area;

import link.thingscloud.mobile.area.domain.Area;
import link.thingscloud.mobile.area.domain.MobileType;

/**
 * <p>MobileArea interface.</p>
 *
 * @author : zhouhailin
 * @version $Id: $Id
 */
public interface MobileArea {

    /**
     * 根据区号获取区域信息
     *
     * @param code {@link java.lang.String} 区号.
     * @return {@link link.thingscloud.mobile.area.domain.Area} 区域信息.
     */
    Area getArea0(String code);

    /**
     * <p>getArea.</p>
     *
     * @param mobileNo a {@link java.lang.String} object.
     * @return a {@link link.thingscloud.mobile.area.domain.Area} object.
     */
    Area getArea(String mobileNo);

    /**
     * <p>getMobileType.</p>
     *
     * @param mobileNo a {@link java.lang.String} object.
     * @return a {@link link.thingscloud.mobile.area.domain.MobileType} object.
     */
    MobileType getMobileType(String mobileNo);

    /**
     * <p>getMobileNumber.</p>
     *
     * @param mobileNo a {@link java.lang.String} object.
     * @param areaCode a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    String getMobileNumber(String mobileNo, String areaCode);

    /**
     * <p>isSameArea.</p>
     *
     * @param mobileNo a {@link java.lang.String} object.
     * @param areaCode a {@link java.lang.String} object.
     * @return a boolean.
     */
    boolean isSameArea(String mobileNo, String areaCode);

    /**
     * 加载外部资源文件
     *
     * @param resourceFileName 资源文件名称
     */
    void load(String resourceFileName);

    /**
     * <p>getInstance.</p>
     *
     * @return a {@link link.thingscloud.mobile.area.MobileArea} object.
     */
    static MobileArea getInstance() {
        return MobileAreaFactory.getInstance();
    }
}

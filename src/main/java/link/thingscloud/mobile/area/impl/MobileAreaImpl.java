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

package link.thingscloud.mobile.area.impl;

import link.thingscloud.mobile.area.MobileArea;
import link.thingscloud.mobile.area.domain.Area;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>MobileAreaImpl class.</p>
 *
 * @author : zhouhailin
 * @version $Id: $Id
 */
@Slf4j
public class MobileAreaImpl implements MobileArea {

    private final Map<String, Area> areaMap = new HashMap<>(1024);
    private final Map<String, Area> mobileAreaMap = new HashMap<>(1024 * 400);

    /**
     * <p>Constructor for MobileAreaImpl.</p>
     */
    public MobileAreaImpl() {
        load("/mobile-area-20200313.csv");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Area getArea(String mobileNo) {
        if (mobileNo == null || mobileNo.length() < 11) {
            return null;
        }
        return mobileAreaMap.get(mobileNo.substring(mobileNo.length() - 11, mobileNo.length() - 4));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMobileNumber(String mobileNo, String areaCode) {
        if (mobileNo == null || mobileNo.length() < 11) {
            return mobileNo;
        }
        int length = mobileNo.length();
        Area area = mobileAreaMap.get(mobileNo.substring(length - 11, length - 4));
        if (area == null || area.getCode().equals(areaCode)) {
            return mobileNo.substring(length - 11);
        }
        return "0" + mobileNo.substring(length - 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSameArea(String mobileNo, String areaCode) {
        Area area = getArea(mobileNo);
        if (area == null) {
            return false;
        }
        return area.getCode().equals(areaCode);
    }


    private void load(String resourceFileName) {
        log.info("mobile area load resource file name : {}", resourceFileName);

        BufferedReader bufferedReader = null;
        InputStream inputStream = MobileAreaImpl.class.getResourceAsStream(resourceFileName);
        try {
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String line;
            // 1300001,0519,江苏,常州
            while ((line = bufferedReader.readLine()) != null) {
                String[] strs = line.split(",");
                if (strs.length == 4) {
                    String mobileNumber = strs[0];
                    String areaCode = strs[1];
                    Area area0 = areaMap.get(areaCode);
                    if (area0 == null) {
                        area0 = new Area().setCode(areaCode).setProvince(strs[2]).setCity(strs[3]);
                        areaMap.put(areaCode, area0);
                    }
                    mobileAreaMap.put(mobileNumber, area0);
                }
            }
        } catch (IOException e) {
            log.error("mobile area load resource [{}] failed, cause : ", resourceFileName, e);
        } finally {
            close(bufferedReader);
        }
        log.info("mobile area load resource successful, area size : {}, mobileArea size : {}.", areaMap.size(), mobileAreaMap.size());
        log.info("getArea mobile number : 1512100xxxx, area : {}", getArea("1512100xxxx"));
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new MobileAreaImpl();
    }
}

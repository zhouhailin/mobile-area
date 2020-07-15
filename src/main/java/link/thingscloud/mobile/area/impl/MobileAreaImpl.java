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
import link.thingscloud.mobile.area.domain.MobileType;
import link.thingscloud.mobile.area.util.MapBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static link.thingscloud.mobile.area.domain.MobileType.*;

/**
 * <p>MobileAreaImpl class.</p>
 *
 * @author : zhouhailin
 * @version $Id: $Id
 */
@Slf4j
public class MobileAreaImpl implements MobileArea {

    /**
     * 中国联通通讯,中国联通通讯,中国电信 - 号段
     */
    private final Map<String, MobileType> corpMap = new HashMap<>();

    private final Map<String, Area> areaMap = new HashMap<>(1024);
    private final Map<String, Area> mobileAreaMap = new HashMap<>(1024 * 400);

    /**
     * <p>Constructor for MobileAreaImpl.</p>
     */
    public MobileAreaImpl() {
        load0();
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
    public MobileType getMobileType(String mobileNo) {
        String mobileNo1 = trimMobileNo(mobileNo);
        if (mobileNo1 == null || mobileNo1.length() < 3) {
            return MobileType.NONE;
        }
        String prefix = mobileNo1.substring(0, 4);
        MobileType mobileType = corpMap.get(prefix);
        if (mobileType == null) {
            prefix = mobileNo1.substring(0, 3);
            mobileType = corpMap.get(prefix);
            if (mobileType == null) {
                return MobileType.NONE;
            }
        }
        return mobileType;
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

    private String trimMobileNo(String mobileNo) {
        if (mobileNo == null || mobileNo.length() < 11) {
            return mobileNo;
        }
        return mobileNo.substring(mobileNo.length() - 11, mobileNo.length() - 4);
    }

    private void load0() {
        MapBuilder<String, MobileType> builder = MapBuilder.newBuilder();
        // 中国电信号段
        builder.put("133", CTCC).put("149", CTCC).put("153", CTCC)
                .put("173", CTCC).put("177", CTCC).put("180", CTCC)
                .put("181", CTCC).put("189", CTCC).put("199", CTCC);

        // 中国联通
        builder.put("130", CUCC).put("131", CUCC).put("132", CUCC)
                .put("145", CUCC).put("155", CUCC).put("156", CUCC)
                .put("175", CUCC).put("176", CUCC).put("185", CUCC)
                .put("186", CUCC).put("166", CUCC);

        // 中国移动
        builder.put("1340", CMCC).put("1341", CMCC).put("1342", CMCC)
                .put("1343", CMCC).put("1344", CMCC).put("1345", CMCC)
                .put("1346", CMCC).put("1347", CMCC).put("1348", CMCC)
                .put("135", CMCC).put("136", CMCC).put("137", CMCC)
                .put("138", CMCC).put("139", CMCC).put("147", CMCC)
                .put("150", CMCC).put("151", CMCC).put("152", CMCC)
                .put("157", CMCC).put("158", CMCC).put("159", CMCC)
                .put("172", CMCC).put("178", CMCC).put("182", CMCC)
                .put("183", CMCC).put("184", CMCC).put("187", CMCC)
                .put("188", CMCC).put("198", CMCC);

        // 卫星通信
        builder.put("1349", CSCC);

        // 电信 - 虚拟运营商
        builder.put("1700", V_CTCC).put("1701", V_CTCC).put("1702", V_CTCC);
        // 移动 - 虚拟运营商
        builder.put("1703", V_CMCC).put("1705", V_CMCC).put("1706", V_CMCC);
        // 联通 - 虚拟运营商
        builder.put("1704", V_CUCC).put("1707", V_CUCC).put("1708", V_CUCC)
                .put("1709", V_CUCC).put("171", V_CUCC);

        corpMap.putAll(builder.build());
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

}

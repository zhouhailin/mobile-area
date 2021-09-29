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
import link.thingscloud.mobile.area.domain.Mobile;
import link.thingscloud.mobile.area.domain.Type;
import link.thingscloud.mobile.area.util.MapBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static link.thingscloud.mobile.area.domain.Type.*;

/**
 * <p>MobileAreaImpl class.</p>
 *
 * @author : zhouhailin
 * @version 2.0.0
 */
@Slf4j
public class MobileAreaImpl implements MobileArea {

    /**
     * 中国联通通讯,中国联通通讯,中国电信 - 号段
     */
    private final Map<String, Type> typeMap = new HashMap<>();
    private final Map<String, Mobile> mobileMap = new HashMap<>(1024 * 400);

    private static final String MOBILE_RESOURCE_FILE = "classpath:/mobile-20210930.csv";

    /**
     * <p>Constructor for MobileAreaImpl.</p>
     */
    public MobileAreaImpl() {
        load0();
        loadMobile();
    }

    @Override
    public Mobile getMobile(String mobileNo) {
        if (mobileNo == null) {
            return null;
        }
        return mobileMap.get(trimMobileNo(mobileNo));
    }

    @Override
    public Area getArea(String mobileNo) {
        Mobile mobile = getMobile(mobileNo);
        if (mobile == null) {
            return null;
        }
        return mobile.getArea();
    }

    @Override
    public Type getType(String mobileNo) {
        Mobile mobile = getMobile(mobileNo);
        if (mobile == null) {
            return Type.NONE;
        }
        return mobile.getType();
    }


    private String trimMobileNo(String mobileNo) {
        if (mobileNo == null || mobileNo.length() < 11) {
            return mobileNo;
        }
        return mobileNo.substring(mobileNo.length() - 11, mobileNo.length() - 4);
    }

    private void load0() {
        MapBuilder<String, Type> builder = MapBuilder.newBuilder();
        // 中国电信号段
        builder.put("133", CTCC).put("149", CTCC).put("153", CTCC)
                .put("162", CTCC)
                .put("173", CTCC).put("174", CTCC).put("177", CTCC).put("180", CTCC)
                .put("181", CTCC).put("189", CTCC)
                .put("190", CTCC).put("191", CTCC).put("193", CTCC).put("199", CTCC);

        // 中国联通
        builder.put("130", CUCC).put("131", CUCC).put("132", CUCC)
                .put("145", CUCC).put("146", CUCC).put("155", CUCC).put("156", CUCC)
                .put("167", CUCC)
                .put("175", CUCC).put("176", CUCC).put("185", CUCC)
                .put("186", CUCC).put("166", CUCC).put("196", CUCC);

        // 中国移动
        builder.put("1340", CMCC).put("1341", CMCC).put("1342", CMCC)
                .put("1343", CMCC).put("1344", CMCC).put("1345", CMCC)
                .put("1346", CMCC).put("1347", CMCC).put("1348", CMCC)
                .put("135", CMCC).put("136", CMCC).put("137", CMCC).put("138", CMCC).put("139", CMCC)
                .put("147", CMCC).put("148", CMCC)
                .put("150", CMCC).put("151", CMCC).put("152", CMCC).put("157", CMCC).put("158", CMCC).put("159", CMCC)
                .put("165", CMCC)
                .put("172", CMCC).put("178", CMCC).put("179", CMCC).put("182", CMCC)
                .put("183", CMCC).put("184", CMCC).put("187", CMCC).put("188", CMCC)
                .put("195", CMCC).put("198", CMCC);

        // 卫星通信
        builder.put("1349", CSCC);

        // 电信 - 虚拟运营商
        builder.put("1700", V_CTCC).put("1701", V_CTCC).put("1702", V_CTCC);
        // 移动 - 虚拟运营商
        builder.put("1703", V_CMCC).put("1705", V_CMCC).put("1706", V_CMCC);
        // 联通 - 虚拟运营商
        builder.put("1704", V_CUCC).put("1707", V_CUCC).put("1708", V_CUCC)
                .put("1709", V_CUCC).put("171", V_CUCC);

        typeMap.putAll(builder.build());
    }

    private Type getType0(String mobileNo) {
        return typeMap.getOrDefault(mobileNo.substring(0, 4), typeMap.get(mobileNo.substring(0, 3)));
    }

    private void loadMobile() {
        long start = System.currentTimeMillis();
        log.debug("load mobile area resource file name : {}", MOBILE_RESOURCE_FILE);

        final Map<String, Area> areaMap = new HashMap<>();

        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = MobileAreaImpl.class.getResourceAsStream(MOBILE_RESOURCE_FILE.replace("classpath:", ""));
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String line;
            // 1300000,山东,济南,联通
            while ((line = bufferedReader.readLine()) != null) {
                String[] strs = line.split(",");
                if (strs.length == 4) {
                    Type type = getType0(strs[0]);
                    if (type == null) {
                        log.error("not found type for : {}", line);
                        continue;
                    }
                    Area area = areaMap.get(strs[1] + "," + strs[2]);
                    if (area == null) {
                        area = new Area(strs[1], strs[2]);
                        areaMap.put(strs[2] + "," + strs[3], area);
                    }
                    mobileMap.put(strs[0], new Mobile(strs[0], type, area, strs[3]));
                }
            }
        } catch (IOException e) {
            log.error("load mobile area resource [{}] failed, cause : ", MOBILE_RESOURCE_FILE, e);
        } finally {
            close(bufferedReader);
        }
        log.info("load mobile area resource file name : {}, mobile size : {}, area size : {}, type size : {}, use {} ms.", MOBILE_RESOURCE_FILE, mobileMap.size(), areaMap.size(), typeMap.size(), System.currentTimeMillis() - start);
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

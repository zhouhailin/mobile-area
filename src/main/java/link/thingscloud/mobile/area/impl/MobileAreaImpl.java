package link.thingscloud.mobile.area.impl;

import link.thingscloud.mobile.area.MobileArea;
import link.thingscloud.mobile.area.domain.Area;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhouhailin
 */
@Slf4j
public class MobileAreaImpl implements MobileArea {

    private final Map<String, Area> areaMap = new HashMap<>(1024);
    private final Map<String, Area> mobileAreaMap = new HashMap<>(1024 * 400);

    public MobileAreaImpl() {
        load("/mobile-area-20200313.csv");
    }

    @Override
    public Area getArea(String mobileNo) {
        if (mobileNo == null || mobileNo.length() < 11) {
            return null;
        }
        return mobileAreaMap.get(mobileNo.substring(mobileNo.length() - 11, mobileNo.length() - 4));
    }

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
        log.info("mobile area load resource successful.");
        log.info("getArea mobile number : 1512100xxxx, area : {}", getArea("1512100"));
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        new MobileAreaImpl();
    }
}

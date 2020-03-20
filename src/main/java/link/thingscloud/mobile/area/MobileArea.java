package link.thingscloud.mobile.area;

import link.thingscloud.mobile.area.domain.Area;

/**
 * @author : zhouhailin
 */
public interface MobileArea {

    Area getArea(String mobileNo);

    String getMobileNumber(String mobileNo, String areaCode);

    boolean isSameArea(String mobileNo, String areaCode);

    static MobileArea getInstance() {
        return MobileAreaFactory.getInstance();
    }

}

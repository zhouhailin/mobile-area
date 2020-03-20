package link.thingscloud.mobile.area;

import link.thingscloud.mobile.area.impl.MobileAreaImpl;

/**
 * @author : zhouhailin
 */
public class MobileAreaFactory {

    private static final MobileArea MOBILE_AREA = new MobileAreaImpl();

    private MobileAreaFactory(){}

    public static MobileArea getInstance() {
        return MOBILE_AREA;
    }
}

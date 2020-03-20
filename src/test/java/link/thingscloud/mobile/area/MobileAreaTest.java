package link.thingscloud.mobile.area;

import org.junit.Test;

/**
 * @author : zhouhailin
 */
public class MobileAreaTest {

    @Test
    public void getArea() {
        System.out.println(MobileArea.getInstance().getArea("15121007938"));
        System.out.println(MobileArea.getInstance().getArea("0015121007938"));
        System.out.println(MobileArea.getInstance().getMobileNumber("15121007938", "0532"));
        System.out.println(MobileArea.getInstance().isSameArea("915121007938", "0532"));
        System.out.println(MobileArea.getInstance().getMobileNumber("+915121007938", "021"));
        System.out.println(MobileArea.getInstance().isSameArea("9015121007938", "021"));
    }

    @Test
    public void getMobileNumber() {
    }

    @Test
    public void isSameArea() {
    }
}

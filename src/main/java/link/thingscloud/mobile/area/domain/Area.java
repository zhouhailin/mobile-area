package link.thingscloud.mobile.area.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : zhouhailin
 */
@Data
@Accessors(chain = true)
public class Area {
    /**
     * 区号
     */
    private String code;
    /**
     * 省份
     */
    private String province;
    /**
     * 地市
     */
    private String city;
}

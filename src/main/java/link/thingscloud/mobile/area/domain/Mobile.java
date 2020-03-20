package link.thingscloud.mobile.area.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : zhouhailin
 */
@Data
@Accessors(chain = true)
public class Mobile {
    private String number;
    private MobileType type;
    private Area area;
}

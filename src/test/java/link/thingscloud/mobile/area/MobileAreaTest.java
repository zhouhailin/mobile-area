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

import org.junit.Test;

/**
 * @author : zhouhailin
 */
public class MobileAreaTest {

    @Test
    public void getArea() {
        System.out.println(MobileArea.getInstance().getArea("15121001234"));
        System.out.println(MobileArea.getInstance().getArea("0015121001234"));
    }

    @Test
    public void getMobileType() {
        System.out.println(MobileArea.getInstance().getMobileType("1512100"));
        System.out.println(MobileArea.getInstance().getMobileType("1665110"));
    }

    @Test
    public void getMobileNumber() {
        System.out.println(MobileArea.getInstance().getMobileNumber("15121001234", "0532"));
        System.out.println(MobileArea.getInstance().isSameArea("915121001234", "0532"));
    }

    @Test
    public void isSameArea() {
        System.out.println(MobileArea.getInstance().getMobileNumber("+915121001234", "021"));
        System.out.println(MobileArea.getInstance().isSameArea("9015121001234", "021"));
    }
}

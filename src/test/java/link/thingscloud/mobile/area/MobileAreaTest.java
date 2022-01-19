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

import link.thingscloud.mobile.area.domain.Mobile;
import link.thingscloud.mobile.area.domain.Type;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author : zhouhailin
 */
public class MobileAreaTest {

    @Test
    public void getMobile() {
        // 1969019,新疆,喀什,联通
        Mobile mobile = MobileArea.getInstance().getMobile("19690191234");
        System.out.println(mobile);
        assertEquals(mobile.getType(), Type.CUCC);
        assertEquals(mobile.getArea().getProvince(), "新疆");
        assertEquals(mobile.getArea().getCity(), "喀什");

        //1980000,北京,北京,移动
        mobile = MobileArea.getInstance().getMobile("19800001234");
        System.out.println(mobile);
        assertEquals(mobile.getType(), Type.CMCC);
        assertEquals(mobile.getArea().getProvince(), "北京");
        assertEquals(mobile.getArea().getCity(), "北京");

        // 1980382,河南,郑州,移动
        mobile = MobileArea.getInstance().getMobile("19803821234");
        System.out.println(mobile);
        assertEquals(mobile.getType(), Type.CMCC);
        assertEquals(mobile.getArea().getProvince(), "河南");
        assertEquals(mobile.getArea().getCity(), "郑州");

        // 1981073,安徽,滁州,移动
        mobile = MobileArea.getInstance().getMobile("19810731234");
        System.out.println(mobile);
        assertEquals(mobile.getType(), Type.CMCC);
        assertEquals(mobile.getArea().getProvince(), "安徽");
        assertEquals(mobile.getArea().getCity(), "滁州");

        // 1981319,云南,普洱,移动
        mobile = MobileArea.getInstance().getMobile("1981319");
        System.out.println(mobile);
        assertEquals(mobile.getType(), Type.CMCC);
        assertEquals(mobile.getArea().getProvince(), "云南");
        assertEquals(mobile.getArea().getCity(), "普洱");

        // 1300695,广西,玉林,联通
        mobile = MobileArea.getInstance().getMobile("1300695");
        System.out.println(mobile);
        assertEquals(mobile.getType(), Type.CUCC);
        assertEquals(mobile.getArea().getProvince(), "广西");
        assertEquals(mobile.getArea().getCity(), "玉林");


        println();
    }

    @Test
    public void getArea() {
        System.out.println(MobileArea.getInstance().getArea("15121001234"));
        System.out.println(MobileArea.getInstance().getArea("0015121001234"));
    }

    @Test
    public void getType() {
        System.out.println(MobileArea.getInstance().getType("1512100"));
        System.out.println(MobileArea.getInstance().getType("1665110"));
    }

    @Test
    public void loadTest() {
        System.out.println("1512100".substring(0, 4));
    }

    public static void println() {
        System.out.println("------------------------------------");
        System.gc();
        Runtime run = Runtime.getRuntime();
        long max = run.maxMemory();
        long total = run.totalMemory();
        long free = run.freeMemory();
        long usable = max - total + free;
        System.out.println("最大内存 = " + (max / 1024 / 1024));
        System.out.println("已分配内存 = " + (total / 1024 / 1024));
        System.out.println("已分配内存中的剩余空间 = " + (free / 1024 / 1024));
        System.out.println("最大可用内存 = " + (usable / 1024 / 1024));
    }

    public static void main(String[] args) {
        println();
        // 1969019,新疆,喀什,联通
        Mobile mobile = MobileArea.getInstance().getMobile("19690191234");
        System.out.println(mobile);
        println();
    }
}

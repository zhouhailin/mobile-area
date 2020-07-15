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

package link.thingscloud.mobile.area.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>MapBuilder class.</p>
 *
 * @author zhouhailin
 * @version $Id: $Id
 */
public class MapBuilder<K, V> {

    private final Map<K, V> map = new HashMap<>();

    /**
     * <p>newBuilder.</p>
     *
     * @param <K> a K object.
     * @param <V> a V object.
     * @return a {@link link.thingscloud.mobile.area.util.MapBuilder} object.
     */
    public static <K, V> MapBuilder<K, V> newBuilder() {
        return new MapBuilder();
    }

    /**
     * <p>put.</p>
     *
     * @param k a K object.
     * @param v a V object.
     * @return a {@link link.thingscloud.mobile.area.util.MapBuilder} object.
     */
    public MapBuilder<K, V> put(K k, V v) {
        map.put(k, v);
        return this;
    }

    /**
     * <p>build.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<K, V> build() {
        return map;
    }
}

/*
 * Copyright (C) 2014 Dmitry Kotlyarov.
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package apphub.rest;

import apphub.Config;
import apphub.util.ThreadLock;
import apphub.util.ThreadUtil;
import apphub.util.Util;
import apphub.util.MapUtil;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Dmitry Kotlyarov
 * @since 1.0
 */
public final class Service {
    private static final ResteasyClient client = new ResteasyClientBuilder().build();
    private static final ConcurrentHashMap<Key, Holder> services = new ConcurrentHashMap<>(256);

    private Service() {
    }

    @SuppressWarnings({"unchecked"})
    public static <S> S get(String url, Class<S> type) {
        Key<S> key = new Key<>(url, type);
        Holder<S> holder = services.get(key);
        if (holder == null) {
            holder = MapUtil.putIfAbsent(services, key, new Holder<>(key));
        }
        return holder.getProxy();
    }

    static {
        Thread t = new Thread("apphub-rest-service") {
            @Override
            public void run() {
                while (!ThreadUtil.isShutdown()) {
                    ThreadUtil.sleep(60000L);
                    Holder[] holders = services.values().toArray(new Holder[services.size()]);
                    long time = System.currentTimeMillis();
                    for (Holder holder : holders) {
                        if (holder.getAccessTime() + 60000 < time) {
                            services.remove(holder.getKey());
                        }
                    }
                }
            }
        };
        t.setDaemon(true);
        t.start();
    }

    private static final class Key<S> {
        private final String url;
        private final Class<S> type;

        public Key(String url, Class<S> type) {
            this.url = url;
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public Class<S> getType() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (!type.equals(key.type)) return false;
            if (!url.equals(key.url)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = url.hashCode();
            result = 31 * result + type.hashCode();
            return result;
        }
    }

    private static final class Holder<S> {
        private final Key<S> key;
        private final ResteasyWebTarget target;
        private final S proxy;
        private final AtomicLong accessTime;

        public Holder(Key<S> key) {
            this.key = key;
            this.target = client.target(key.getUrl());
            this.proxy = target.proxy(key.getType());
            this.accessTime = new AtomicLong(System.currentTimeMillis());
        }

        public Key<S> getKey() {
            return key;
        }

        public ResteasyWebTarget getTarget() {
            return target;
        }

        public S getProxy() {
            accessTime.set(System.currentTimeMillis());
            return proxy;
        }

        public long getAccessTime() {
            return accessTime.get();
        }
    }
}

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

package apphub.service.v1.api;

import apphub.util.CborUtil;
import apphub.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Dmitry Kotlyarov
 * @since 1.0
 */
public class Instance implements Serializable {
    private static final long serialVersionUID = 1L;

    public final String environment;
    public final String application;
    public final Timestamp createTime;
    public final String createUser;
    public Timestamp updateTime;
    public String updateUser;
    public String version;
    public String description;
    public String url;

    @JsonCreator
    public Instance(@JsonProperty("environment") String environment,
                    @JsonProperty("application") String application,
                    @JsonProperty("createTime") Timestamp createTime,
                    @JsonProperty("createUser") String createUser,
                    @JsonProperty("updateTime") Timestamp updateTime,
                    @JsonProperty("updateUser") String updateUser,
                    @JsonProperty("version") String version,
                    @JsonProperty("description") String description,
                    @JsonProperty("url") String url) {
        this.environment = environment;
        this.application = application;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.version = version;
        this.description = description;
        this.url = url;
    }

    public byte[] toBytes() {
        return CborUtil.toBytes(this);
    }

    @Override
    public String toString() {
        return JsonUtil.toString(this);
    }

    public static Instance valueOf(byte[] data) {
        return CborUtil.fromBytes(data, Instance.class);
    }

    public static Instance valueOf(String content) {
        return JsonUtil.fromString(content, Instance.class);
    }
}

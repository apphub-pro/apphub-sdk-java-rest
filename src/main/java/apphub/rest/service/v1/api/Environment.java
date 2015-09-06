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

package apphub.rest.service.v1.api;

import apphub.EnvironmentType;
import apphub.util.cbor.CborUtil;
import apphub.util.json.JsonUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Dmitry Kotlyarov
 * @since 1.0
 */
public class Environment implements Serializable {
    private static final long serialVersionUID = 1;

    public final String id;
    public final Timestamp createTime;
    public final String createUser;
    public Timestamp updateTime;
    public String updateUser;
    public final EnvironmentType type;
    public EnvironmentState state;
    public String name;
    public String description;
    public String url;
    public String company;
    public String location;
    public String picture;

    @JsonCreator
    public Environment(@JsonProperty("id") String id,
                       @JsonProperty("createTime") Timestamp createTime,
                       @JsonProperty("createUser") String createUser,
                       @JsonProperty("updateTime") Timestamp updateTime,
                       @JsonProperty("updateUser") String updateUser,
                       @JsonProperty("type") EnvironmentType type,
                       @JsonProperty("state") EnvironmentState state,
                       @JsonProperty("name") String name,
                       @JsonProperty("description") String description,
                       @JsonProperty("url") String url,
                       @JsonProperty("company") String company,
                       @JsonProperty("location") String location,
                       @JsonProperty("picture") String picture) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.type = type;
        this.state = state;
        this.name = name;
        this.description = description;
        this.url = url;
        this.company = company;
        this.location = location;
        this.picture = picture;
    }

    public byte[] toBytes() {
        return CborUtil.toBytes(this);
    }

    @Override
    public String toString() {
        return JsonUtil.toString(this);
    }

    public static Environment valueOf(byte[] data) {
        return CborUtil.fromBytes(data, Environment.class);
    }

    public static Environment valueOf(String content) {
        return JsonUtil.fromString(content, Environment.class);
    }
}

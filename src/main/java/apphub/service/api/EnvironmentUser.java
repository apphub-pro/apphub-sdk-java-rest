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

package apphub.service.api;

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
public class EnvironmentUser implements Serializable {
    private static final long serialVersionUID = 1L;

    public final String environment;
    public final String user;
    public final Timestamp createTime;
    public final String createUser;
    public Timestamp updateTime;
    public String updateUser;
    public Boolean admin;

    @JsonCreator
    public EnvironmentUser(@JsonProperty("environment") String environment,
                           @JsonProperty("user") String user,
                           @JsonProperty("createTime") Timestamp createTime,
                           @JsonProperty("createUser") String createUser,
                           @JsonProperty("updateTime") Timestamp updateTime,
                           @JsonProperty("updateUser") String updateUser,
                           @JsonProperty("admin") Boolean admin) {
        this.environment = environment;
        this.user = user;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.admin = admin;
    }

    public byte[] toBytes() {
        return CborUtil.toBytes(this);
    }

    @Override
    public String toString() {
        return JsonUtil.toString(this);
    }

    public static EnvironmentUser valueOf(byte[] data) {
        return CborUtil.fromBytes(data, EnvironmentUser.class);
    }

    public static EnvironmentUser valueOf(String content) {
        return JsonUtil.fromString(content, EnvironmentUser.class);
    }
}

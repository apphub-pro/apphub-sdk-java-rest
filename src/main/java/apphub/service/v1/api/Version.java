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
public class Version implements Serializable {
    private static final long serialVersionUID = 1;

    public final String application;
    public final String id;
    public final Timestamp createTime;
    public final String createUser;
    public Timestamp updateTime;
    public String updateUser;
    public String notes;
    public String url;

    @JsonCreator
    public Version(@JsonProperty("application") String application,
                   @JsonProperty("id") String id,
                   @JsonProperty("createTime") Timestamp createTime,
                   @JsonProperty("createUser") String createUser,
                   @JsonProperty("updateTime") Timestamp updateTime,
                   @JsonProperty("updateUser") String updateUser,
                   @JsonProperty("notes") String notes,
                   @JsonProperty("url") String url) {
        this.application = application;
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.notes = notes;
        this.url = url;
    }

    public byte[] toBytes() {
        return CborUtil.toBytes(this);
    }

    @Override
    public String toString() {
        return JsonUtil.toString(this);
    }

    public static Version valueOf(byte[] data) {
        return CborUtil.fromBytes(data, Version.class);
    }

    public static Version valueOf(String content) {
        return JsonUtil.fromString(content, Version.class);
    }
}

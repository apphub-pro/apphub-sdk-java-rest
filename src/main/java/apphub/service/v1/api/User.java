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
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public final String id;
    public final Timestamp createTime;
    public Timestamp updateTime;
    public String name;
    public final String email;
    public String url;
    public String company;
    public String location;
    public String picture;

    @JsonCreator
    public User(@JsonProperty("id") String id,
                @JsonProperty("createTime") Timestamp createTime,
                @JsonProperty("updateTime") Timestamp updateTime,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("url") String url,
                @JsonProperty("company") String company,
                @JsonProperty("location") String location,
                @JsonProperty("picture") String picture) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
        this.email = email;
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

    public static User valueOf(byte[] data) {
        return CborUtil.fromBytes(data, User.class);
    }

    public static User valueOf(String content) {
        return JsonUtil.fromString(content, User.class);
    }
}

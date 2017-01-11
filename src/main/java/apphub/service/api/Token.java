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
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;

    public final String user;
    public final String id;
    public final Timestamp createTime;

    @JsonCreator
    public Token(@JsonProperty("user") String user,
                 @JsonProperty("id") String id,
                 @JsonProperty("createTime") Timestamp createTime) {
        this.user = user;
        this.id = id;
        this.createTime = createTime;
    }

    public byte[] toBytes() {
        return CborUtil.toBytes(this);
    }

    @Override
    public String toString() {
        return JsonUtil.toString(this);
    }

    public static Token valueOf(byte[] data) {
        return CborUtil.fromBytes(data, Token.class);
    }

    public static Token valueOf(String content) {
        return JsonUtil.fromString(content, Token.class);
    }
}

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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author Dmitry Kotlyarov
 * @since 1.0
 */
@Path("/v1/application")
public interface IApplicationService {
    @GET
    @Produces(CborUtil.APPLICATION_CBOR)
    public Application get(@HeaderParam("token") String token,
                           @HeaderParam("id") String id);

    @GET
    @Path("/list")
    @Produces(CborUtil.APPLICATION_CBOR)
    public List<Application> list(@HeaderParam("token") String token);

    @POST
    @Consumes(CborUtil.APPLICATION_CBOR)
    @Produces(CborUtil.APPLICATION_CBOR)
    public Application post(@HeaderParam("token") String token,
                            Application application);

    @PUT
    @Consumes(CborUtil.APPLICATION_CBOR)
    @Produces(CborUtil.APPLICATION_CBOR)
    public Application put(@HeaderParam("token") String token,
                           Application application);
}


package org.noorganization.instantListApi.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.noorganization.instantListApi.model.Login;
import org.noorganization.instantListApi.model.Register;
import org.noorganization.instantListApi.model.ResetPassword;

@Path("user")
public interface UserResource {


    /**
     * Get the auth token
     * 
     * @param accessToken
     *     An access token is required for secured routes
     */
    @GET
    @Path("token")
    @Produces({
        "application/json"
    })
    UserResource.GetUserTokenResponse getUserToken(
        @QueryParam("accessToken")
        String accessToken)
        throws Exception
    ;

    /**
     * The action to register an user.
     * 
     * @param entity
     *      e.g. {
     *       "eMail" : "HansWurst@nonesense.bit",
     *       "password": "blabla"
     *     }
     *     
     */
    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces({
        "application/json"
    })
    UserResource.PostUserRegisterResponse postUserRegister(Register entity)
        throws Exception
    ;

    /**
     * The action to login an user.
     * 
     * @param entity
     *      e.g. {
     *       "eMail" : "HansWurst@nonesense.bit",
     *       "password": "blabla"
     *     }
     *     
     */
    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces({
        "application/json"
    })
    UserResource.PostUserLoginResponse postUserLogin(Login entity)
        throws Exception
    ;

    /**
     * The action to reset a password of a user.
     * 
     * @param entity
     *      e.g. {
     *       "eMail" : "HansWurst@nonesense.bit"
     *     }
     *     
     */
    @POST
    @Path("resetPassword")
    @Consumes("application/json")
    @Produces({
        "application/json"
    })
    UserResource.PostUserResetPasswordResponse postUserResetPassword(ResetPassword entity)
        throws Exception
    ;

    public class GetUserTokenResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private GetUserTokenResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "token" : "some token"
         *     },
         *   "status" : 200,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "token" : "some token"
         *         },
         *       "status" : 200,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.GetUserTokenResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.GetUserTokenResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "There was an error with your get token."
         *     },
         *   "status" : 400,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "There was an error with your get token."
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.GetUserTokenResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.GetUserTokenResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "The element has been created.",
         *       "relativePath": "/recipes/someuuid"
         *     },
         *   "status" : 201,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "The element has been created.",
         *           "relativePath": "/recipes/someuuid"
         *         },
         *       "status" : 201,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.GetUserTokenResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.GetUserTokenResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Accepted by server. But processed later."
         *     },
         *   "status" : 202,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "Accepted by server. But processed later."
         *         },
         *       "status" : 202,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.GetUserTokenResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.GetUserTokenResponse(responseBuilder.build());
        }

        /**
         * Unauthorized
         * 
         */
        public static UserResource.GetUserTokenResponse withUnauthorized() {
            Response.ResponseBuilder responseBuilder = Response.status(401);
            return new UserResource.GetUserTokenResponse(responseBuilder.build());
        }

    }

    public class PostUserLoginResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private PostUserLoginResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Login succeeded!",
         *       "token" : "some token"
         *     },
         *   "status" : 200,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "Login succeeded!",
         *           "token" : "some token"
         *         },
         *       "status" : 200,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.PostUserLoginResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserLoginResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "There was an error with your login."
         *     },
         *   "status" : 400,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "There was an error with your login."
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.PostUserLoginResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserLoginResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "The element has been created.",
         *       "relativePath": "/recipes/someuuid"
         *     },
         *   "status" : 201,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "The element has been created.",
         *           "relativePath": "/recipes/someuuid"
         *         },
         *       "status" : 201,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.PostUserLoginResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserLoginResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Accepted by server. But processed later."
         *     },
         *   "status" : 202,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "Accepted by server. But processed later."
         *         },
         *       "status" : 202,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.PostUserLoginResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserLoginResponse(responseBuilder.build());
        }

    }

    public class PostUserRegisterResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private PostUserRegisterResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Registration succeeded! We sent an E-Mail to your inbox."
         *     },
         *   "status" : 200,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "Registration succeeded! We sent an E-Mail to your inbox."
         *         },
         *       "status" : 200,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.PostUserRegisterResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserRegisterResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "There was an error with your registration."
         *     },
         *   "status" : 400,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "There was an error with your registration."
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.PostUserRegisterResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserRegisterResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "The element has been created.",
         *       "relativePath": "/recipes/someuuid"
         *     },
         *   "status" : 201,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "The element has been created.",
         *           "relativePath": "/recipes/someuuid"
         *         },
         *       "status" : 201,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.PostUserRegisterResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserRegisterResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Accepted by server. But processed later."
         *     },
         *   "status" : 202,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "Accepted by server. But processed later."
         *         },
         *       "status" : 202,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.PostUserRegisterResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserRegisterResponse(responseBuilder.build());
        }

    }

    public class PostUserResetPasswordResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private PostUserResetPasswordResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "E-Mail was sent to your inbox. Click the link to reset the password!"
         *     },
         *   "status" : 200,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "E-Mail was sent to your inbox. Click the link to reset the password!"
         *         },
         *       "status" : 200,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.PostUserResetPasswordResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserResetPasswordResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "There was an error with your E-Mail address."
         *     },
         *   "status" : 400,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "There was an error with your E-Mail address."
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.PostUserResetPasswordResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserResetPasswordResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "The element has been created.",
         *       "relativePath": "/recipes/someuuid"
         *     },
         *   "status" : 201,
         *   "success" : true
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "The element has been created.",
         *           "relativePath": "/recipes/someuuid"
         *         },
         *       "status" : 201,
         *       "success" : true
         *     }
         *     
         */
        public static UserResource.PostUserResetPasswordResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserResetPasswordResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Accepted by server. But processed later."
         *     },
         *   "status" : 202,
         *   "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *       "data" :
         *         {
         *           "msg" : "Accepted by server. But processed later."
         *         },
         *       "status" : 202,
         *       "success" : false
         *     }
         *     
         */
        public static UserResource.PostUserResetPasswordResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new UserResource.PostUserResetPasswordResponse(responseBuilder.build());
        }

    }

}


package org.noorganization.instantListApi.resource;

import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.noorganization.instantListApi.model.Product;


/**
 * Collection of available products.
 * 
 */
@Path("products")
public interface ProductsResource {


    /**
     * Get a list of products.
     * 
     * 
     * @param changedSince
     *     Requests only the elements that changed since the given date. ISO 8601 time e.g. 2016-01-19T11:54:07+01:00
     */
    @GET
    @Produces({
        "application/json"
    })
    ProductsResource.GetProductsResponse getProducts(
        @QueryParam("changedSince")
        Date changedSince)
        throws Exception
    ;

    /**
     * Add a new product.
     * 
     * 
     * @param entity
     *      e.g. {
     *         "id" : "87c03314-8733-4e91-b356-0f1d37dd4eb8",
     *         "name" : "test_Product1",
     *         "unitId" : "079d3bc8-ee0c-47de-a9c5-bb673e980892",
     *         "defaultAmount" : 0.5,
     *         "stepAmount" : 0.5
     *     }
     *     
     */
    @POST
    @Consumes("application/json")
    @Produces({
        "application/json"
    })
    ProductsResource.PostProductsResponse postProducts(Product entity)
        throws Exception
    ;

    /**
     * Returns the product.
     * 
     * 
     * @param productId
     *     
     */
    @GET
    @Path("{productId}")
    @Produces({
        "application/json"
    })
    ProductsResource.GetProductsByProductIdResponse getProductsByProductId(
        @PathParam("productId")
        String productId)
        throws Exception
    ;

    /**
     * Updates the product.
     * 
     * 
     * @param productId
     *     
     * @param entity
     *      e.g. {
     *         "id" : "87c03314-8733-4e91-b356-0f1d37dd4eb8",
     *         "name" : "test_Product1",
     *         "unitId" : "079d3bc8-ee0c-47de-a9c5-bb673e980892",
     *         "defaultAmount" : 0.5,
     *         "stepAmount" : 0.5
     *     }
     *     
     */
    @PUT
    @Path("{productId}")
    @Consumes("application/json")
    @Produces({
        "application/json"
    })
    ProductsResource.PutProductsByProductIdResponse putProductsByProductId(
        @PathParam("productId")
        String productId, Product entity)
        throws Exception
    ;

    /**
     * Deletes the product.
     * 
     * 
     * @param productId
     *     
     */
    @DELETE
    @Path("{productId}")
    @Produces({
        "application/json"
    })
    ProductsResource.DeleteProductsByProductIdResponse deleteProductsByProductId(
        @PathParam("productId")
        String productId)
        throws Exception
    ;

    public class DeleteProductsByProductIdResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private DeleteProductsByProductIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Deletion without error"
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
         *           "msg" : "Deletion without error"
         *         },
         *       "status" : 200,
         *       "success" : true
         *     }
         *     
         */
        public static ProductsResource.DeleteProductsByProductIdResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.DeleteProductsByProductIdResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Update with error"
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
         *           "msg" : "Update with error"
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static ProductsResource.DeleteProductsByProductIdResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.DeleteProductsByProductIdResponse(responseBuilder.build());
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
        public static ProductsResource.DeleteProductsByProductIdResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.DeleteProductsByProductIdResponse(responseBuilder.build());
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
        public static ProductsResource.DeleteProductsByProductIdResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.DeleteProductsByProductIdResponse(responseBuilder.build());
        }

    }

    public class GetProductsByProductIdResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private GetProductsByProductIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         * "status" : 200,
         * "success" : true,
         * "data" :
         *   {
         *       "id" : "87c03314-8733-4e91-b356-0f1d37dd4eb8",
         *       "name" : "test_Product1",
         *       "unitId" : "079d3bc8-ee0c-47de-a9c5-bb673e980892",
         *       "defaultAmount" : 1.5,
         *       "stepAmount" : 0.5,
         *       "lastChanged": 2016-01-19T11:54:07+01:00
         *   }
         * }
         * 
         * 
         * @param entity
         *     {
         *     "status" : 200,
         *     "success" : true,
         *     "data" :
         *       {
         *           "id" : "87c03314-8733-4e91-b356-0f1d37dd4eb8",
         *           "name" : "test_Product1",
         *           "unitId" : "079d3bc8-ee0c-47de-a9c5-bb673e980892",
         *           "defaultAmount" : 1.5,
         *           "stepAmount" : 0.5,
         *           "lastChanged": 2016-01-19T11:54:07+01:00
         *       }
         *     }
         *     
         */
        public static ProductsResource.GetProductsByProductIdResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsByProductIdResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Get with error."
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
         *           "msg" : "Get with error."
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static ProductsResource.GetProductsByProductIdResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsByProductIdResponse(responseBuilder.build());
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
        public static ProductsResource.GetProductsByProductIdResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsByProductIdResponse(responseBuilder.build());
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
        public static ProductsResource.GetProductsByProductIdResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsByProductIdResponse(responseBuilder.build());
        }

    }

    public class GetProductsResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private GetProductsResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         * "status" : 200,
         * "success" : true,
         * "count" : 2,
         * "data" :
         *   [
         *   {
         *       "id" : "87c03314-8733-4e91-b356-0f1d37dd4eb8",
         *       "name" : "test_Product1",
         *       "unitId" : "079d3bc8-ee0c-47de-a9c5-bb673e980892",
         *       "defaultAmount" : 1.5,
         *       "stepAmount" : 0.5,
         *       "lastChanged": 2016-01-19T11:54:07+01:00
         *   },
         *   {
         *       "id" : "de14221d-6958-4cf1-b099-7c6a67b2125d",
         *       "name" : "test_Product2",
         *       "unitId" : "7359a706-3303-497e-b6bb-11f34e73e7c4",
         *       "defaultAmount" : 0.75,
         *       "stepAmount" : 0.5,
         *       "lastChanged": 2016-01-19T11:54:07+01:00
         *   }
         *   ]
         * }
         * 
         * 
         * 
         * @param entity
         *     {
         *     "status" : 200,
         *     "success" : true,
         *     "count" : 2,
         *     "data" :
         *       [
         *       {
         *           "id" : "87c03314-8733-4e91-b356-0f1d37dd4eb8",
         *           "name" : "test_Product1",
         *           "unitId" : "079d3bc8-ee0c-47de-a9c5-bb673e980892",
         *           "defaultAmount" : 1.5,
         *           "stepAmount" : 0.5,
         *           "lastChanged": 2016-01-19T11:54:07+01:00
         *       },
         *       {
         *           "id" : "de14221d-6958-4cf1-b099-7c6a67b2125d",
         *           "name" : "test_Product2",
         *           "unitId" : "7359a706-3303-497e-b6bb-11f34e73e7c4",
         *           "defaultAmount" : 0.75,
         *           "stepAmount" : 0.5,
         *           "lastChanged": 2016-01-19T11:54:07+01:00
         *       }
         *       ]
         *     }
         *     
         *     
         */
        public static ProductsResource.GetProductsResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Get with error"
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
         *           "msg" : "Get with error"
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static ProductsResource.GetProductsResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsResponse(responseBuilder.build());
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
        public static ProductsResource.GetProductsResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsResponse(responseBuilder.build());
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
        public static ProductsResource.GetProductsResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.GetProductsResponse(responseBuilder.build());
        }

    }

    public class PostProductsResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private PostProductsResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Creation without error"
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
         *           "msg" : "Creation without error"
         *         },
         *       "status" : 200,
         *       "success" : true
         *     }
         *     
         */
        public static ProductsResource.PostProductsResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PostProductsResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Creation with error"
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
         *           "msg" : "Creation with error"
         *         },
         *       "status" : 400,
         *       "success" : false
         *     }
         *     
         */
        public static ProductsResource.PostProductsResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PostProductsResponse(responseBuilder.build());
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
        public static ProductsResource.PostProductsResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PostProductsResponse(responseBuilder.build());
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
        public static ProductsResource.PostProductsResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PostProductsResponse(responseBuilder.build());
        }

    }

    public class PutProductsByProductIdResponse
        extends org.noorganization.instantListApi.resource.support.ResponseWrapper
    {


        private PutProductsByProductIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         *  e.g. {
         *   "data" :
         *     {
         *       "msg" : "Update without error"
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
         *           "msg" : "Update without error"
         *         },
         *       "status" : 200,
         *       "success" : true
         *     }
         *     
         */
        public static ProductsResource.PutProductsByProductIdResponse withJsonOK(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PutProductsByProductIdResponse(responseBuilder.build());
        }

        /**
         *  e.g. {
         * "data" :
         *   {
         *     "msg" : "Update with error"
         *   },
         * "status" : 400,
         * "success" : false
         * }
         * 
         * 
         * @param entity
         *     {
         *     "data" :
         *       {
         *         "msg" : "Update with error"
         *       },
         *     "status" : 400,
         *     "success" : false
         *     }
         *     
         */
        public static ProductsResource.PutProductsByProductIdResponse withJsonBadRequest(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PutProductsByProductIdResponse(responseBuilder.build());
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
        public static ProductsResource.PutProductsByProductIdResponse withJsonCreated(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PutProductsByProductIdResponse(responseBuilder.build());
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
        public static ProductsResource.PutProductsByProductIdResponse withJsonAccepted(StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(202).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ProductsResource.PutProductsByProductIdResponse(responseBuilder.build());
        }

    }

}

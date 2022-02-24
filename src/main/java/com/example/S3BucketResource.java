package com.example;

import com.example.s3client.S3Retriever;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/buckets")
public class S3BucketResource {

    @Inject
    S3Retriever retriever;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> hello() {
        return retriever.listStateBuckets();
    }
}

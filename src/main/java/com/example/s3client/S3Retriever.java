package com.example.s3client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class S3Retriever {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    HashMap<String, String> statefilesByName = new HashMap<>();

    @Inject
    S3Client s3Client;

    public List<String> listStateBuckets() {
        ListBucketsRequest request = ListBucketsRequest.builder().build();
        List<Bucket> buckets = s3Client.listBuckets(request).buckets();
        logger.info("found {} buckets {}", buckets.size(), buckets.get(0).name());
        return buckets
                .stream()
                .map(Bucket::name)
                .filter(name -> name.contains("k8s") && !name.contains("replica"))
                .collect(Collectors.toList());
    }

    public String ping() {
        return "pong";
    }

}

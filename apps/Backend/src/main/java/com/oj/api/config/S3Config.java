package com.oj.api.config;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {
  @Bean
  S3Client s3(@Value("${aws.region}") String region,
              @Value("${aws.s3.endpoint:}") Optional<String> endpoint,
              @Value("${aws.s3.accessKey:}") Optional<String> ak,
              @Value("${aws.s3.secretKey:}") Optional<String> sk) {
    S3ClientBuilder b = S3Client.builder().region(Region.of(region));
    endpoint.ifPresent(e -> b.endpointOverride(URI.create(e)));
    if (ak.isPresent() && sk.isPresent()) {
      b.credentialsProvider(StaticCredentialsProvider.create(
          AwsBasicCredentials.create(ak.get(), sk.get())));
    }
    return b.build();
  }

  @Bean
  S3Presigner s3Presigner(@Value("${aws.region}") String region,
                          @Value("${aws.s3.endpoint:}") Optional<String> endpoint,
                          @Value("${aws.s3.accessKey:}") Optional<String> ak,
                          @Value("${aws.s3.secretKey:}") Optional<String> sk) {
    S3Presigner.Builder b = S3Presigner.builder().region(Region.of(region));
    endpoint.ifPresent(e -> b.endpointOverride(URI.create(e)));
    if (ak.isPresent() && sk.isPresent()) {
      b.credentialsProvider(StaticCredentialsProvider.create(
          AwsBasicCredentials.create(ak.get(), sk.get())));
    }
    return b.build();
  }
}

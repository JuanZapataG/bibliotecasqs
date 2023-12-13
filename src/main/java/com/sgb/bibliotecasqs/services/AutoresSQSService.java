package com.sgb.bibliotecasqs.services;

import com.amazonaws.services.sqs.AmazonSQS;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AutoresSQSService {
    private final AmazonSQS sqsClient;
    private String getQueueUrl() {
        return sqsClient.getQueueUrl("autores-libros").getQueueUrl();
    }

}

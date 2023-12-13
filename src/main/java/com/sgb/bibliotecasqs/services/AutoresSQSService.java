package com.sgb.bibliotecasqs.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.sgb.bibliotecasqs.model.Autor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AutoresSQSService {
    private final AmazonSQS sqsClient;
    private String getQueueUrl() {
        return sqsClient.getQueueUrl("autores-libros").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, Autor autor) {
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();
        atributosMensaje.put("id",
                new MessageAttributeValue()
                        .withStringValue(autor.id())
                        .withDataType("String")
        );
        atributosMensaje.put("nombre",
                new MessageAttributeValue()
                        .withStringValue(autor.nombre())
                        .withDataType("String")
        );
        atributosMensaje.put("nacionalidad",
                new MessageAttributeValue()
                        .withStringValue(autor.nacionalidad())
                        .withDataType("String")
        );
        atributosMensaje.put("fechaNacimiento",
                new MessageAttributeValue()
                        .withStringValue(autor.fechaNacimiento())
                        .withDataType("String")
        );
        atributosMensaje.put("fechaDefuncion",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(autor.fechaDefuncion()).orElse(""))
                        .withDataType("String")
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(autor.nombre())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}

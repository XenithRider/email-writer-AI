package com.email.email_writer;


import com.email.email_writer.contoller.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailGeneratorService {

    @Value("${gemeni.api.url}")
    private String gemeniApiUrl ;

    @Value("${gemeni.api.key}")
    private String gemeniApiKey ;

    public String generateEmailReply(EmailRequest emailRequest){
        // Build the prompt ( go to gemeni api)

        String prompt = buildPrompt( emailRequest);

        // Craft the request
        Map<String , Object> requestBody = Map.of(
                "contents" , new Object[]{
                        Map.of("parts" , new Object[]{
                                Map.of("text" , prompt)
                        })

                }
        );

        // Do request and get response



        // Return response
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line");
        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty() ){
            prompt.append("use a ").append(emailRequest.getTone()).append("tone.");
        }

        prompt.append("\nOriginal email:  \n").append(emailRequest.getEmailContent());
        return  prompt.toString();


    }
}

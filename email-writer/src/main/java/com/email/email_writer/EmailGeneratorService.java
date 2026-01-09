package com.email.email_writer;


import com.email.email_writer.contoller.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    // by adding construct this will injected during the runtime
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


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
        String response = webClient.post()
                .uri(gemeniApiUrl + gemeniApiKey)
                .header("Content-Type" , "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();



        // Extract Response and Return Response
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        }catch ( Exception e){
            return " Error processing request : " + e.getMessage();
        }
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

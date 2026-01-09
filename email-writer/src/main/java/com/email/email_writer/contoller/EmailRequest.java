package com.email.email_writer.contoller;


import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone ;
}

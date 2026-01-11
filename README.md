# Email Writer - AI-Powered Email Reply Generator
An intelligent email reply generation application that uses Google's Gemini API to craft professional email responses with customizable tones.
## Overview
Email Writer is a Spring Boot application that helps users generate contextual and professional email replies. Simply provide the original email content and specify the desired tone, and the application will generate an appropriate response using AI.


## Live Demo

**Frontend**
👉 https://email-writer-frontend-9ysh.vercel.app/

**Backend**
👉 https://honest-nature-production-78f1.up.railway.app/

## Technology Stack
```
Java 17
Spring Boot 4.0.1
Spring WebMVC - REST API endpoints
Spring WebFlux - Reactive HTTP client for external API calls
Lombok - Reduces boilerplate code
Maven - Dependency management and build tool
Google Gemini API - AI text generation
```
## Project Structure
```
email-writer/
├── src/
│   ├── main/
│   │   ├── java/com/email/email_writer/
│   │   │   ├── Config/
│   │   │   │   ├── CorsConfig.java          # CORS configuration
│   │   │   │   └── WebClientConfig.java     # WebClient bean configuration
│   │   │   ├── contoller/
│   │   │   │   ├── EmailGeneratorController.java  # REST controller
│   │   │   │   └── EmailRequest.java               # Request DTO
│   │   │   ├── EmailGeneratorService.java          # Business logic
│   │   │   └── EmailWriterApplication.java         # Main application
│   │   └── resources/
│   │       └── application.properties        # Application configuration
│   └── test/
└── pom.xml                                   # Maven dependencies
```

## How To Run
### Option 1: Using Docker 
The easiest way to run the application is using the pre-built Docker image:

```
docker pull xenithrider/email-writer:latest

docker run -p 8080:8080 \
  -e GEMENI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key= \
  -e GEMENI_API_KEY=your_actual_api_key_here \
  xenithrider/email-writer:latest

```
The application will be available at http://localhost:8080

### Option 2: Run from Source
#### 1. Clone the Repository
```
git clone <repository-url>
cd email-writer
```
#### 2. Configure Environment Variables
Set the following environment variables with your Gemini API credentials:
```
export GEMENI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=
export GEMENI_API_KEY=your_actual_api_key_here
```

## Credits

Developed by Sumit Kumar Mandal

B.tech Computer Science and Engineering-2026

## License

This project is for education and personal use.

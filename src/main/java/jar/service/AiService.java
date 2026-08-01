package jar.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent?key=";

    private String callGeminiApi(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        String url = GEMINI_URL + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String, Object> parts = new HashMap<>();
        parts.put("parts", Collections.singletonList(textPart));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", Collections.singletonList(parts));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
            List candidateList = (List) response.getBody().get("candidates");
            Map firstCandidate = (Map) candidateList.get(0);
            Map content = (Map) firstCandidate.get("content");
            List partsList = (List) content.get("parts");
            Map firstPart = (Map) partsList.get(0);

            return (String) firstPart.get("text");
        } catch (Exception e) {
            return "Error calling Gemini API: " + e.getMessage();
        }
    }

    // 1. Existing Feature: Generate Email
    public String generateFollowUpEmail(String customerName, String notes, String tone) {
        String prompt = String.format(
            "Write a concise professional follow-up email to %s. Tone: %s. Context notes: %s",
            customerName, tone != null ? tone : "Professional", notes
        );
        return callGeminiApi(prompt);
    }

    // 2. Feature: Generate Customer Summary
    public String generateCustomerSummary(String customerName, String company, String notes) {
        String prompt = String.format(
            "Provide a brief 3-bullet point executive summary for customer %s from company %s based on these notes: %s",
            customerName, company, notes
        );
        return callGeminiApi(prompt);
    }

    // 3. Feature: Suggest Next Action
    public String suggestNextAction(String customerName, String status, String notes) {
        String prompt = String.format(
            "Based on CRM data - Customer: %s, Current Deal Status: %s, Notes: %s. Suggest the single best next action a sales representative should take right now in 1-2 clear sentences.",
            customerName, status, notes
        );
        return callGeminiApi(prompt);
    }
}
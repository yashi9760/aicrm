package jar.controller;

import jar.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiController {

    @Autowired
    private AiService aiService;

    // 1. Generate Email
    @PostMapping("/generate-email")
    public Map<String, String> generateEmail(@RequestBody Map<String, String> request) {
        String customerName = request.get("customerName");
        String notes = request.get("notes");
        String tone = request.get("tone");
        
        String result = aiService.generateFollowUpEmail(customerName, notes, tone);
        return Map.of("response", result);
    }

    // 2. Generate Customer Summary
    @PostMapping("/summary")
    public Map<String, String> generateSummary(@RequestBody Map<String, String> request) {
        String customerName = request.get("customerName");
        String company = request.get("company");
        String notes = request.get("notes");

        String result = aiService.generateCustomerSummary(customerName, company, notes);
        return Map.of("response", result);
    }

    // 3. Suggest Next Action
    @PostMapping("/suggest-action")
    public Map<String, String> suggestNextAction(@RequestBody Map<String, String> request) {
        String customerName = request.get("customerName");
        String status = request.get("status");
        String notes = request.get("notes");

        String result = aiService.suggestNextAction(customerName, status, notes);
        return Map.of("response", result);
    }
}
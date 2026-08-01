package jar.controller;

import jar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/stats")
    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("totalCustomers", customerRepository.count());
        stats.put("newLeads", customerRepository.countByStatus("NEW_LEAD"));
        stats.put("closedDeals", customerRepository.countByStatus("CLOSED_DEAL"));
        stats.put("followUpsDue", customerRepository.countFollowUpsDue(LocalDate.now()));

        return stats;
    }
}
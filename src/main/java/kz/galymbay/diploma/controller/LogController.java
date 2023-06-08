package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.configuration.ElasticSearchDto;
import kz.galymbay.diploma.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/open-api")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/log")
    public String getLogs(Model model) {
         model.addAttribute("logs", logService.getLogs());
         return "log";
    }
}

package br.com.gsr.corporate_file_processor.controller;

import br.com.gsr.corporate_file_processor.controller.mapper.DashboardControllerMapper;
import br.com.gsr.corporate_file_processor.controller.response.DashboardResponse;
import br.com.gsr.corporate_file_processor.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final DashboardControllerMapper dashboardControllerMapper;

    @GetMapping
    public DashboardResponse getDashboard() {
        return dashboardControllerMapper.toResponse(dashboardService.getDashboard());
    }
}

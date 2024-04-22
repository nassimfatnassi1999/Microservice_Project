package com.saccess.forumservice.controller;
import com.saccess.forumservice.Entities.Report;
import com.saccess.forumservice.services.IGestionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Report")
public class ReportController {
    @Autowired
    IGestionReport gestionReport;

    @GetMapping("/getall")
    public List<Report> getall() {
        return gestionReport.retrieveAllReport();}

    @GetMapping("/getId/{id}")
    public Report getId(@PathVariable("id") Long id) {
        return gestionReport.retreiveReport(id);}

    @PostMapping("/add")
    public Report add(@RequestBody Report report){
        return gestionReport.addReport(report);
    }

    @DeleteMapping("/deleteID/{id}")
    public void delete(@PathVariable("id") long id){
        gestionReport.removeReport(id);
    }

    @PutMapping ("/update")
    public Report update(@RequestBody Report report){
        return gestionReport.updateReport(report);
    }
    @PostMapping("/AddReportAndAssignToUserAndPost/{numU}-{numP}")
    public Report AddReportAndAssignToUserAndPost(@RequestBody Report report,
                                                  @PathVariable("numU") Long userId,
                                                  @PathVariable("numP") Long idPost) {
        return gestionReport.AddReportAndAssignToUserAndPost(report, userId, idPost);
    }
}


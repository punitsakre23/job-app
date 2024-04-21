package com.project.jobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Created Successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        if (jobService.deleteJobById(id)) {
            return ResponseEntity.ok("Job has been deleted successfully!");
        }
        else {
            return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
//    @RequestMapping(method = RequestMethod.PUT, value = "/jobs/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job) {
        if (jobService.updateJobById(id, job)) {
            return ResponseEntity.ok("Job updated successfully!");
        }
        else {
            return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
        }
    }
}

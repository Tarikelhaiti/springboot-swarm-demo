package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  private final JdbcTemplate jdbc;
  @Value("${spring.application.name:demo}") String appName;

  public HelloController(JdbcTemplate jdbc) { this.jdbc = jdbc; }

  @GetMapping("/")
  public String hello() {
    return "Hello from " + appName + " \uD83D\uDC4B";
  }

  @GetMapping("/db")
  public String db() {
    try {
      Integer one = jdbc.queryForObject("SELECT 1", Integer.class);
      return "DB OK (" + one + ")";
    } catch (Exception e) {
      return "DB ERROR: " + e.getMessage();
    }
  }
}

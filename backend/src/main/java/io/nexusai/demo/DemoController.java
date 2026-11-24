package io.nexusai.demo;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api")
public class DemoController {
 private final DemoService service;
 public DemoController(DemoService service){this.service=service;}
 public record Request(String input,String query){}
 @PostMapping("/demo") public Map<String,List<String>> run(@RequestBody Request request){return Map.of("results",service.execute(request.input(),request.query()));}
 @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<Map<String,String>> invalid(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error","Invalid input or unsupported operation"));}
}

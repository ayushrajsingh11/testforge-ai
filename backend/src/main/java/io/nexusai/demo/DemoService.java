package io.nexusai.demo;
import java.util.*;
import org.springframework.stereotype.Service;
@Service public class DemoService {
 public List<String> execute(String input,String query){
  if(input==null||query==null||input.length()>50000||query.length()>2000)throw new IllegalArgumentException("Invalid input size");
  var result=new ArrayList<String>();try{var node=new com.fasterxml.jackson.databind.ObjectMapper().readTree(input);if(node==null||!node.isObject())throw new IllegalArgumentException("Expected JSON object");for(String check:query.split("\\R")){var pair=check.split("=",2);if(pair.length!=2)throw new IllegalArgumentException("Expected field=value");var actual=node.get(pair[0].trim());result.add((actual!=null&&actual.asText().equals(pair[1].trim())?"PASS ":"FAIL ")+check);}return result;}catch(com.fasterxml.jackson.core.JsonProcessingException e){throw new IllegalArgumentException("Invalid JSON");}
 }
}

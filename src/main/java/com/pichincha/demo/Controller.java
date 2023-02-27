package com.pichincha.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;





@RestController
public class Controller extends TokenGenerator {
	
	
	 String token = TokenGenerator.generateToken();
	 
	
	public static final String SECRET_KEY = "secret";
	
	//Method Post ACCEPT
	@PostMapping("/DevOps")
    public ResponseEntity<String> myEndpoint(@RequestHeader("X-Parse-REST-API-KEY") String apiKey, 
    										 @RequestBody String requestBody,
    										 @RequestHeader("X-JWT-KWY") String authorizationHeader) throws JsonProcessingException {
		
		 if (!apiKey.equals("2f5ae96c-b558-4c7b-a590-a501ae1c3f6c")) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
		 }
		 
		   
		
		 // Extraer el token del encabezado de la solicitud
		  String token = authorizationHeader.replace("Bearer ", "");
		  
		  // Verificar que el token no sea nulo ni vacío
		  if (token == null || token.isEmpty()) {
		    return ResponseEntity.badRequest().body("El token es nulo o vacío");
		  }
		  
		  // Analizar y validar el token utilizando la clave secreta
		  try {
		    Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
		    
		  } catch (JwtException e) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
		  }
		 
		  ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(requestBody);
	        
	        // Obtener el valor del campo "name"
	        String name = jsonNode.get("to").asText();
	        
	        // Hacer algo con el valor obtenido
	        String response = "Hello" + " "  +name+ " " + "your message will be send";
	        
		 return ResponseEntity.ok(response);
	       
    }
	
	
	
	
	
	//Denied Other Methods PUT , DELETE, GET
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
    public ResponseEntity<String> notAllowed() {
        String errorMessage = "Solicitud HTTP no permitida";
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorMessage);
    }
	 
	      
	   
	  

}

package br.edu.unoesc.lista27.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.unoesc.lista27.utils.Calculadora;
import br.edu.unoesc.lista27.utils.ConversorNumerico;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class OperationsController {
	
		@GetMapping("/")
		public String listaOperacoes(ModelMap model) {
			
			String content= 
			"""
			query = ?num1={numero1}&num2={numero2}		
			path = /{numero1}/{numero2}
			
			endpoints controller:
			localhost:8080/soma-query
			localhost:8080/soma-forward-query
			localhost:8080/subtrai-query
			localhost:8080/multiplica-forward-query
			
			endpoints rest controller:
			localhost:8080/somar-query
			localhost:8080/somar-path
			localhost:8080/subtrair-query
			localhost:8080/subtrair-path
			localhost:8080/multiplicar-query
			localhost:8080/multiplicar-path
			localhost:8080/dividir-query
			localhost:8080/dividir-path
			localhost:8080/calcular-media-query
			localhost:8080/calcular-media-path
			localhost:8080/calcular-potencia-query
			localhost:8080/calcular-potencia-path
			localhost:8080/calcular-raiz-query?num={numero}
			localhost:8080/calcular-raiz-path/{numero}
			""";
					
			model.addAttribute("resultado", content);
			return "operacoes";
			
		}
	
		@GetMapping("/soma-query")
	    public String somaQuery(
	    		@RequestParam(value = "num1", defaultValue = "0") String num1,
	    		@RequestParam(value = "num2", defaultValue = "0") String num2,
	    		RedirectAttributes ra) {	
			
			Double resultado = Calculadora.somar(
					ConversorNumerico.converteParaDouble(num1),
					ConversorNumerico.converteParaDouble(num2));
			
			ra.addAttribute("operacao", "soma");
			ra.addAttribute("resultado", resultado);
			
	    	return "redirect:/soma-path/" + num1 + "/" + num2;
	    }
		@GetMapping("/soma-path/{num1}/{num2}")
		public String somaPath(
		    @PathVariable String num1, 
		    @PathVariable String num2,
		    @ModelAttribute("operacao") String operacao,
		    @ModelAttribute("resultado") String resultado,
		    ModelMap model) {
		   	    	
			model.addAttribute("operacao", operacao);
		    model.addAttribute("resultado", resultado);
		    
		    System.out.println("Operação: " + model.getAttribute("operacao"));
		    System.out.println("Resultado: " + num1 + " + " + num2 + " = " + model.getAttribute("resultado"));
		    
	    	return "resultado"; 
		    
		}
		
	   
		@GetMapping("/soma-forward-query")
	    public String somaForwardQuery(
	    		@RequestParam(value = "num1", defaultValue = "0") String num1,
		        @RequestParam(value = "num2", defaultValue = "0") String num2,
			    ModelMap model,
			    HttpServletRequest request) {	
			
			Double resultado = Calculadora.somar(
					ConversorNumerico.converteParaDouble(num1),
					ConversorNumerico.converteParaDouble(num2));
			
			model.addAttribute("operacao", "soma");
	    	request.setAttribute("resultado", resultado);
			
	    	return "forward:/soma-forward-path/" + num1 + "/" + num2;
	    }   	
	    @GetMapping("/soma-forward-path/{num1}/{num2}")
	    public String somaForwardPath(
	    		@PathVariable String num1, 
	    		@PathVariable String num2,
	    		Model model,
	    		HttpServletRequest request) {
	    	
	    	System.out.println("Operação: " + request.getAttribute("operacao"));
	    	System.out.println("Resultado: " + num1 + " + " + num2 + " = " + request.getAttribute("resultado"));

	    	model.addAttribute("resultado", Calculadora.somar(
	    			ConversorNumerico.converteParaDouble(num1),
	    			ConversorNumerico.converteParaDouble(num2)));
	    	
	    	return "resultado"; 
	    }


	    @GetMapping("/subtrai-query")
	    public String subtraiQuery(
	    		@RequestParam(value = "num1", defaultValue = "0") String num1,
	    		@RequestParam(value = "num2", defaultValue = "0") String num2,
	    		RedirectAttributes ra) {	
			
			Double resultado = Calculadora.subtrair(
					ConversorNumerico.converteParaDouble(num1),
					ConversorNumerico.converteParaDouble(num2));
			
			ra.addAttribute("operacao", "subtração");
			ra.addAttribute("resultado", resultado);
			
	    	return "redirect:/subtrai-path/" + num1 + "/" + num2;
	    }
	    @GetMapping("/subtrai-path/{num1}/{num2}")
		public String subtraiPath(
		    @PathVariable String num1, 
		    @PathVariable String num2,
		    @ModelAttribute("operacao") String operacao,
		    @ModelAttribute("resultado") String resultado,
		    ModelMap model) {
		   	    	
			model.addAttribute("operacao", operacao);
		    model.addAttribute("resultado", resultado);
		    
		    System.out.println("Operação: " + model.getAttribute("operacao"));
		    System.out.println("Resultado: " + num1 + " - " + num2 + " = " + model.getAttribute("resultado"));
		    
	    	return "resultado"; 
		    
		}

	    
	    @GetMapping("/multiplica-forward-query")
	    public String multiplicaForwardQuery(
	    		@RequestParam(value = "num1", defaultValue = "0") String num1,
		        @RequestParam(value = "num2", defaultValue = "0") String num2,
			    ModelMap model,
			    HttpServletRequest request) {	
			
			Double resultado = Calculadora.multiplicar(
					ConversorNumerico.converteParaDouble(num1),
					ConversorNumerico.converteParaDouble(num2));
			
			model.addAttribute("operacao", "multiplicação");
	    	request.setAttribute("resultado", resultado);
			
	    	return "forward:/multiplica-forward-path/" + num1 + "/" + num2;
	    }
	    @GetMapping("/multiplica-forward-path/{num1}/{num2}")
	    public String multiplicaForwardPath(
	    		@PathVariable String num1, 
	    		@PathVariable String num2,
	    		Model model,
	    		HttpServletRequest request) {
	    	
	    	model.addAttribute("resultado", Calculadora.multiplicar(
	    			ConversorNumerico.converteParaDouble(num1),
	    			ConversorNumerico.converteParaDouble(num2)));
	    	
	    	System.out.println("Operação: " + request.getAttribute("operacao"));
	    	System.out.println(String.format("Resultado: %s x %s = %s", num1, num2, request.getAttribute("resultado")));
	    	
	    	return "resultado"; 
	    }
}


package br.edu.unoesc.lista27.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.edu.unoesc.lista27.utils.Calculadora;
import br.edu.unoesc.lista27.utils.ConversorNumerico;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class OperationsRestController {
	
	// endpoints para operação de SOMA
	@GetMapping("/somar-query")
	public RedirectView somarQuery(
			@RequestParam(value = "num1", defaultValue = "0") String num1,
			@RequestParam(value = "num2", defaultValue = "0") String num2,
			RedirectAttributes ra) { 

		ra.addAttribute("operacao", "soma");
		ra.addFlashAttribute("operacaoFlash", "somaFlash");
    	
		return new RedirectView("/somar-path/" + num1 + "/" + num2);
	}
	@GetMapping("/somar-path/{num1}/{num2}")
	public Double somarPath(
			@PathVariable(value = "num1") String num1,
			@PathVariable(value = "num2") String num2,
			@ModelAttribute("operacao") String operacao,
			@ModelAttribute("operacaoFlash") String operacaoFlash,
			Model model) {

		Double resultado = Calculadora.somar(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));
		
		System.out.println(model.getAttribute("operacao"));
    	System.out.println(model.getAttribute("operacaoFlash"));
    	System.out.println(operacao + " " + operacaoFlash);

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da soma com path params: " + num1.toString() + " + " + num2.toString() + " = " + resultado);

		return resultado;
	}

	// endpoints para operação de SUBTRAÇÃO
	@GetMapping("/subtrair-query")
	public Double subtrairQuery(
			@RequestParam(value = "num1", defaultValue = "0") String num1,
			@RequestParam(value = "num2", defaultValue = "0") String num2) {
		
		Double resultado = Calculadora.subtrair(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da subtração com query params: " + resultado);

		return resultado;
	}
	@GetMapping("/subtrair-path/{num1}/{num2}")
	public Double subtrairPath(
			@PathVariable(value = "num1") String num1,
			@PathVariable(value = "num2") String num2) {
		
		Double resultado = Calculadora.subtrair(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da subtração com path params: " + resultado);
		
		return resultado;
	}

	// endpoints para operação de MULTIPLICAÇÃO
	@GetMapping("/multiplicar-query")
	public Double multiplicarQuery(
			@RequestParam(value = "num1", defaultValue = "0") String num1,
			@RequestParam(value = "num2", defaultValue = "0") String num2) {

		Double resultado = Calculadora.multiplicar(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da multiplicação com query params: " + resultado);

		return resultado;
	}
	@GetMapping("/multiplicar-path/{num1}/{num2}")
	public Double multiplicarPath(
			@PathVariable(value = "num1") String num1,
			@PathVariable(value = "num2") String num2) {
		
		Double resultado = Calculadora.multiplicar(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da multiplicação com path params: " + resultado);

		return resultado;
	}
	
	
	// endpoints para operação de DIVISÃO
	@GetMapping("/dividir-query")
	public Double dividirQuery(
			@RequestParam(value = "num1", defaultValue = "0") String num1,
			@RequestParam(value = "num2", defaultValue = "0") String num2) {
		
		Double resultado = null;
		try {
			resultado = Calculadora.dividir(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

			Logger.getLogger(OperationsRestController.class.getName())
				.log(Level.INFO, "Resultado da divisão com query params: " + resultado);
			
		} catch (ArithmeticException e) { 
			System.out.println("Erro: " + e.getMessage()); 
		}
		
		return resultado;
	}
	@GetMapping("/dividir-path/{num1}/{num2}")
	public Double dividirPath(
		@PathVariable(value = "num1") String num1,
		@PathVariable(value = "num2") String num2) {
		
	Double resultado = null;
	try {
		resultado = Calculadora.dividir(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da divisão com path params: " + resultado);

	} catch (ArithmeticException e) { System.out.println("Erro: " + e.getMessage()); }
		
		return resultado;
	}


	// endpoints para operação de MÉDIA
	@GetMapping("/calcular-media-query")
	public Double mediaQuery(
			@RequestParam(value = "num1", defaultValue = "0") String num1,
			@RequestParam(value = "num2", defaultValue = "0") String num2) {
		
		Double resultado = Calculadora.media(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da média com query params: " + resultado);

		return resultado;
	}
	@GetMapping("/calcular-media-path/{num1}/{num2}")
	public Double mediaPath(
			@PathVariable(value = "num1") String num1,
			@PathVariable(value = "num2") String num2) {
		
		Double resultado = Calculadora.media(
				ConversorNumerico.converteParaDouble(num1),
				ConversorNumerico.converteParaDouble(num2));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da média com path params: " + resultado);

		return resultado;
	}


	// endpoints para operação de POTENCIAÇÃO
	@GetMapping("/calcular-potencia-query")
	public ModelAndView potenciaQuery(
			@RequestParam(value = "base", defaultValue = "0") String base,
			@RequestParam(value = "expoente", defaultValue = "0") String expoente,
			ModelMap mp) {
		
		Double resultado = Calculadora.potencia(
				ConversorNumerico.converteParaDouble(base),
				ConversorNumerico.converteParaDouble(expoente));
		
		mp.addAttribute("operacao", "potencia");
		mp.addAttribute("resultado", resultado);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/potencia-path/" + base + "/" + expoente);
		mav.addAllObjects(mp);
		
		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da potenciação com query params: " + resultado);

		return mav;
	}
	@GetMapping("/calcular-potencia-path/{base}/{expoente}")
	public Double potenciaPath(
			@PathVariable(value = "base") String base,
			@PathVariable(value = "expoente") String expoente,
			@ModelAttribute("operacao") String operacao,
            @ModelAttribute("resultado") String r,
            Model model) {
		
		Double resultado = Calculadora.potencia(
				ConversorNumerico.converteParaDouble(base),
				ConversorNumerico.converteParaDouble(expoente));
		
		System.out.println(model.getAttribute(operacao));
		System.out.println(model.getAttribute(r));

		Logger.getLogger(OperationsRestController.class.getName())
			.log(Level.INFO, "Resultado da potenciação com path params: " + resultado);

		return resultado;
	}

	// endpoints para operação de RAIZ QUADRADA
	@GetMapping("/calcular-raiz-query")
    public ModelAndView raizQuery(
    		@RequestParam(value = "num", defaultValue = "0") String num,
    	    HttpServletRequest request) {
		
		Double resultado = null;
		try {
			resultado = Calculadora.raizQuadrada(
					ConversorNumerico.converteParaDouble(num));  
			
		} catch (ArithmeticException e) { System.out.println("Erro:" + e.getMessage()); }
		
		request.setAttribute("operacao", "raiz-quadrada");
		request.setAttribute("resultado", resultado);
    	
    	return new ModelAndView("forward:/calcular-raiz-path/" + num);
    }
	@GetMapping("/calcular-raiz-path/{num}")
    public Double raizPath(
    		@PathVariable String num,
    		HttpServletRequest request) {
 
		Double resultado = null;
		try {
			resultado = Calculadora.raizQuadrada(
					ConversorNumerico.converteParaDouble(num));
			
			System.out.println(request.getAttribute("operacao"));
			System.out.println(request.getAttribute("resultado"));
			
			Logger.getLogger(OperationsRestController.class.getName())
				.log(Level.INFO, "Resultado da raiz quadrada com path params: " + resultado);
			
		} catch (ArithmeticException e) { System.out.println("Erro:" + e.getMessage()); }
				
    	return resultado;
    }
}

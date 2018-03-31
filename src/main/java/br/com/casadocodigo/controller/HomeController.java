package br.com.casadocodigo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 31 de mar de 2018 - @author jorge - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		System.out.println( "Entrando na home da CDC" );
		return "home";
	}
}

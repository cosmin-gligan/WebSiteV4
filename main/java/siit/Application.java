package siit;


import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

//1. Implementati controllerul pentru "/api/product?term=[value]"
//		Din controller se va returna o lista de produse a caror numa contine string-ul specificat in atributul term
//		Hint: pentru a prelua valoarea lui `term` folositi annotarea @RequestParam similar cu @PathVariable
//
//2. Implementati functionalitatea "Add Product"
//
//		- daca produsul este deja adaugat in orderul curent atunci va trebui sa faceti update  la field-ul "quantity" cu valoarea              noua  adaugata in browser
//
//		- daca produsul nu exista pe orderul curent atunci produsul va trebui adaugat in lista de produse al order-ului curent
//
//		3. imprementati functionalitatea de "Remove product" din lista de produse ale orderlui curent

@SpringBootApplication
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletRegistrationBean dbConsole() {
		return new ServletRegistrationBean<>(new WebServlet(), "/db/*");
	}

}

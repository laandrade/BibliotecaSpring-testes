package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.User;
import br.biblioteca.livros.repository.AutorRepository;
import br.biblioteca.livros.repository.EmprestimoRespository;
import br.biblioteca.livros.repository.LivroRepository;
import br.biblioteca.livros.repository.RoleRepository;
import br.biblioteca.livros.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivrosApplicationTests {
	
	@Autowired
	AutorRepository autorRepository;
	@Autowired
	EmprestimoRespository emprestimoRepository;
	@Autowired
	LivroRepository livroRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void buscaAutoresCadastrados() {
		Page<Autor> autores = this.autorRepository.findAll(new PageRequest(0, 10));
		assertThat(autores.getTotalElements()).isGreaterThan(1L);
	}
	
	@Test
	public void buscaLivrosCadastrados() {
		Page<Livro> livros = this.livroRepository.findAll(new PageRequest(0, 10));
		assertThat(livros.getTotalElements()).isGreaterThan(1L);
	}
	
	@Test
	public void buscaUsuariosCadastrados() {
		Page<User> usuarios = this.userRepository.findAll(new PageRequest(0, 10));
		assertThat(usuarios.getTotalElements()).isGreaterThan(1L);
	}
	
	@Test
	 public void tentaAdicionarUmNovoAutor() throws Exception {
	        this.mvc.perform(post("/autor/novo")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("nome","Lucas Almeida")
	                .accept(MediaType.TEXT_HTML))
	                .andExpect(status().isOk());
	}
	
	@Test
	public void tentaExcluirUmAutor() throws Exception {
        this.mvc.perform(post("/autor/excluir/1")
                .accept(MediaType.TEXT_HTML))
                .andExpect(flash().attribute("mensagem", "O autor foi excluído com sucesso"))
                .andExpect(status().isOk());
    }
	
	@Test
	public void tentaAlterarNomeAutorJaExistente() throws Exception {
        this.mvc.perform(post("/autor/alterar/1")
        		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        		.param("nome","Joao Victor Jairo")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }
	
	@Test
	 public void tentaAdicionarUmNovoLivro() throws Exception {
	        this.mvc.perform(post("/livros/novo")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("nome","A Verdade Oculta")
	                .param("totalPaginas","700")
	                .param("autor","Alice Pedroso")
	                .accept(MediaType.TEXT_HTML))
	                .andExpect(status().isOk());
	}
	
	@Test
	 public void tentaEmprestarLivro() throws Exception {
	        this.mvc.perform(post("/emprestimo/novo")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("livro","Alice no Pais das Maravilhas")
	                .accept(MediaType.TEXT_HTML))
	                .andExpect(status().isOk());
	}
	
	@Test
	public void tentaListarUsuariosdoSistema() throws Exception {
       this.mvc.perform(get("/user/list")
               .accept(MediaType.TEXT_HTML))
               .andExpect(status().isOk());
   }
	
	@Test
	public void tentaBuscarMeusEmprestimos() throws Exception {
       this.mvc.perform(get("/emprestimo/meusemprestimos")
               .accept(MediaType.TEXT_HTML))
               .andExpect(status().isOk());
   }
	
//	@Test
//	public void testaSeOCadastroDeAutorFoiEfetuado() {
//		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lucas Almeida\\Documents\\pos_fib\\java_avancado\\chromedriver.exe");
//
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:8080/autor/novo");
//
//		// encontrando ambos elementos na pagina
//		WebElement nome = driver.findElement(By.name("nome"));
//
//		// digitando em cada um deles
//		nome.sendKeys("Lucas Almeida de Andrade");
//
//		WebElement botaoSalvar = driver.findElement(By.id("button1"));
//		botaoSalvar.click();
//		
//		boolean achouNome = driver.getPageSource().contains("Manoel da Silva");
//		boolean achouEmail = driver.getPageSource().contains("manoel.silva@mailinator.com");
//		
//		assertTrue(achouNome);
//		assertTrue(achouEmail);
//		driver.close();
//	}

}

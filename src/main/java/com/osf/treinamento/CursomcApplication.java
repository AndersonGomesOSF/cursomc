package com.osf.treinamento;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.osf.treinamento.domain.Categoria;
import com.osf.treinamento.domain.Cidade;
import com.osf.treinamento.domain.Cliente;
import com.osf.treinamento.domain.Endereco;
import com.osf.treinamento.domain.Estado;
import com.osf.treinamento.domain.Produto;
import com.osf.treinamento.domain.enums.TipoCliente;
import com.osf.treinamento.repositories.CategoriaRepository;
import com.osf.treinamento.repositories.CidadeRepository;
import com.osf.treinamento.repositories.ClienteRepository;
import com.osf.treinamento.repositories.EnderecoRepository;
import com.osf.treinamento.repositories.EstadoRepository;
import com.osf.treinamento.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "54864795632", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("88888888", "99999999"));
		
		Endereco end1 = new Endereco(null, "Rua A", 320, "ap 408", "Jardims","16546484", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua B", 320, "ap 508", "Aero","16546484", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
				
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}
}

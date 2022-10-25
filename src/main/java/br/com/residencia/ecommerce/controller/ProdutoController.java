package br.com.residencia.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.exception.NoSuchElementFoundException;
import br.com.residencia.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos(){
		return new ResponseEntity<>(produtoService.getAllProdutos(),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
		Produto produto = new Produto();
		
		try {
			produto = produtoService.getProdutoById(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);			
		}catch(Exception ex) {
			throw new NoSuchElementFoundException("Não foi encontrado resultado com o id " + id);
		}
	}

	/*
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
		Produto produto = produtoService.getProdutoById(id);
		if(null != produto)
			return new ResponseEntity<>(produto,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(produto,
					HttpStatus.NOT_FOUND);
	}
	*/
	
	@PostMapping
	public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.saveProduto(produto),
				HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/cadastro-produto-com-foto",
			consumes = { MediaType.APPLICATION_JSON_VALUE,
						MediaType.MULTIPART_FORM_DATA_VALUE}
	)
	public ResponseEntity<ProdutoDTO> saveProdutoFoto(
			@RequestPart("produto") String produtoTxt,
			@RequestPart("filename") MultipartFile file
	) throws IOException{
		ProdutoDTO produtoDTO = produtoService.saveProdutoFoto(produtoTxt, file);
		if(produtoDTO == null)
			return new ResponseEntity<>(produtoDTO, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, 
			@PathVariable Integer id){
		return new ResponseEntity<>(produtoService.updateProduto(produto, id),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable Integer id) {
		Produto produto = produtoService.getProdutoById(id);
		if(null == produto)
			return new ResponseEntity<>(produto,
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(produtoService.deleteProduto(id),
					HttpStatus.OK);
	}

}

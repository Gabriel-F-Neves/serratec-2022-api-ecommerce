package br.com.residencia.ecommerce.service;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.dto.imgbb.ImgBBDTO;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.repository.ProdutoRepository;


@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EmailService emailService;
	
	@Value("${imgbb.host.url}")
	private String imgBBHostUrl;
	
	@Value("${imgbb.host.key}")
    private String imgBBHostKey;
	
	public List<Produto> getAllProdutos(){
		return produtoRepository.findAll();
	}
	
	public Produto getProdutoById(Integer id) {
		//return produtoRepository.findById(id).orElse(null);
		return produtoRepository.findById(id).get();
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public ProdutoDTO saveProdutoFoto(
			String produtoTxt,
			MultipartFile file
	) throws IOException {
				
			RestTemplate restTemplate = new RestTemplate();
			String serverUrl = imgBBHostUrl + imgBBHostKey;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			
			MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
			
			ContentDisposition contentDisposition = ContentDisposition
					.builder("form-data")
					.name("image")
					.filename(file.getOriginalFilename())
					.build();
			
			fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
			
			HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);
			
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("image", fileEntity);
			
			HttpEntity<MultiValueMap<String, Object>> requestEntity =
					new HttpEntity<>(body, headers);
			
			ResponseEntity<ImgBBDTO> response = null;
			ImgBBDTO imgDTO = new ImgBBDTO();
			Produto novoProduto = new Produto(); 
			try {
				response = restTemplate.exchange(
						serverUrl,
						HttpMethod.POST,
						requestEntity,
						ImgBBDTO.class);
				
				imgDTO = response.getBody();
				System.out.println("ImgBBDTO: " + imgDTO.getData().toString());
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
			}
			
			//Converte os dados da editora recebidos no formato String em Entidade
			//  Coleta os dados da imagem, após upload via API, e armazena na Entidade Editora
			if(null != imgDTO) {
				Produto produtoFromJson = convertProdutoFromStringJson(produtoTxt);
				produtoFromJson.setImagemFileName(imgDTO.getData().getImage().getFilename());
				produtoFromJson.setImagemNome(imgDTO.getData().getTitle());
				produtoFromJson.setImagemUrl(imgDTO.getData().getUrl());
				novoProduto = produtoRepository.save(produtoFromJson);
			}
			
			return toDTO(novoProduto);
	}	
	
	private Produto convertProdutoFromStringJson(String produtoJson) {
		Produto produto = new Produto();
		
		try {
//			ObjectMapper objectMapper = new ObjectMapper();
			ObjectMapper objectMapper = new ObjectMapper()
					  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		  
			objectMapper.registerModule(new JavaTimeModule());
			
			produto = objectMapper.readValue(produtoJson, Produto.class);
		} catch (IOException err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Editora", err.toString());
		}
		
		return produto;
	}
	
	public Produto updateProduto(Produto produto, Integer id) {
		Produto produtoExistenteNoBanco = getProdutoById(id);
	
		
		if(produtoExistenteNoBanco!= null) {
		produtoExistenteNoBanco.setIdProduto(produtoExistenteNoBanco.getIdProduto());
		produtoExistenteNoBanco.setNome(produto.getNome());
		produtoExistenteNoBanco.setDescricao(produto.getDescricao());
		produtoExistenteNoBanco.setQtdEstoque(produto.getQtdEstoque());
		produtoExistenteNoBanco.setDataCadastro(produto.getDataCadastro());
		produtoExistenteNoBanco.setValorUnitario(produto.getValorUnitario());
//		produtoExistenteNoBanco.setImagem(produto.getImagem());
		produtoExistenteNoBanco.setCategoria(produto.getCategoria());
		
	
		}
		return produtoRepository.save(produtoExistenteNoBanco);
	}
	
	
	
	public Produto deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
		return getProdutoById(id);
	}
	
	private ProdutoDTO toDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		/*	
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		*/
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNome());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setImagemFileName(produto.getImagemFileName());
		produtoDTO.setImagemNome(produto.getImagemNome());
		produtoDTO.setImagemUrl(produto.getImagemUrl());
		
		return produtoDTO;
	}
}

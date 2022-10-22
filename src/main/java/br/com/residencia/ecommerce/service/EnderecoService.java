package br.com.residencia.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.residencia.ecommerce.dto.ConsultaCepDTO;
import br.com.residencia.ecommerce.entity.Endereco;
import br.com.residencia.ecommerce.repository.EnderecoRepository;


@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Endereco> getAllEnderecos(){
		return enderecoRepository.findAll();	
	}
	
	public Endereco getEnderecoById(Integer id) {
		//return enderecoRepository.findById(id).orElse(null);
		return enderecoRepository.findById(id).get();
	}
	
	public ConsultaCepDTO consultaCepApiExterna(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json/";
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cep", cep);
		
		ConsultaCepDTO consultaCepDTO = restTemplate.getForObject(uri, ConsultaCepDTO.class, params);
		
		return consultaCepDTO;
	}
	
	public Endereco saveEnderecoFromApi(String cep) {
		ConsultaCepDTO consultaCepDTO = consultaCepApiExterna(cep);
		
		Endereco endereco = new Endereco();
		endereco.setCep(consultaCepDTO.getCep());
		endereco.setBairro(consultaCepDTO.getBairro());
		endereco.setCidade(consultaCepDTO.getLocalidade());
		endereco.setComplemento(consultaCepDTO.getComplemento());
		endereco.setNumero(null);
		endereco.setRua(consultaCepDTO.getLogradouro());
		endereco.setUf(consultaCepDTO.getUf());
		
		return enderecoRepository.save(endereco);
	}
	
	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco updateEndereco(Endereco endereco,Integer id) {
		Endereco enderecoExistenteNoBanco = getEnderecoById(id);
		 
		if(  enderecoExistenteNoBanco!= null) {
		enderecoExistenteNoBanco.setIdEndereco(enderecoExistenteNoBanco.getIdEndereco());
		enderecoExistenteNoBanco.setCep(endereco.getCep());
		enderecoExistenteNoBanco.setRua(endereco.getRua());
		enderecoExistenteNoBanco.setBairro(endereco.getBairro());
		enderecoExistenteNoBanco.setCidade(endereco.getCidade());
		enderecoExistenteNoBanco.setNumero(endereco.getNumero());
		enderecoExistenteNoBanco.setComplemento(endereco.getComplemento());
		enderecoExistenteNoBanco.setUf(endereco.getUf());
		}
		return enderecoRepository.save(enderecoExistenteNoBanco);
		
	}
	
	public Endereco deleteEndereco(Integer id) {
		enderecoRepository.deleteById(id);
		return getEnderecoById(id);
	}
}

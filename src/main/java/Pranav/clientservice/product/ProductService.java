package Pranav.clientservice.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	public void removeProduct(int id) {
		productRepository.deleteById(id);
	}
	@SuppressWarnings("unchecked")
	public HashMap<String,Double> getBid(int id) {
		if(productRepository.existsById(id)==false) {
			return null;
		}
		HashMap<String,Double> bids = new HashMap<>();
		RestTemplate restTemplate =new RestTemplate();
		bids = restTemplate.getForObject("http://localhost:8082/products/"+id,HashMap.class);
		return bids;
		
	}
}

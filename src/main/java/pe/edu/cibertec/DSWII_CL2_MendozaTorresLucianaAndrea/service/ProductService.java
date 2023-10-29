package pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.model.bd.Product;
import pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> listarProduct(){
        return productRepository.findAll();
    }
    public Product guardar(Product product){
        return productRepository.save(product);
    }
    public Optional<Product> obtenerProductPorId(Integer id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            return Optional.empty();
        }else
            return product;
    }





    public List<Product> buscarProductosPorNombre(String nombre){
        return productRepository.findByNombre(nombre);
    }

    public List<Product> buscarProductosEntre10Y100(){
        return productRepository.findProductEntre10y100();
    }

    public List<Product> buscarProductosPorAnio2024(Integer anio){
        return productRepository.findProductByYear2024(anio);
    }
}

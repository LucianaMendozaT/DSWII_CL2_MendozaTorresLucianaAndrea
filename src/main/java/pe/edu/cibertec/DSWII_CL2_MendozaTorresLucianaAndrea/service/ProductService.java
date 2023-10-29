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
    public Product guardar(Product category){
        return productRepository.save(category);
    }
    public Optional<Product> obtenerProductPorId(Integer id){
        Optional<Product> category = productRepository.findById(id);
        if(category.isEmpty()){
            return Optional.empty();
        }else
            return category;
    }

    public Optional<Product> obtenerProductPorNombre(String categoryName){
        Optional<Product> category = productRepository.findByProductname(categoryName);
        if(category.isEmpty())
            return  Optional.empty();
        else
            return category;
    }

    public List<Product> obtenerProductPorFiltro(String filtro){
        return productRepository.filtrarProductPorNombreSQL(filtro);
    }
}

package pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.model.bd.Product;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

    Optional<Product> findByProductname(String productName);

    List<Product> findByProductnameContainingIgnoreCase(String filtro);

    @Query("SELECT p FROM Product p WHERE p.productname LIKE %:filtro%")
    List<Product> filtrarProductPorNombre(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM categories WHERE productname LIKE %:filtro%",
            nativeQuery = true)
    List<Product> filtrarProductPorNombreSQL(@Param("filtro") String filtro);

}

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


    public List<Product> findByNombre(String nombre);
    @Query("SELECT p FROM Producto p WHERE p.cantidad > 10 AND p.cantidad < 100")
    public List<Product> findProductEntre10y100();

    @Query(value = "SELECT * FROM Producto p WHERE YEAR(:fecha) = 2024", nativeQuery = true)
    public List<Product> findProductByYear2024(@Param("fecha")Integer anio);


}

package pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.model.bd.Product;
import pe.edu.cibertec.DSWII_CL2_MendozaTorresLucianaAndrea.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> listarProduct(){
        List<Product> productList = new ArrayList<>();
        productService.listarProduct()
                .forEach(productList::add);
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProduct(
            @PathVariable("id") Integer id){
        Product product = productService
                .obtenerProductPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La Producto con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @GetMapping("/productname/{filtro}")
    public ResponseEntity<List<Product>> filtrarProductPorNombre(
            @PathVariable("filtro") String filtro
    ){
        List<Product> productList = new ArrayList<>();
        productService.obtenerProductPorFiltro(filtro)
                .forEach(productList::add);
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Product> registrarProduct(
            @RequestBody Product product
    ){
        return new ResponseEntity<>(
                productService.guardar(product), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> actualizarProduct(
            @PathVariable("id") Integer id,
            @RequestBody Product product
    ){
        Product oldProduct = productService
                .obtenerProductPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el Id Nro. "+
                        id + " no existe."));
        oldProduct.getNombre(product.getNombre());
        oldProduct.setDescripcion(product.getDescripcion());
        oldProduct.getCantidad(product.getCantidad());
        oldProduct.getFechavencimineto(product.getFechavencimineto());

        return new ResponseEntity<>(
                productService.guardar(oldProduct), HttpStatus.OK
        );
    }


}

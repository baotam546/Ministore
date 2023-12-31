package com.sitesquad.ministore.controller.admin;

import com.sitesquad.ministore.model.ProductType;
import com.sitesquad.ministore.dto.ResponseObject;
import com.sitesquad.ministore.service.ProductTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @GetMapping("/productType")
    public ResponseEntity<ResponseObject> getProductTypes() {
        List<ProductType> productTypes = productTypeService.findAll();
        if (productTypes != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Found ProductType list", productTypes)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(500, "Not found ProductType list", "")
            );
        }

    }

    @GetMapping("/productType/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        ProductType foundProductType = productTypeService.findById(id);
        if (foundProductType != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Found ProductType id = " + id, foundProductType)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(500, "Cant find ProductType id = " + id, "")
            );
        }
    }

    @PostMapping("/productType")
    public ResponseEntity<ResponseObject> addProductType(@RequestBody ProductType productType) {
        ProductType addProductType = productTypeService.add(productType);
        if (addProductType != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Add sucessfully ", addProductType)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(500, "Cant add productType", productType)
            );
        }

    }

    @PutMapping("/productType")
    public ResponseEntity<ResponseObject> editProductType(@RequestBody ProductType productType) {
        ProductType editedProductType = productTypeService.edit(productType);
        if (editedProductType != null) {

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Edit sucessfully ", editedProductType)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(500, "Cant edit productType", productType)
            );
        }
    }

    @DeleteMapping("/productType/{id}")
    public ResponseEntity<ResponseObject> deleteProductType(@PathVariable Long id) {
        Boolean isDeleted = productTypeService.delete(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Delete sucessfully ", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(500, "Cant delete productType", "")
            );
        }
    }
}

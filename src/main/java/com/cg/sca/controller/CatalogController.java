package com.cg.sca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sca.exception.SCAException;
import com.cg.sca.exception.ScaExceptionMessage;
import com.cg.sca.model.Catalog;
import com.cg.sca.service.CatalogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "Controller")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Internal server error"),
		@ApiResponse(code = 404, message = "Not found") })
@RestController
@RequestMapping(path="api")
public class CatalogController {

	@Autowired
	CatalogService catalogService;

	
	
	@PostMapping("/addCatalog")
	public ResponseEntity<Catalog> addCatalog(@RequestBody Catalog catalog) {
		Catalog list=catalogService.findCatalog(catalog.getProductId());
		if(list!=null) {
			
			throw new SCAException(ScaExceptionMessage.MESSAGE +catalog.getProductId());
			//System.out.println("Product Already Present");
		}
		
		Catalog data=catalogService.addProductDetails(catalog);
		return new ResponseEntity<>(data,HttpStatus.OK);
		
	}
	
//	@PostMapping("/addProduct")
//	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
//		Product list=catalogService.findByProductId(product.getProductId());
//		Catalog catalog=CatalogService.findProductInCatalog(product.getProductId());
//		if(list!=null) {
//			throw new SCAException(ScaExceptionMessage.MESSAGE +product.getProductId());
//		}
//		if(catalog==null) {
//			throw new SCAException("No Catalog present with given id" +product.getProductId());
//		}
//		
//		Product data=catalogService.addProducts(product);
//		return new ResponseEntity<>(data,HttpStatus.OK);
//		
//	}
//	
	
//  @GetMapping("/DisplayCatalog{productId}")
//  public ResponseEntity<List<Catalog>> listOfProduct(@PathVariable int productId) {
//	Catalog catalog= catalogService.findByProductId(productId);
//		if(catalog==null) {
//			throw new SCAException(ScaExceptionMessage.MESSAGETOUSER +productId);
//		}
//		
//		 List<Catalog> list=catalogService.diplayListofProduct(productId);
//		return new ResponseEntity<>(list,HttpStatus.OK);
//    }

    
}

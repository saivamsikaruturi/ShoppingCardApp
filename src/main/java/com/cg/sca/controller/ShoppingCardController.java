package com.cg.sca.controller;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cg.sca.exception.SCAException;
import com.cg.sca.model.Product;
import com.cg.sca.model.ShoppingCard;
import com.cg.sca.service.ProductService;
import com.cg.sca.service.ShoppingCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Controller")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Internal server error"),
		@ApiResponse(code = 404, message = "Not found") })
@RestController
@RequestMapping(path="api")
@CrossOrigin
public class ShoppingCardController {


	@Autowired
	ShoppingCardService cardService;

	@PutMapping("/addProductInShoppingCard")
	public ResponseEntity<ShoppingCard> addProductInCard(@RequestBody ShoppingCard shoppingCard) {
		//ShoppingCard list=cardService.findProduct(shoppingCard.getProduct());
		List<ShoppingCard>  list=cardService.findShoppingCardDetails(shoppingCard.getCardId());
		if(list==null) {
			throw new SCAException("card Details already Present");
		}
		
		ShoppingCard data=cardService.addProductDetails(shoppingCard);
		return new ResponseEntity<>(data,HttpStatus.OK);
		
	}
	
	
  @GetMapping("/cart/{cardId}")
  public ResponseEntity<List<ShoppingCard>> showhoppingCard(@PathVariable int cardId) {
	ShoppingCard shoppingCard= cardService.findProductById(cardId);
		if(shoppingCard==null) {
			throw new SCAException("No Item Present in Card");
		}
		 List<ShoppingCard> list=cardService.diplayListofShoppingCard(cardId);
		return new ResponseEntity<>(list,HttpStatus.OK);
  }
  
//  @GetMapping("/shoppingCart/addProduct/{productId}")
//  public ResponseEntity<ShoppingCard> addProductToCart(@PathVariable("productId") int productId) {
//      cardService.findByCardId(productId).ifPresent(cardService::addProduct);
//      return return new ResponseEntity<>(list,HttpStatus.OK); ;
//  }
//
//  @GetMapping("/shoppingCart/removeProduct/{productId}")
//  public String removeProductFromCart(@PathVariable("productId") int productId) {
//      productService.findByProductId(productId).ifPresent(cardService::removeProduct);
//      return " Product Removed Successfully";
//      
//      
//      
//  }s
  
  

//  @DeleteMapping("/DeleteProduct/{id}")
//	public String DeleteProduct(@PathVariable Integer id){
//		ShoppingCard list=cardService.findProductDetails(id);
//		if(list==null) {
//			//throw new SCAException("No Product is present with given id ");
//		}
////		cardService.findProductDetails(id).ifPresent(cardService::removeProduct);
//		Product product=cardService.removeProduct();
//		return "Product Removed Successfully" ;
//		
//	}
    
}

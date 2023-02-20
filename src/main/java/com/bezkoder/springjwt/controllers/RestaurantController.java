package com.bezkoder.springjwt.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Restaurant;
import com.bezkoder.springjwt.repository.RestaurantRepository;
import com.bezkoder.springjwt.security.services.RestaurantServiceImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resto")
public class RestaurantController {

	@Autowired
	private RestaurantServiceImpl restaurantService;

	@Autowired
	private RestaurantRepository restaurantRepo;

	@PostMapping("/add")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant resto) {
		System.out.println(" In post of resto :  " + resto);
		Restaurant rest = restaurantService.addResto(resto);

		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}

	@PostMapping(value="/addd",  headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public ResponseEntity<Restaurant> adddRestaurant(@RequestBody Restaurant resto,  @RequestParam("file") MultipartFile file) {
		System.out.println(" In post of resto :  " + resto);
		Restaurant rest = restaurantService.adddResto(resto,file);

		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}
	
	@GetMapping("/show/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<Restaurant> getById(@PathVariable Long id) {
		Optional<Restaurant> rest = restaurantService.getResto(id);
		return rest;

	}

	@GetMapping("/show")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public List<Restaurant> getAllRestaurant() {
		List<Restaurant> list = restaurantService.getAllResto();
		return list;
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteByRestoId(@PathVariable Long id) {
		restaurantService.deleteResto(id);
	}

	@PutMapping("/change")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Restaurant> updateRestoData(@RequestBody Restaurant resto) {
		Restaurant rest = restaurantService.updateResto(resto);
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}

	@PutMapping("/change/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Restaurant> updateRestoByI(@PathVariable(value = "id") Long id,
			@Validated @RequestBody Restaurant restoDetails) throws Exception {
		Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new Exception());
		restaurant.setName(restoDetails.getName());
		restaurant.setAddress(restoDetails.getAddress());
		restaurant.setPhonenumber(restoDetails.getPhonenumber());
		restaurant.setOpentime(restoDetails.getOpentime());
		restaurant.setClosetime(restoDetails.getClosetime());
		restaurant.setFlag(restoDetails.isFlag());// set flag change
		final Restaurant updatedRestaurant = restaurantRepo.save(restaurant);
		return ResponseEntity.ok(updatedRestaurant);
	}

	// change status

	@GetMapping("/changestatus/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Restaurant> changeStatus(@PathVariable Long id) throws Exception {
		boolean isActive = true;
		Restaurant resto = restaurantRepo.findById(id).orElseThrow(() -> new Exception());

		if (resto.isFlag()) {
			isActive = false;
		}

		resto.setFlag(isActive);
		Date d = new Date();
		String date = d.toString();
		resto.setLastUpdatedTime(date.substring(4));
		Restaurant changeStatus = restaurantRepo.save(resto);
		return ResponseEntity.ok(resto);

	}

	// image controller

	@PutMapping("/updaterestimage/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String imageUpload(@RequestParam MultipartFile file, @PathVariable Long id) {
		System.out.println(" In put of File :" + file.getName() + "---" + file.getContentType() + "---"
				+ file.getOriginalFilename());
		return restaurantService.uploadImage(file, id);

	}

//	@GetMapping("/{id}/{filename}")
//	// @GetMapping("/file/{name}")
//	public ResponseEntity<byte[]> getFileByName(@PathVariable Long id, @PathVariable String filename) {
//		System.out.println("name=" + filename + "Id=" + id);
//		Optional<Restaurant> fileOptional = restaurantRepo.findByFilenameAndId(filename, id);
//		System.out.println("fileoptional---------------" + fileOptional);
//		if (fileOptional.isPresent()) {
//			Restaurant file = fileOptional.get();
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//					.body(file.getPic());
//		}
//
//		return ResponseEntity.status(404).body(null);
//	}
//	
	@GetMapping("/search")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Restaurant>>searchRestaurant(@RequestParam("resto") String resto){
		return ResponseEntity.ok(restaurantService.searchRestaurant(resto));
	}

}
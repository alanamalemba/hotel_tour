package com.amalemba.hotel_tour.service;

import com.amalemba.hotel_tour.dto.HotelDto;
import com.amalemba.hotel_tour.exception.ForbiddenException;
import com.amalemba.hotel_tour.exception.UserDoesNotExistException;
import com.amalemba.hotel_tour.model.Hotel;
import com.amalemba.hotel_tour.model.User;
import com.amalemba.hotel_tour.repository.HotelRepository;
import com.amalemba.hotel_tour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    UserRepository userRepository;

    public List<HotelDto> getAllHotels() {

        return hotelRepository.findAll().stream().map(HotelDto::new).toList();
    }

    public HotelDto addHotel(String hotelName, MultipartFile imageFile, Long recommenderId) {
        // Get recommender from db
        User recommender = userRepository.findById(recommenderId).orElseThrow(() -> new UserDoesNotExistException("User not found"));

        //create uploads directory if it does not exist
        String uploadDir = "uploads/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename to avoid conflict
        String originalFileName = imageFile.getOriginalFilename();
        String extension = (originalFileName != null && originalFileName.contains(".")) ?
                originalFileName.substring(originalFileName.lastIndexOf(".")) :
                "";
        String uniqueFileName = System.currentTimeMillis() + "_" + hotelName.replaceAll("\\s+", "_") + extension;

        // save file
        Path destination = Path.of(uploadDir + uniqueFileName);
        try {
            Files.copy(imageFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image file");
        }

        // save hotel data in db
        Hotel newHotel = Hotel.builder().name(hotelName).imageUrl(destination.toString()).recommender(recommender).build();

        Hotel savedHotel = hotelRepository.save(newHotel);

        return new HotelDto(savedHotel);
    }

    public HotelDto markAsVisited(Long hotelId, Long markingUserId) {
        Hotel targetHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));

        if(!Objects.equals(targetHotel.getRecommender().getId(), markingUserId)){
            throw  new ForbiddenException("Only hotel recommender can mark hotel as visited!");
        }

        targetHotel.setVisited(true);

        Hotel markdedHotel = hotelRepository.save(targetHotel);

        return new HotelDto(markdedHotel);
    }
}

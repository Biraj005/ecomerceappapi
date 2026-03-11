package com.biraj.ecomerceappapi.services;

import com.biraj.ecomerceappapi.dto.AddressDto;
import com.biraj.ecomerceappapi.entities.Address;
import com.biraj.ecomerceappapi.entities.User;
import com.biraj.ecomerceappapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private  final UserRepository userRepository;
    public AddressDto addAddress(AddressDto addressDto, Long useId) {

        User user = userRepository.findById(useId).orElseThrow(()->
                new UsernameNotFoundException("User not found with ID: " + useId));

        Address address = new Address();

        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setState(addressDto.getStreet());
        address.setZipCode(addressDto.getZipCode());
        address.setHouseNo(addressDto.getHouseNo());

        user.setAdress(address);

        userRepository.save(user);
        return addressDto;

    }
}

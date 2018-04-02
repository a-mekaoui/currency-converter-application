package com.github.amekaoui.currencyconverter.service.converter;

import com.github.amekaoui.currencyconverter.form.AddressForm;
import com.github.amekaoui.currencyconverter.domain.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter implements Converter<AddressForm, Address> {
    @Override
    public Address convert(AddressForm addressForm) {
        Address address = new Address();
        address.setCountry(addressForm.getCountry());
        address.setStreet(addressForm.getStreet());
        address.setZipCode(addressForm.getZipCode());
        address.setCity(addressForm.getCity());
        return address;
    }
}

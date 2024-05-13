package fsoft.tanvx.utils;

import fsoft.tanvx.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidationUtil {

    private final Validator validator;

    public void validateRequest(Object request){
        Map<String, List<String>> result = validator
                .validate(request)
                .stream()
                .collect(Collectors.groupingBy(o -> o.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));

        if(!result.isEmpty()){
            throw new ValidationException(result);
        }
    }
}

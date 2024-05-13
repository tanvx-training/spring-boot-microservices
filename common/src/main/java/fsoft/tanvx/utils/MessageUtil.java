package fsoft.tanvx.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageUtil {

    private final MessageSource messageSource;

    public String getMessage(String key, String ...args) {

        return messageSource.getMessage(key, args, Locale.getDefault());
    }
}

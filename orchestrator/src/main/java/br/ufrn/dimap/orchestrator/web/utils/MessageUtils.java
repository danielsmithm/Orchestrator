package br.ufrn.dimap.orchestrator.web.utils;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Arrays;

@Component
public class MessageUtils {

    public static final String ERROR_MESSAGE_LIST = "errorMessageList";
    public static final String SUCCESS_MESSAGE_LIST = "successMessageList";

    private MessageUtils(){}

    public void addModelError(Model model, ValidationException validationException){
        addModelError(model,validationException.getMessage());
    }

    public void addModelError(Model model, String... errorMessages){
        model.addAttribute(ERROR_MESSAGE_LIST, Arrays.asList(errorMessages));
    }

    public void addSuccessMessage(Model model, String... successMessages){
        model.addAttribute(SUCCESS_MESSAGE_LIST,Arrays.asList(successMessages));
    }

}

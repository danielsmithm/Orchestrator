package br.ufrn.dimap.orchestrator.web.utils;

import br.ufrn.dimap.orchestrator.web.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    protected MessageUtils messageUtils;

    @Autowired
    public void setMessageUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

}

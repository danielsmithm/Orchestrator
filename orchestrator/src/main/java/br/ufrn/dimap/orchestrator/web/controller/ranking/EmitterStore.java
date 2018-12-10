package br.ufrn.dimap.orchestrator.web.controller.ranking;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmitterStore {

    private Map<String, SseEmitter> registeredEmitters = new ConcurrentHashMap<String, SseEmitter>();

    public SseEmitter createEmitterForSessionId(String sessionId) {
        SseEmitter emitter = new SseEmitter();

        emitter.onCompletion(() -> this.registeredEmitters.remove(emitter));
        emitter.onTimeout(() -> this.registeredEmitters.remove(emitter));

        this.registeredEmitters.put(sessionId, emitter);

        return emitter;
    }


    public void notifyEmmiter(Object message, String sessionId) throws EmmiterNotFoundException {

        SseEmitter emitter = getEmitter(sessionId);

        if (emitter == null) {
            throw new EmmiterNotFoundException("An emmiter for the provided sessionId was not found.");
        }

        try {
            emitter.send(message);
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error on notifying client using SseEmitter", e);
        }

    }

    public Set<String> registeredEmmiters() {
        return registeredEmitters.keySet();
    }

    public SseEmitter getEmitter(String sessionId) {
        return registeredEmitters.get(sessionId);
    }

    public void unregisterEmmiter(String sessionId) {
        SseEmitter emitter = this.getEmitter(sessionId);
        if (emitter != null) {
            emitter.complete();
            registeredEmitters.remove(sessionId);
        }
    }
}
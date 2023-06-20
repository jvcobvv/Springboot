package pl.edu.wat.sportowcyapplication.service;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.sportowcyapplication.repository.AthleteRepository;
import pl.edu.wat.sportowcyapplication.repository.AchivmentRepository;


@Service
@Slf4j
public class ScriptService {
    private final AchivmentRepository achivmentRepository;
    private final AthleteRepository athleteRepository;

    @Autowired
    public ScriptService(AthleteRepository athleteRepository,
                         AchivmentRepository achivmentRepository) {
        this.athleteRepository = athleteRepository;
        this.achivmentRepository = achivmentRepository;
    }

    public String exec(String script){
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()){
            var bindings = context.getBindings("js");
            bindings.putMember("athleteRepository",athleteRepository);
            bindings.putMember("achivmentRepository",achivmentRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }



}


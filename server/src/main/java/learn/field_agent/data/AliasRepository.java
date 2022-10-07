package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface AliasRepository {

    List<Alias> findByName(String name);

    Alias add(Alias alias);

    boolean update(Alias alias);

    @Transactional
    boolean deleteById(int aliasId);
}

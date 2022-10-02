package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AliasRepository {

    Alias mapRow(ResultSet resultSet, int i) throws SQLException;
    List<Alias> findAll();

    @Transactional
    Alias findById(int aliasId);

    Alias add(Alias alias);

    boolean update(Alias alias);

    @Transactional
    boolean deleteById(int aliasId);
}

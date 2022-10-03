package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AliasJdbcTemplateRepository implements AliasRepository {
    private final JdbcTemplate jdbcTemplate;

    public AliasJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Alias> mapper = (resultSet, rowIndex) ->{
        Alias alias = new Alias();
        alias.setAliasId(resultSet.getInt("alias_id"));
        alias.setName(resultSet.getString("name"));
        alias.setPersona(resultSet.getString("persona"));
        alias.setAgentId(resultSet.getInt("agent_id"));

        return alias;
    };

    @Override
    public List<Alias> findByName(String name) {
        final String sql = "select alias_id, name alias_name, persona, agent_id "
                + "from alias "
                + "where name alias_name = ?;";

        return jdbcTemplate.query(sql, mapper, name);
    }

    @Override
    public Alias add(Alias alias) {
        final String sql = "insert into alias (name, persona, agent_id) values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alias.getName());
            ps.setString(2, alias.getPersona());
            ps.setInt(3, alias.getAgentId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        alias.setAliasId(keyHolder.getKey().intValue());
        return alias;
    }

    @Override
    public boolean update(Alias alias) {

        final String sql = "update alias set "
                + "alias_id = ?, "
                + "name = ?, "
                + "persona = ?, "
                + "agent_id = ?;";

        return jdbcTemplate.update(sql,
                alias.getAliasId(),
                alias.getName(),
                alias.getPersona(),
                alias.getAgentId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int aliasId) {
        return jdbcTemplate.update("delete from alias where alias_id = ?", aliasId) > 0;
    }
}

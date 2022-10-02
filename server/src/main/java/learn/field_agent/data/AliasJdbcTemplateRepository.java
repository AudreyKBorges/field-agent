package learn.field_agent.data;

import learn.field_agent.data.mappers.AliasMapper;
import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AliasJdbcTemplateRepository implements AliasRepository {
    private final JdbcTemplate jdbcTemplate;

    public AliasJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Alias mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public List<Alias> findAll() {
        final String sql = "select alias_id, name, persona, agent_id "
                + "from alias limit 1000;";
        return jdbcTemplate.query(sql, new AliasMapper());
    }

    @Override
    @Transactional
    public Alias findById(int aliasId) {

        final String sql = "select alias_id, name, persona, agent_id "
                + "from alias "
                + "where alias_id = ?;";

        Alias alias = jdbcTemplate.query(sql, new AliasMapper(), aliasId).stream()
                .findFirst().orElse(null);

        if (alias != null) {
            addAliases(alias);
        }

        return alias;
    }

    @Override
    public Alias add(Alias alias) {

        final String sql = "insert into alias (alias_id, name, persona, agent_id) "
                + " values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alias.getName());
            ps.setString(2, alias.getPersona());
            ps.setInt(3, alias.getAgentId());;
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
                alias.getName(),
                alias.getPersona(),
                alias.getAgentId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int aliasId) {
        jdbcTemplate.update("delete from alias where alias_id = ?;", aliasId);
        return jdbcTemplate.update("delete from alias where alias_id = ?;", aliasId) > 0;
    }

    private void addAliases(Alias alias) {

        final String sql = "select al.alias_id, al.name, al.persona, al.agency_id "
                + "from alias as al "
                + "inner join alias al on al.agency_id = aa.agency_id "
                + "where aa.agent_id = ?;";

        var aliases = jdbcTemplate.query(sql, new AliasMapper(), alias.getAliasId());
        alias.setAliases(aliases);
    }
}

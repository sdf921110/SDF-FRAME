package ${packageNamePrefix}.db.extractor.${smallClassName};

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import ${packageNamePrefix}.model.${smallClassName}.${className};

public class ${className}Extractor implements ResultSetExtractor<${className}> {

	@Override
	public ${className} extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs.next()) {
			return new ${className}(rs);
		}
		return null;
	}

}

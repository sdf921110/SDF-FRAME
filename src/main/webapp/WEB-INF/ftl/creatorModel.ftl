package ${packageNamePrefix}.db.creator.${smallClassName};

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import ${packageNamePrefix}.model.${smallClassName}.${className};

public class ${className}Creator implements PreparedStatementCreator {

	private String sql;
	private ${className} ${smallClassName};

	public ${className}Creator(String sql, ${className} ${smallClassName}) {
		this.sql = sql;
		this.${smallClassName} = ${smallClassName};
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
				
				int i = 1;
				<#list properties as pro>  	
				<#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"
					||pro.bigProName == "UpdateUser"||pro.bigProName == "UpdateUserId">
				<#else>
				ps.set${pro.intProType}(i++, ${smallClassName}.get${pro.bigProName}());
				</#if>
				</#list>
				return ps;
	}

}

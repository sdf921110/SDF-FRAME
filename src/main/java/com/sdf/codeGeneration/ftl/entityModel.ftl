package ${packageNamePrefix}.model.${smallClassName};

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.sdf.common.model.BasePO;

/**
 * ${tableNameStr}(${tableName})
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
public class ${className} extends BasePO {
	
	private static final long serialVersionUID = 1L;
	
	<#list properties as pro>  
	
	<#if pro.proName == "id" || pro.proName == "createTime" || pro.proName == "createUser" || pro.proName == "createUserId" || pro.proName == "updateTime" || pro.proName == "updateUser" || pro.proName == "updateUserId" || pro.proName == "status">  
	<#else>  
	/**${pro.fieldDesc}**/	
    private ${pro.proType} ${pro.proName};  
	</#if>
	</#list>

	/**无参构造方法**/	
	public ${className}(){};
	
	public ${className}(ResultSet rs) throws SQLException {
		super.id = rs.getInt("id");
		super.status = rs.getInt("status");
		super.createTime = rs.getTimestamp("createTime");
		super.updateTime = rs.getTimestamp("updateTime");
		super.createUser = rs.getString("createUser");
		super.createUserId = rs.getInt("createUserId");
		super.updateUser = rs.getString("updateUser");
		super.updateUserId = rs.getInt("updateUserId");
		
	<#list properties as pro>  	
		<#if pro.proName == "id" || pro.proName == "createTime" || pro.proName == "createUser" || pro.proName == "createUserId" || pro.proName == "updateTime" || pro.proName == "updateUser" || pro.proName == "updateUserId" || pro.proName == "status">  
		<#else>  
		<#if pro.proType == "Integer">  
		this.${pro.proName} = rs.getInt("${pro.proName}");
			<#else>  
		this.${pro.proName} = rs.get${pro.proType}("${pro.proName}");
			</#if>  
		</#if>
	</#list>
	}
	
	/**get,set方法**/
	<#list properties as pro>  
	<#if pro.proType == "String" >  
	public ${pro.proType} get${pro.proName?cap_first}() {
		return StringUtils.isBlank(${pro.proName}) ? ${pro.proName} : ${pro.proName}.trim();
	}
	<#else>  
    public ${pro.proType} get${pro.proName?cap_first}() {
		return this.${pro.proName};
	}
	</#if>
	
	public void set${pro.proName?cap_first}(${pro.proType} ${pro.proName}) {
		this.${pro.proName} = ${pro.proName};
	}	
	</#list>
	
	/**重写toString方法*/
	@Override
	public String toString(){
		return "${tableNameStr}:${className} [<#list properties as pro>${pro.fieldDesc}: ${pro.proName}=" + ${pro.proName} + "<#if pro_has_next>,<#else></#if></#list>]";
	}

}
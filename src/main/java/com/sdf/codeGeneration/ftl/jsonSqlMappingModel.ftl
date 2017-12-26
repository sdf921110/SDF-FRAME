<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">

	<!-- ${tableNameStr}(${tableName})查询SQL -->
	<bean id="${'${tableName}'?upper_case}_SELECT_SQL" class="com.sdf.common.sql.JSONSqlMapping">
		<property name="select">
			<value>
				<![CDATA[
			      		SELECT 
			      		<#list properties as pro>t.${pro.proName}
			      		<#if pro_has_next>,<#else></#if></#list> 
			      		FROM ${tableName} t
						WHERE 
							1=1 
					 ]]>
			</value>
		</property>
		<property name="conditions">
			<list>
			<#list properties as pro>
			<#if pro.proName != "createTime"&&pro.proName != "updateTime"&&pro.proName != "updateUser"&&pro.proName != "img"&&pro.proName != "createUsertId"&&pro.proName != "updateUsertId">
				<#if pro.intProType == "Int">
					<bean class="com.sdf.common.sql.Condition">
						<property name="key" value="${pro.proName}" />
						<property name="value" value="and t.${pro.proName} = ? " />
					</bean>
					<#else>
					<bean class="com.sdf.common.sql.Condition">
						<property name="key" value="${pro.proName}" />
						<property name="value" value="and t.${pro.proName} like ? " />
					</bean>
				</#if>
			<#else>
			</#if>
			</#list>
				<bean class="com.sdf.common.sql.Condition">
					<property name="key" value="beginTime" />
					<property name="value">
						<value>
							<![CDATA[ and t.createTime >=? ]]>
						</value>
					</property>
				</bean>
				<bean class="com.sdf.common.sql.Condition">
					<property name="key" value="endTime" />
					<property name="value">
						<value>
							<![CDATA[ and t.createTime <=? ]]>
						</value>
					</property>
				</bean>
			</list>
		</property>
		<property name="order" value="order by t.id desc " />
	</bean>
	
	
	<!-- ${tableNameStr}(${tableName})新增SQL -->
	<bean id="${'${tableName}'?upper_case}_INSERT_SQL" class="com.sdf.common.sql.JSONSqlMapping">
		<property name="select">
			<value>
				<![CDATA[
			      		INSERT INTO ${tableName} (<#list properties as pro><#if pro.proName == "id"||pro.proName == "updateTime"||pro.proName == "updateUser"||pro.proName == "updateUserId"><#else><#if pro.proName == "desc">`${pro.proName}`<#else>${pro.proName}</#if><#if pro_has_next>,<#else></#if></#if></#list>) values (<#list properties as pro><#if pro.proName == "id"||pro.proName == "updateTime"||pro.proName == "updateUser"||pro.proName == "updateUserId"><#else>?<#if pro_has_next>,<#else></#if></#if></#list>)
					 ]]>
			</value>
		</property>
	</bean>

</beans>
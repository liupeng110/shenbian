package com.sbh.basedao.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.sbh.basedao.utils.DtoUtil.*;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
public class MyBatisTemplate<T  extends Serializable> {
	public String  insert(T obj) {  
        BEGIN();  
        INSERT_INTO(tableName(obj));  
        caculationColumnList(obj);  
        VALUES(returnInsertColumnsName(obj), returnInsertColumnsDefine(obj));  
  
        return SQL();  
    }  
	/**
	 * 批量插入
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String  insertBatch(Map map) {  
		List<T> objList =(List<T>)map.get("list");
        BEGIN();  
        INSERT_INTO(tableName(objList.get(0)));  
        caculationColumnList(objList.get(0));  
        Map<String, String> columnAndValue= new HashMap<String, String>();
        columnAndValue= returnInsertColumnsValue(objList);
        VALUES(columnAndValue.get("columnName"),columnAndValue.get("columnValue")); 
  
        return SQL();
    }  
	public String updateById(T obj) {  
        String idname = id(obj);  
        BEGIN();  
        UPDATE(tableName(obj));  
        caculationColumnList(obj); 
        SET(returnUpdateSetFull(obj));  
        WHEREID(idname); 
        return SQL();  
    }
    public String updateNotNullById(T obj) {  
        String idname = id(obj);  
          
        BEGIN();  
          
        UPDATE(tableName(obj));  
        caculationColumnList(obj); 
        SET(returnUpdateSet(obj));  
        WHEREID(idname);
        return SQL();  
    }  
      
    public String updateToFrom(Map<String, Serializable> para){
    	Serializable to = para.get("to");
    	Serializable from = para.get("from");
        BEGIN();  
        UPDATE(tableName(to));  
        caculationColumnList(to); 
        SET(returnUpdateSetToFrom(to));  
        WHERE(whereColumnNotNullToFrom(from));
        return SQL();  
    }
    
    public String deleteById(T obj) {  
        String idname = id(obj);  
        BEGIN();
        DELETE_FROM(tableName(obj));
        WHEREID(idname);
        return SQL();  
    }  

    public String deleteByObject(T obj){
    	caculationColumnList(obj); 
        BEGIN();
        DELETE_FROM(tableName(obj));
        WHERE(whereColumnNotNull(obj));
        return SQL();  
    }
    public String deleteByParamNotEmpty(Map<String,Object> param){
    	removeEmpty(param);
    	BEGIN();
    	Serializable obj = (Serializable) param.get(SPACE_TABLE_NAME);
        DELETE_FROM(tableName(obj));
        param.remove(SPACE_TABLE_NAME);
        WHERE(whereColumnNotEmpty(param));
        return SQL(); 
    }
    public String deleteByParam(Map<String,Object> param){
    	BEGIN();
    	Serializable obj = (Serializable) param.get(SPACE_TABLE_NAME);
        DELETE_FROM(tableName(obj));
        param.remove(SPACE_TABLE_NAME);
        WHERE(whereColumn(param));
        return SQL(); 
    }
    
    public String queryById(T obj){
    	String idname = id(obj);
    	caculationColumnList(obj); 
    	BEGIN();
    	SELECT(queryColumn(obj));
    	FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
    	WHEREID(idname);
    	return SQL();
    }
    public String queryByObject(T obj){
    	caculationColumnList(obj); 
    	BEGIN();
    	SELECT(queryColumn(obj));
    	FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
    	if(!"".equals(whereColumnNotNull(obj))){
    		WHERE(whereColumnNotNull(obj));
    	}
    	return SQL();
    }
    
    public String queryByParamNotEmpty(Map<String,Object> param){
    	removeEmpty(param);
    	Serializable obj =(Serializable) param.get(SPACE_TABLE_NAME);
    	param.remove(SPACE_TABLE_NAME);
    	Object orderBy = param.get(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	param.remove(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	caculationColumnList(obj); 
    	BEGIN();
    	SELECT(queryColumn(obj));
    	FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
    	if(!param.isEmpty())
        WHERE(whereColumnNotEmpty(param));
        if(null!=orderBy)
        ORDER_BY(orderBy.toString());
        return SQL();    
    }
    public String queryByParamNotEmptyIN(Map<String,Object> param){
    	removeEmpty(param);
    	Serializable obj =(Serializable) param.get(SPACE_TABLE_NAME);
    	param.remove(SPACE_TABLE_NAME);
    	Object orderBy = param.get(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	param.remove(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	caculationColumnList(obj); 
    	BEGIN();
    	SELECT(queryColumn(obj));
    	FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
    	if(!param.isEmpty())
        WHERE(whereColumnNotEmptyIN(param));
        if(null!=orderBy)
        ORDER_BY(orderBy.toString());
        return SQL();    
    }
	public String queryByParam(Map<String,Object> param){
		Serializable obj =(Serializable) param.get(SPACE_TABLE_NAME);
		Object orderBy = param.get(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	param.remove(SPACE_TABLE_NAME);
    	param.remove(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	caculationColumnList(obj); 
    	BEGIN();
    	SELECT(queryColumn(obj));
    	FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
    	if(!param.isEmpty())
        WHERE(whereColumn(param));
        if(null!=orderBy)
            ORDER_BY(orderBy.toString());
        return SQL(); 
    }

	public String queryByObjectCount(T obj) {
		caculationColumnList(obj);
		BEGIN();
		SELECT(" count(*) total ");
		FROM(tableName(obj) + " " + obj.getClass().getSimpleName());
		WHERE(whereColumnNotNull(obj));
		return SQL();
	}

	public String queryByParamNotEmptyCount(Map<String, Object> param) {
		removeEmpty(param);
		Serializable obj = (Serializable)param.remove(SPACE_TABLE_NAME);
		caculationColumnList(obj);
		BEGIN();
		SELECT(" count(*) total ");
		FROM(tableName(obj) + " " + obj.getClass().getSimpleName());
		Object orderBy = param.remove(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
		if(!param.isEmpty())
		WHERE(whereColumn(param));
		param.put(MYBATIS_SPECIAL_STRING.ORDER_BY.name(), orderBy);
		return SQL();
	}

	public String queryByParamCount(Map<String, Object> param) {
		Serializable obj = (Serializable) param.get(SPACE_TABLE_NAME);
		param.remove(SPACE_TABLE_NAME);
		caculationColumnList(obj);
		BEGIN();
		SELECT(" count(*) total ");
		FROM(tableName(obj) + " " + obj.getClass().getSimpleName());
		Object orderBy = param.remove(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
		if(!param.isEmpty())
		WHERE(whereColumn(param));
		param.put(MYBATIS_SPECIAL_STRING.ORDER_BY.name(), orderBy);
		return  SQL(); 
	}
	
    public String queryPageByParamNotEmpty(Map<String,Object> param){
    	removeEmpty(param);
    	Serializable obj =(Serializable)param.remove(SPACE_TABLE_NAME); 
    	String limit = addlimit(param);
    	Object orderBy = param.remove(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	caculationColumnList(obj);
    	BEGIN();
    	SELECT(queryColumn(obj));
    	FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
    	if(!param.isEmpty())
        WHERE(whereColumnNotEmpty(param));
    	if(orderBy!=null){
    		ORDER_BY(orderBy.toString());
    	}
        return  SQL()+limit; 
    }

	public String queryPageByParam(Map<String,Object> param){
		Serializable obj =(Serializable)param.remove(SPACE_TABLE_NAME);
    	String limit = addlimit(param);
    	Object orderBy = param.remove(MYBATIS_SPECIAL_STRING.ORDER_BY.name());
    	caculationColumnList(obj); 
    	BEGIN();
    	SELECT(queryColumn(obj));
    	FROM(tableName(obj)+" "+obj.getClass().getSimpleName());
    	if(!param.isEmpty())
        WHERE(whereColumn(param));
    	if(orderBy!=null){
    		ORDER_BY(orderBy.toString());
    	}
        return SQL()+limit; 
    }
	
    public void WHEREID(final String idname){
    	WHERE(idname.replaceAll("([A-Z])", "_$1").toLowerCase() + "=#{" + idname + "}");
    }
    
    public String addlimit(Map<String,Object> param){
    	String subsql =" "+ MYBATIS_SPECIAL_STRING.LIMIT.name()+" ";
    	Object obj =param.remove(MYBATIS_SPECIAL_STRING.LIMIT.name());
    	if(null==obj){
    		subsql = subsql+"0,10";
    	}else{
    		subsql = subsql+obj;
    	}
    	
    	return subsql;
    }
    
    private void removeEmpty(Map<String,Object> params){
    	Iterator<String> iterator = params.keySet().iterator();
    	while(iterator.hasNext()){
    		String key = iterator.next();
    		if (key == null)  params.remove(key);
    		if(params.get(key)==null){  
    			params.remove(key);
    			iterator = params.keySet().iterator();
    		}
    	}
    }
}

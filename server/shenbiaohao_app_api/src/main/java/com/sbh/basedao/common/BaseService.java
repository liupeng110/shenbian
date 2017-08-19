package com.sbh.basedao.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbh.annotation.NotColumn;
import com.sbh.basedao.utils.JsonUtil;
import com.sbh.basedao.utils.MYBATIS_SPECIAL_STRING;
import com.sbh.common.IBaseService;
import com.sbh.common.PageInfo;
import com.sbh.common.RemotePage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public abstract class BaseService<T> implements IBaseService<T> {

	protected Log	loggerBase	= LogFactory.getLog(getClass());

	public abstract BaseDao<T> getDao();

	public T insert(T obj) {
		getDao().insert(obj);
		return obj;
	}

	public void insertBatch(List<T> objList) {
		getDao().insertBatch(objList);
	}

	public int updateNotNullById(T obj) {
		return getDao().updateNotNullById(obj);
	}

	public int updateById(T obj) {
		return getDao().updateById(obj);
	}

	/**
	 * to 会生成set后面的sql from会生成where后面的sql
	 */
	public int updateToFrom(T to, T from) {
		return getDao().updateToFrom(to, from);
	}

	public int deleteById(Number id) {
		return getDao().deleteById(id);
	}

	public int deleteByObject(T obj) {
		return getDao().deleteByObject(obj);
	}

	public int deleteByParamNotEmpty(Map<String, Object> param) {
		return getDao().deleteByParamNotEmpty(param);
	}

	public int deleteByParam(Map<String, Object> param) {
		return getDao().deleteByParam(param);
	}

	public T queryById(Number id) {
		return getDao().queryById(id);
	}

	public List<T> queryByObject(T obj) {
		return (List<T>) getDao().queryByObject(obj);
	}

	public List<T> queryByParamNotEmpty(Map<String, Object> params) {
		return getDao().queryByParamNotEmpty(params);
	}

	public List<T> queryByParamNotEmptyIN(Map<String, Object> params) {
		return getDao().queryByParamNotEmptyIN(params);
	}

	public List<T> queryByParam(Map<String, Object> params) {
		return getDao().queryByParam(params);
	}

	public Integer queryByObjectCount(T obj) {
		return getDao().queryByObjectCount(obj);
	}

	public Integer queryByParamNotEmptyCount(Map<String, Object> params) {
		return getDao().queryByParamNotEmptyCount(params);
	}

	public Integer queryByParamCount(Map<String, Object> params) {
		return getDao().queryByParamCount(params);
	}

	/**
	 * 这个已经实现,表一定要有id
	 */
	public RemotePage<T> queryPageByObject(T obj, PageInfo info) {
		Map<String, Object> params = getValuesByObject(obj, info);
		return queryPageByParamNotEmpty(params, info);
	}

	public RemotePage<T> queryPageByParamNotEmpty(Map<String, Object> params, PageInfo info) {
		Integer totalCount = queryByParamNotEmptyCount(params);
		info.setRecordCount(totalCount);
		setLimit(params, info);
		return new RemotePage<T>(getDao().queryPageByParamNotEmpty(params), info);
	}

	public RemotePage<T> queryPageByParam(Map<String, Object> params, PageInfo info) {
		info.setRecordCount(queryByParamCount(params));
		setLimit(params, info);
		return new RemotePage<T>(getDao().queryPageByParam(params), info);
	}

	public void setLimit(final Map<String, Object> params, final PageInfo info) {
		Integer begin = info.getStartRow();
		params.put(MYBATIS_SPECIAL_STRING.LIMIT.name(), begin + "," + info.getPageSize());
	}

	protected Map<String, Object> getValuesByObject(T obj, PageInfo info) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field f : fields) {
			Object notColumn = f.getAnnotation(NotColumn.class);
			if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers()) || notColumn != null)
				continue;
			Object value = null;
			try {
				f.setAccessible(true);
				value = f.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null && (!(value instanceof String) || (value instanceof String && StringUtils.isNotBlank((String) value))))
				map.put(f.getName(), value);
		}

		map.put(MYBATIS_SPECIAL_STRING.ORDER_BY.name(), info.getOrderByFiled());

		return map;
	}

	/**
	 * 期望查询单条数据时，检查有没有传入查询条件。
	 * @author 张克行
	 * @since 2016年3月2日
	 * @param obj
	 * @return
	 */
	private boolean hasCondition(T obj) {
		Field[] fs = obj.getClass().getDeclaredFields();
		if (fs != null && fs.length > 0) {
			for (Field field : fs) {
				//排除NotColumn注解的成员
				Annotation notColumn = field.getAnnotation(NotColumn.class);
				if (notColumn != null)
					continue;
				
				//排除final成员字段
				boolean isFinal = Modifier.isFinal(field.getModifiers());
				if(isFinal)
					continue;
				
				//排除static成员字段
				boolean isStatic = Modifier.isStatic(field.getModifiers());
				if(isStatic)
					continue;
				
				try {
					if (!field.isAccessible())
						field.setAccessible(true);

					Object v = field.get(obj);
					if (v != null)// 传入的实体类已经设置有查询条件
						return true;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public T queryUniqueByObject(T obj) {
		if(!hasCondition(obj)){
			throw new RuntimeException("没有传入任何查询条件[queryUniqueByObject],请检查代码和程序运行日志。");
		}
		
		List<T> list = queryByObject(obj);
		if (list == null || list.isEmpty()) {
			return null;
		} else if (list.size() > 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("调用类：").append(this.getClass().getName()).append("\n");
			sb.append("参数类型：").append(obj.getClass().getName()).append("\n");
			sb.append("参数：").append(JsonUtil.java2json(obj)).append("\n\n");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			new Exception().printStackTrace(pw);
			pw.flush();
			pw.close();
			String stack = sw.toString();
			loggerBase.error(list.size() - 1 + " 条查询数据被丢弃,这可能不是你的本意.\n\n "//
					+ sb//
					+ stack);
		}
		return list.get(0);
	}

	@Override
	public T queryUniqueByParams(Map<String, Object> params) {
		List<T> list = queryByParam(params);
		if (list == null || list.isEmpty()) {
			return null;
		} else if (list.size() > 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("调用类：").append(this.getClass().getName()).append("\n");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			new Exception().printStackTrace(pw);
			pw.flush();
			pw.close();
			String stack = sw.toString();
			loggerBase.error(list.size() - 1 + " 条查询数据被丢弃,这可能不是你的本意\n\n," + sb + "查询参数: " + params + "\n\n" + stack);
		}
		return list.get(0);
	}

	protected Map<String, Object> getValuesByParamObject(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field f : fields) {
			Object notColumn = f.getAnnotation(NotColumn.class);
			if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers()) || notColumn != null)
				continue;
			Object value = null;
			try {
				f.setAccessible(true);
				value = f.get(obj);
				if (value != null && value instanceof String && StringUtils.isBlank(value.toString())) {
					value = null;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null)
				map.put(f.getName(), value);
		}
		return map;
	}
}

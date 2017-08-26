<<<<<<< HEAD
package com.sbh.basedao.utils;

import java.util.ArrayList;
import java.util.List;

public enum MYBATIS_SPECIAL_STRING{
	ORDER_BY,
	LIMIT,
	COLUMNS,
	TABLES,
	WHERE,
	LIKE;
	public static List<String> list(){
		List<String> result = new ArrayList<String>();
		for (MYBATIS_SPECIAL_STRING entry : MYBATIS_SPECIAL_STRING.values()) {
			result.add(entry.name());
		}
		return result;
	}
}
=======
package com.sbh.basedao.utils;

import java.util.ArrayList;
import java.util.List;

public enum MYBATIS_SPECIAL_STRING{
	ORDER_BY,
	LIMIT,
	COLUMNS,
	TABLES,
	WHERE,
	LIKE;
	public static List<String> list(){
		List<String> result = new ArrayList<String>();
		for (MYBATIS_SPECIAL_STRING entry : MYBATIS_SPECIAL_STRING.values()) {
			result.add(entry.name());
		}
		return result;
	}
}
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2

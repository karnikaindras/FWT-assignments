package com.yash.moviebookingsystem.util;

import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.messages.ExceptionMessage;

public class ObjectUtil {
	
	public static boolean isNull(Object object) {
		boolean isObjectNull =false;
		if(object==null)
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_OBJECT_IS_NULL);
		return isObjectNull;
	}

}

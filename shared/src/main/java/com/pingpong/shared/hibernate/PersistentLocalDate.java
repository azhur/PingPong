/**
 * Copyright U-wiss
 */
package com.pingpong.shared.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.EnhancedUserType;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 15/05/2012
 */

public class PersistentLocalDate implements EnhancedUserType, Serializable {
	private static final long serialVersionUID = -601936423294509557L;

	private static final int[] SQL_TYPES = new int[] { Types.DATE, };

	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	public Class returnedClass() {
		return LocalDate.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		LocalDate dtx = (LocalDate) x;
		LocalDate dty = (LocalDate) y;
		return dtx.equals(dty);
	}

	public int hashCode(Object object) throws HibernateException {
		return object.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		Object timestamp = StandardBasicTypes.DATE.nullSafeGet(rs, names, session, owner);
		if (timestamp == null) {
			return null;
		}
		return new LocalDate(timestamp);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if (value == null) {
			StandardBasicTypes.DATE.nullSafeSet(st, null, index, session);
		} else {
			StandardBasicTypes.DATE.nullSafeSet(st, ((LocalDate) value).toDateTimeAtStartOfDay().toDate(), index, session);
		}
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object assemble(Serializable cached, Object value) throws HibernateException {
		return cached;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	public String objectToSQLString(Object object) {
		throw new UnsupportedOperationException();
	}

	public String toXMLString(Object object) {
		return object.toString();
	}

	public Object fromXMLString(String string) {
		return new LocalDate(string);
	}
}

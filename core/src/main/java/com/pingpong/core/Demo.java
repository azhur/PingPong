/**
 * Copyright U-wiss
 */
package com.pingpong.core;

import com.pingpong.core.dao.PlayerDAO;
import com.pingpong.core.dao.impl.PlayerDAOImpl;
import com.pingpong.domain.Player;
import com.pingpong.domain.enumeration.Gender;
import org.joda.time.LocalDate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/12/2011
 */

public class Demo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/pingpong/core/app-context.xml");
		Player player = new Player();
		player.setEmail("sdfctrftsdf");
		player.setGender(Gender.MALE);
		player.setLogin("loginessyut");
		player.setPassword("pass");
		player.setName("name");
		player.setBirth(new LocalDate());
		PlayerDAO dao= applicationContext.getBean(PlayerDAOImpl.class);

		Player byId = dao.getById(10);
		dao.list();
	}
}

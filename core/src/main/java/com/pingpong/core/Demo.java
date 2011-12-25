/**
 * Copyright U-wiss
 */
package com.pingpong.core;

import com.pingpong.core.dao.PlayerDAO;
import com.pingpong.core.dao.impl.PlayerDAOImpl;
import com.pingpong.domain.Player;
import com.pingpong.domain.enumeration.Gender;
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
		player.setEmail("sdfcsdf");
		player.setGender(Gender.MALE);
		player.setLogin("log");
		player.setPassword("pass");
		player.setName("name");
		PlayerDAO dao= applicationContext.getBean(PlayerDAOImpl.class);
		System.out.println(dao.getById(7).getGender());
	}
}

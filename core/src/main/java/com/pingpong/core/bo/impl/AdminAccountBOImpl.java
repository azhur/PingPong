/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AdminAccountBO;
import com.pingpong.core.dao.AdminAccountDAO;
import com.pingpong.domain.AdminAccount;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */
@Guarded
public class AdminAccountBOImpl extends AbstractBO<Integer, AdminAccount, AdminAccountDAO> implements AdminAccountBO {
}

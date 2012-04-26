/**
 * Copyright U-wiss
 */
package com.pingpong.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 28/03/2012
 */

public abstract class AbstractMessageListener<T> implements MessageListener {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractMessageListener.class);

	private final Class<T> messageClass;

	protected AbstractMessageListener(Class<T> messageClass) {
		this.messageClass = messageClass;
	}

	@Override
	public final void onMessage(Message message) {
		if(message instanceof ObjectMessage) {
			try {
				final ObjectMessage objMessage = (ObjectMessage)message;
				if(messageClass.isInstance(objMessage.getObject())) {
					onMessage((T)objMessage.getObject());
				} else {
					LOG.error("Received wrong JMS object message type: [{}] but expected [{}] ", message.getClass(), messageClass);
				}
			} catch(Exception e) {
				LOG.error("Error on message procession", e);

				throw new RuntimeException("Error on message procession", e);
			}
		} else {
			LOG.error("Received wrong type of JMS message: [{}] but expected [{}] ", message.getClass(), messageClass);
		}
	}

	protected abstract void onMessage(T message) throws Exception;
}

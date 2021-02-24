package msk.rest.vio.emu.client

import com.ibm.msg.client.jms.JmsConnectionFactory
import com.ibm.msg.client.jms.JmsFactoryFactory
import com.ibm.msg.client.wmq.WMQConstants
import msk.rest.vio.emu.domain.Setting
import javax.jms.*

class MQClient() {
    fun sendText(text: String): String {
        val cf = createConnectionFactory()
        var connection: Connection? = null
        var session: Session? = null
        try {
            connection = cf.createConnection(Setting.mqMca, null)
            connection.start()
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
            val producer: MessageProducer = session.createProducer(session.createQueue(Setting.mqIn))
            val message = session.createTextMessage(text)
            producer.send(message)
            return message.getJMSMessageID()
        } finally {
            closeResources(session, connection)
        }
    }

    private fun createConnectionFactory(): JmsConnectionFactory {
        val ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER)
        val cf = ff.createConnectionFactory()
        cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, Setting.mqHost)
        cf.setIntProperty(WMQConstants.WMQ_PORT, Integer.valueOf(Setting.mqPort))
        cf.setStringProperty(WMQConstants.WMQ_CHANNEL, Setting.mqChannel)
        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT)
        cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, Setting.mqManager)
        cf.setStringProperty(WMQConstants.USERID, Setting.mqMca)
        cf.setStringProperty(WMQConstants.PASSWORD, Setting.mqPass)
        return cf
    }

    fun getTexts(): List<String> {
        val cf = createConnectionFactory()
        var connection: Connection? = null
        var session: Session? = null
        try {
            connection = cf.createConnection()
            connection.start()
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
            val messages = ArrayList<String>()
            val consumer: MessageConsumer = session.createConsumer(session.createQueue(Setting.mqOut))
            while (true) {
                val reserve: TextMessage = consumer.receive(100L) as TextMessage? ?: break
                messages.add(reserve.text)
            }
            return messages
        } finally {
            closeResources(session, connection)
        }
    }

    private fun closeResources(session: Session?, connection: Connection?) {
        try {
            if (session != null) session.close()
        } finally {
            try {
                if (connection != null) connection.close()
            } finally {
                // ignore
            }
        }
    }
}
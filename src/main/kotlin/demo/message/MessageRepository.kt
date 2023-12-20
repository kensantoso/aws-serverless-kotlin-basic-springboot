package demo.message

import demo.message.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>
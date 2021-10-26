package io.github.sparty02.debezium.sink.logging;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.debezium.DebeziumException;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.DebeziumEngine.RecordCommitter;
import io.debezium.server.BaseChangeConsumer;
import io.debezium.server.CustomConsumerBuilder;

@Named("logging")
@Dependent
public class LoggingChangeConsumer extends BaseChangeConsumer
    implements DebeziumEngine.ChangeConsumer<ChangeEvent<Object, Object>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingChangeConsumer.class);

  @Override
  public void handleBatch(final List<ChangeEvent<Object, Object>> records,
      final RecordCommitter<ChangeEvent<Object, Object>> committer) throws InterruptedException {

    for (ChangeEvent<Object, Object> changeEvent : records) {
      try {
        LOGGER.info("Received event '{}'", changeEvent);
        committer.markProcessed(changeEvent);
      } catch (Exception e) {
        throw new DebeziumException(e);
      }
    }

    committer.markBatchFinished();
  }
}
package nl.kristalsoftware.ddd.eventstream.base.offsetmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTopicPartitionRepository extends JpaRepository<TopicPartitionData,Long> {

    Optional<TopicPartitionData> findOneByTopicNameAndPartitionNumber(String topicName, Integer partitionNumber);

}

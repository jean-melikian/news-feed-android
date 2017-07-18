package fr.esgi.newsfeed.services.topic;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.Topic;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public interface ITopicService {

    void createTopic(Topic topic, IServiceResultListener<String> resultListener);

    void getTopicsList(IServiceResultListener<List<Topic>> resultListener);

    void getTopicById(String id, IServiceResultListener<Topic> resultListener);

    void deleteTopic(String id, IServiceResultListener<String> resultListener);

    void updateTopic(Topic topic, IServiceResultListener<Topic> resultListener);

}
